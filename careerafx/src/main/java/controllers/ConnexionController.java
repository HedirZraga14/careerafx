package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.sql.*;

public class ConnexionController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    // Méthode pour vérifier les identifiants
    private boolean checkUserCredentials(String email, String password) {
        boolean isValid = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/careera",
                    "root",    // Ton utilisateur MySQL
                    ""         // Ton mot de passe MySQL
            );

            String query = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                isValid = true;
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return isValid;
    }

    // Lors du clic sur "Se connecter"
    @FXML
    void handleConnexion(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        boolean isAuthenticated = checkUserCredentials(email, password);

        if (isAuthenticated) {
            try {
                // Charger le fichier FXML manuellement pour obtenir le contrôleur
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageAccueil.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur associé
                AccueilController accueilController = loader.getController();

                // Passer l'email ou une info personnalisée
                accueilController.setBienvenueMessage("Bienvenue, " + email + "!");

                // Afficher la page d'accueil
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Connexion échouée");
            alert.setContentText("Email ou mot de passe incorrect");
            alert.showAndWait();
        }
    }

    @FXML
    void handleCreationCompte(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/PageCreationCompte.fxml"));
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
