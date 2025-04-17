package controllers;

import entities.Candidature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.candidatureService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AjouterCandidatureController {

    @FXML
    private TextField utilisateurTF;

    @FXML
    private TextArea lettreMotivationTA;

    private int offreId;

    public void initData(int offreId) {
        this.offreId = offreId;
        // Tu peux afficher l’ID dans une étiquette ou l’utiliser lors de la soumission
        System.out.println("Offre ID reçu : " + offreId);
    }

    @FXML
    void soumettreCandidature(ActionEvent event) {
        try {
            String utilisateur = utilisateurTF.getText();
            String lettre = lettreMotivationTA.getText();

            if (utilisateur.isEmpty()) {
                throw new IllegalArgumentException("Le champ utilisateur est requis.");
            }

            Candidature candidature = new Candidature();
            candidature.setOffre_id(offreId); // <-- Utilisation de l'ID reçu
            candidature.setUtilisateur(utilisateur);
            candidature.setStatut("En attente");
            candidature.setDateSoumission(LocalDateTime.now());
            candidature.setCv("cv_par_defaut.pdf");
            candidature.setLettreMotivation(lettre.isEmpty() ? "Lettre par défaut" : lettre);

            candidatureService service = new candidatureService();
            service.ajouter(candidature);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Candidature ajoutée");
            alert.setContentText("La candidature a été soumise avec succès.");
            alert.showAndWait();

        } catch (SQLException | IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la soumission");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void afficherCandidature(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCandidaturesUser.fxml"));
            Parent root = loader.load();

            // Optionnel : si tu veux passer l'offreId à la page suivante
            // AfficherCandidatureController controller = loader.getController();
            // controller.initData(offreId);

            Stage stage = new Stage();
            stage.setTitle("Liste des Candidatures");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d'afficher les candidatures");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    public void afficherOffre(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherOffre.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Afficher la nouvelle scène
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
