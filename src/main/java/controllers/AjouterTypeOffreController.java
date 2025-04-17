package controllers;

import entities.TypeOffre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.TypeOffreService;

import java.io.IOException;

public class AjouterTypeOffreController {

    @FXML
    private TextField nomTypeOffreTF; // Champ de texte pour le nom

    private final TypeOffreService typeOffreService = new TypeOffreService(); // Instance du service

    // Méthode appelée lors du clic sur le bouton "Ajouter"
    @FXML
    private void ajouterTypeOffre(ActionEvent event) {
        String nom = nomTypeOffreTF.getText().trim();

        if (nom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom du TypeOffre ne peut pas être vide.");
        } else {
            TypeOffre typeOffre = new TypeOffre(nom);
            try {
                typeOffreService.ajouter(typeOffre);
                showAlert(Alert.AlertType.INFORMATION, "Succès", "TypeOffre ajouté avec succès !");
                nomTypeOffreTF.clear(); // Réinitialiser le champ
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue : " + e.getMessage());
            }
        }
    }

    // Méthode pour naviguer vers la liste des TypeOffres
    @FXML
    private void afficherTypeOffres(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherTypeOffre.fxml"));
            Parent root = loader.load();
            nomTypeOffreTF.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'ouvrir l'affichage des types d'offres.");
        }
    }

    // Méthode utilitaire pour afficher une alerte
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
