package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {

    @FXML
    private AnchorPane contentPane;
    @FXML
    private Button profileButton;
    @FXML
    private Button logoutButton;

    // Variables pour stocker les informations de l'utilisateur
    private String userEmail;
    private String userPassword;

    @FXML
    public void initialize() {
        // Initialiser avec des valeurs par défaut
        this.userEmail = "utilisateur@example.com";
        this.userPassword = "••••••••";
    }

    public void setUserCredentials(String email, String password) {
        this.userEmail = email;
        this.userPassword = "••••••••";
    }

    @FXML
    private void handleOffreClick(ActionEvent event) {
        loadUI("/AjouterOffre.fxml");
    }

    @FXML
    private void handleProfile() {
        // Hardcoded email for the user you want to filter
        String email = "choubenighofrane@gmail.com"; // Use the email directly here

        if (email == null || email.trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Email manquant", "Veuillez entrer un email.");
            return;
        }

        try {
            // Load the Profile Management window (ProfileCRUD.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileCRUD.fxml"));
            Parent root = loader.load();

            // Pass the email to the ProfileCRUDController
            ProfileCRUDController crudController = loader.getController();
            crudController.setUserData("choubeniighoffrane@gmail.com"); // Pass the specific email here

            // Create a new Stage (Window) for the profile management
            Stage stage = new Stage();
            stage.setTitle("Gestion de Profil");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'ouvrir la fenêtre de gestion du profil.");
        }
    }
    @FXML
    private void handleDeconnexion(ActionEvent event) {
        try {
            this.userEmail = "";
            this.userPassword = "";

            Parent root = FXMLLoader.load(getClass().getResource("/index.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de se déconnecter");
        }
    }

    private void loadUI(String fxmlPath) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger la vue: " + fxmlPath);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}