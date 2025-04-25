package controllers;

import entities.Candidature;
import entities.Candidature.StatutCandidature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.CandidatureService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherCandidaturesUserController {

    @FXML private ListView<Candidature> listView;
    private ObservableList<Candidature> obs;
    private final CandidatureService cs = new CandidatureService();

    @FXML
    public void initialize() {
        try {
            chargerCandidatures();
        } catch (SQLException e) {
            showAlert("Erreur lors du chargement des candidatures : " + e.getMessage());
        }
    }
    @FXML
    private void handleOffreClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterOffre.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ajouter une Offre");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chargerCandidatures() throws SQLException {
        List<Candidature> candidatures = cs.recuperer(50, 0);
        obs = FXCollections.observableArrayList(candidatures);
        listView.setItems(obs);

        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Candidature candidature, boolean empty) {
                super.updateItem(candidature, empty);
                if (empty || candidature == null) {
                    setText(null);
                } else {
                    setText(String.format("Candidature #%d - Offre ID: %d - Statut: %s",
                            candidature.getId(),
                            candidature.getOffre().getId(),
                            candidature.getStatut()));
                }
            }
        });
    }

    @FXML
    public void supprimerCandidature(ActionEvent actionEvent) {
        Candidature selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                boolean success = cs.supprimer(selected.getId());
                if (success) {
                    obs.remove(selected);
                    showAlert("Candidature supprimée avec succès.");
                } else {
                    showAlert("Aucune candidature trouvée à supprimer.");
                }
            } catch (SQLException e) {
                showAlert("Erreur lors de la suppression : " + e.getMessage());
            }
        } else {
            showAlert("Veuillez sélectionner une candidature à supprimer.");
        }
    }

    @FXML
    public void rafraichirListe() {
        try {
            chargerCandidatures();
        } catch (SQLException e) {
            showAlert("Erreur lors du rafraîchissement : " + e.getMessage());
        }
    }

    @FXML
    private void retourAccueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCandidature.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur lors du retour : " + e.getMessage());
        }
    }

    @FXML
    public void accepterCandidature() {
        changerStatut(StatutCandidature.ACCEPTEE, "acceptée");
    }

    @FXML
    public void refuserCandidature() {
        changerStatut(StatutCandidature.REFUSEE, "refusée");
    }

    private void changerStatut(StatutCandidature statut, String label) {
        Candidature selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatut(statut);
            try {
                cs.modifier(selected);
                rafraichirListe();
                showAlert("Candidature " + label + ".");
            } catch (SQLException e) {
                showAlert("Erreur lors du changement de statut : " + e.getMessage());
            }
        } else {
            showAlert("Veuillez sélectionner une candidature à traiter.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
