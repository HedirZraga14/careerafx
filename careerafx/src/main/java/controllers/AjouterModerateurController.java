package controllers;

import entities.Moderateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.ModerateurService;

public class AjouterModerateurController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField sexeField;
    @FXML private TextField telField;
    @FXML private TextField societeField;

    @FXML private Label statusLabel;

    private final ModerateurService moderateurService = new ModerateurService();

    @FXML
    private void ajouterModerateur(ActionEvent event) {
        // Vérification des champs obligatoires
        if (emailField.getText().isEmpty() || passwordField.getText().isEmpty() || nomField.getText().isEmpty()
                || prenomField.getText().isEmpty() || societeField.getText().isEmpty()) {
            statusLabel.setText("Tous les champs obligatoires doivent être remplis.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        // Création de l'objet Moderateur
        Moderateur moderateur = new Moderateur();
        moderateur.setEmail(emailField.getText());
        moderateur.setPassword(passwordField.getText());
        moderateur.setNom(nomField.getText());
        moderateur.setPrenom(prenomField.getText());
        moderateur.setSexe(sexeField.getText());
        moderateur.setTel(telField.getText());
        moderateur.setType("moderateur");
        moderateur.setSociete(societeField.getText());


        // Set roles
        moderateur.setRoles("[\"ROLE_MODERATEUR\"]");

        // Appel du service
        try {
            moderateurService.ajouterModerateur(moderateur);
            statusLabel.setText("Modérateur ajouté avec succès !");
            statusLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } catch (Exception e) {
            statusLabel.setText("Erreur lors de l'ajout du modérateur.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
            e.printStackTrace();
        }
    }
}
