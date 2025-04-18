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

import java.io.IOException;
import java.sql.SQLException;

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

    // Affichage des types de contrat
    public void afficherTypeContrat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherTypeContrat.fxml"));
            gererContratBtn.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Affichage des types d'offres
    public void afficherTypeOffres(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherTypeOffre.fxml"));
            gererOffreBtn.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Affichage des offres
    public void afficherOffres(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherOffre.fxml"));
            typeContratTF.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ajout d'une nouvelle offre avec validation des champs
    public void ajouterOffre(ActionEvent actionEvent) {
        OffreService os = new OffreService();
        try {
            // Validation des champs de saisie
            if (typeContratTF.getText().trim().isEmpty() || typeOffreTF.getText().trim().isEmpty() || posteTF.getText().trim().isEmpty() ||
                    entrepriseTF.getText().trim().isEmpty() || localisationTF.getText().trim().isEmpty() || salaireTF.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
            }

            // Validation des IDs de TypeContrat et TypeOffre entre 1 et 3
            int typeContratId = Integer.parseInt(typeContratTF.getText().trim());
            int typeOffreId = Integer.parseInt(typeOffreTF.getText().trim());

            if (typeContratId < 1 || typeContratId > 3) {
                throw new IllegalArgumentException("L'ID du type de contrat doit être entre 1 et 3.");
            }
            if (typeOffreId < 1 || typeOffreId > 3) {
                throw new IllegalArgumentException("L'ID du type d'offre doit être entre 1 et 3.");
            }

            // Validation du salaire
            double salaire;
            try {
                salaire = Double.parseDouble(salaireTF.getText().trim());
                if (salaire <= 0) {
                    throw new IllegalArgumentException("Le salaire doit être un montant positif.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Le salaire doit être un nombre valide.");
            }

            // Création des objets TypeContrat et TypeOffre
            TypeContrat typeContrat = new TypeContrat();
            typeContrat.setId(typeContratId);

            TypeOffre typeOffre = new TypeOffre();
            typeOffre.setId(typeOffreId);

            // Récupération des autres champs
            String nomPoste = posteTF.getText().trim();
            String entreprise = entrepriseTF.getText().trim();
            String localisation = localisationTF.getText().trim();
            boolean disponibilite = disponibleCB.isSelected();
            String image = imageTF.getText().trim();
            String utilisateur = utilisateurTF.getText().trim();

            // Création de l'offre
            Offre o = new Offre(typeContrat, typeOffre, nomPoste, entreprise, localisation, salaire, disponibilite, image, utilisateur);

            // Ajout de l'offre dans la base de données
            os.ajouter(o);

            // Affichage de l'alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Offre ajoutée !");
            alert.setContentText("L'offre a été ajoutée avec succès.");
            alert.showAndWait();
        } catch (SQLException | IllegalArgumentException e) {
            // Affichage de l'alerte d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout de l'offre");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
