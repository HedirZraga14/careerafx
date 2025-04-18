package controllers;

import entities.Candidature;
import entities.Offre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CandidatureService;
import services.OffreService; // Add a service for fetching offers

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class AjouterCandidatureController {

    @FXML
    private TextField offreIdTF;
    @FXML
    private TextField utilisateurTF;
    @FXML
    private TextField cvTF;
    @FXML
    private TextArea lettreMotivationTA;
    @FXML
    private Button ajouterBtn;
    @FXML
    private TableView<Candidature> candidatureTableView; // TableView for displaying candidatures

    private CandidatureService candidatureService = new CandidatureService(); // Instance of service
    private OffreService offreService = new OffreService(); // Instance of OffreService for checking if the offer exists

    @FXML
    public void ajouterCandidature(ActionEvent event) {
        try {
            // Récupérer les valeurs saisies
            int offreId = Integer.parseInt(offreIdTF.getText());
            String utilisateur = utilisateurTF.getText();
            String cv = cvTF.getText();
            String lettre = lettreMotivationTA.getText();

            // Vérifier si l'offre existe
            Offre offre = offreService.getOffreById(offreId);
            if (offre == null) {
                new Alert(Alert.AlertType.ERROR, "L'offre avec cet ID n'existe pas. Veuillez vérifier l'ID de l'offre.").show();
                return;
            }

            // Créer la candidature
            Candidature candidature = new Candidature();
            candidature.setOffre(offre);
            candidature.setUtilisateur(utilisateur);
            candidature.setCv(cv);
            candidature.setLettreMotivation(lettre);
            candidature.setDateSoumission(LocalDateTime.now());

            // Ajouter la candidature à la base de données
            candidatureService.ajouter(candidature);

            new Alert(Alert.AlertType.INFORMATION, "Candidature ajoutée avec succès.").show();

            // Rafraîchir la table
            afficherCandidatures(offreId);

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Veuillez entrer un ID d'offre valide.").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erreur lors de l'ajout de la candidature : " + e.getMessage()).show();
        }
    }
    @FXML
    private void afficherCandidature() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCandidaturesUser.fxml"));
            AnchorPane root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement de la page : " + e.getMessage()).show();
        }
    }
    @FXML
    public void afficherCandidatures(int offreId) {
        try {
            List<Candidature> candidatures = candidatureService.recuperer(10, 0); // Fetch list of candidatures
            // Filter candidatures by the given offer ID
            List<Candidature> candidaturesFiltrees = candidatures.stream()
                    .filter(c -> c.getOffre().getId() == offreId)
                    .toList();

            // Populate the table with filtered candidatures
            candidatureTableView.getItems().setAll(candidaturesFiltrees);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Erreur lors de l'affichage des candidatures : " + e.getMessage()).show();
        }
    }

    @FXML
    public void afficherOffre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherOffre.fxml"));
            AnchorPane root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement de la page : " + e.getMessage()).show();
        }
    }
}
