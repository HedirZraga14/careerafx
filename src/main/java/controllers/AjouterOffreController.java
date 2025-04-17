package controllers;

import entities.Candidature;
import entities.Offre;
import entities.TypeContrat;
import entities.TypeOffre;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import services.OffreService;
import services.candidatureService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AjouterOffreController {

    public TextField typeContratTF;
    public TextField typeOffreTF;
    public TextField posteTF;
    public TextField entrepriseTF;
    public TextField localisationTF;
    public TextField salaireTF;
    public CheckBox disponibleCB;
    public TextField imageTF;
    public TextField utilisateurTF;
    public Button gererContratBtn;
    public Button gererOffreBtn;

    public void afficherTypeContrat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherTypeContrat.fxml"));
            gererContratBtn.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficherTypeOffres(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherTypeOffre.fxml"));
            gererOffreBtn.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afficherOffres(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherOffre.fxml"));
            typeContratTF.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterOffre(ActionEvent actionEvent) {
        OffreService os = new OffreService();
        try {
            int typeContratId = Integer.parseInt(typeContratTF.getText());
            int typeOffreId = Integer.parseInt(typeOffreTF.getText());

            TypeContrat typeContrat = new TypeContrat();
            typeContrat.setId(typeContratId);

            TypeOffre typeOffre = new TypeOffre();
            typeOffre.setId(typeOffreId);

            String nomPoste = posteTF.getText();
            String entreprise = entrepriseTF.getText();
            String localisation = localisationTF.getText();
            double salaire = Double.parseDouble(salaireTF.getText());
            boolean disponibilite = disponibleCB.isSelected();
            String image = imageTF.getText();
            String utilisateur = utilisateurTF.getText();

            Offre o = new Offre(typeContrat, typeOffre, nomPoste, entreprise, localisation, salaire, disponibilite, image, utilisateur);

            os.ajouter(o);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Offre ajoutée !");
            alert.setContentText("L'offre a été ajoutée avec succès.");
            alert.showAndWait();
        } catch (SQLException | NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setContentText(e.getMessage());
            a.show();
        }
    }


}
