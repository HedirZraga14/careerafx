package controllers;

import entities.Candidature;
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
import services.candidatureService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AfficherCandidaturesUserController {

    @FXML
    private ListView<Candidature> listView;

    private ObservableList<Candidature> obs;
    private final candidatureService cs = new candidatureService();

    @FXML
    void initialize() {
        try {
            chargerCandidatures();
        } catch (SQLException e) {
            System.err.println("Erreur lors du chargement des candidatures : " + e.getMessage());
        }
    }

    public void chargerCandidatures() throws SQLException {
        List<Candidature> candidatures = cs.recuperer(50, 0); // Tu peux adapter le limit/offset
        obs = FXCollections.observableArrayList(candidatures);
        listView.setItems(obs);

        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Candidature candidature, boolean empty) {
                super.updateItem(candidature, empty);

                if (empty || candidature == null) {
                    setText(null);
                } else {
                    setText(String.format("Candidature #%d - Offre: %d - Statut: %s",
                            candidature.getId(),
                            candidature.getOffre_id(),
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
                    System.out.println("Candidature supprimée avec succès.");
                } else {
                    System.err.println("Aucune candidature trouvée à supprimer.");
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la suppression : " + e.getMessage());
            }
        } else {
            System.out.println("Veuillez sélectionner une candidature à supprimer.");
        }
    }

    @FXML
    public void rafraichirListe() {
        try {
            chargerCandidatures();
        } catch (SQLException e) {
            System.err.println("Erreur lors du rafraîchissement : " + e.getMessage());
        }
    }

    @FXML
    private void retourAccueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCandidature.fxml")); // Remplace ce chemin par le bon
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void accepterCandidature() {
        Candidature selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatut("Acceptée");
            try {
                cs.modifier(selected);
                rafraichirListe(); // appelle la version sans paramètre
            } catch (SQLException e) {
                System.err.println("Erreur lors de la modification : " + e.getMessage());
            }
        } else {
            showAlert("Veuillez sélectionner une candidature à accepter.");
        }
    }

    @FXML
    public void refuserCandidature() {
        Candidature selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setStatut("Refusée");
            try {
                cs.modifier(selected);
                rafraichirListe();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la modification : " + e.getMessage());
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
