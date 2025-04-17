package controllers;

import entities.Chercheur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UserService;

public class AjouterChercheurController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField sexeField;
    @FXML private TextField telField;
    @FXML private TextField photoField;
    @FXML private TextField cvField;
    @FXML private Label statusLabel; // For status messages

    private final UserService userService = new UserService();

    @FXML
    private void ajouterChercheur(ActionEvent event) {
        // Validation des champs obligatoires
        if (emailField.getText().isEmpty() || passwordField.getText().isEmpty() || nomField.getText().isEmpty() || prenomField.getText().isEmpty()) {
            statusLabel.setText("Tous les champs obligatoires doivent être remplis.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        // Création de l'objet Chercheur avec les données saisies
        Chercheur chercheur = new Chercheur();
        chercheur.setEmail(emailField.getText());
        chercheur.setPassword(passwordField.getText());
        chercheur.setNom(nomField.getText());
        chercheur.setPrenom(prenomField.getText());
        chercheur.setSexe(sexeField.getText());
        chercheur.setTel(telField.getText());
        chercheur.setType("chercheur");
        chercheur.setPhoto(photoField.getText());
        chercheur.setCv(cvField.getText());
        chercheur.setRoles("[\"ROLE_CHERCHEUR\"]");  // Attribution du rôle 'chercheur'

        // Appel du service pour ajouter le chercheur
        try {
            userService.ajouterUser(chercheur);
            statusLabel.setText("Chercheur ajouté avec succès !");
            statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } catch (Exception e) {
            // Gestion des erreurs
            statusLabel.setText("Erreur lors de l'ajout du Chercheur.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            e.printStackTrace();  // Affiche la trace d'erreur pour le débogage
        }
    }
}
