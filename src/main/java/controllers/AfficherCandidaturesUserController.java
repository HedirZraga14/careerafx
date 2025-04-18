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

    @FXML
    private ListView<Candidature> listView;

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

    public void chargerCandidatures() throws SQLException {
        List<Candidature> candidatures = cs.recuperer(50, 0); // Limit & offset personnalisables
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
                            candidature.getOffre().getId(), // Corrigé pour afficher l'ID de l'offre
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCandidature.fxml")); // Vérifie bien le chemin
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur lors du chargement de la page d'accueil : " + e.getMessage());
        }
    }

    @FXML
    public void accepterCandidature() {
        Candidature selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatut(StatutCandidature.ACCEPTEE);
            try {
                cs.modifier(selected);
                rafraichirListe();
                showAlert("Candidature acceptée.");
            } catch (SQLException e) {
                showAlert("Erreur lors de l'acceptation : " + e.getMessage());
            }
        } else {
            showAlert("Veuillez sélectionner une candidature à accepter.");
        }
    }

    @FXML
    public void refuserCandidature() {
        Candidature selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatut(StatutCandidature.REFUSEE);
            try {
                cs.modifier(selected);
                rafraichirListe();
                showAlert("Candidature refusée.");
            } catch (SQLException e) {
                showAlert("Erreur lors du refus : " + e.getMessage());
            }
        } else {
            showAlert("Veuillez sélectionner une candidature à refuser.");
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
