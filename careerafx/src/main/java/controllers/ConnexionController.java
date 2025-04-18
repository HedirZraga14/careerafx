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
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion à la base de données
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/careera",  // URL de la base de données
                    "root",    // Utilisateur MySQL
                    ""         // Mot de passe MySQL
            );

            // Requête pour vérifier les identifiants
            String query = "SELECT * FROM user WHERE email = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            // Si un utilisateur correspond aux identifiants, il est authentifié
            if (rs.next()) {
                isValid = true;
            }

            // Fermer les ressources
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

        // Vérifier si les champs sont vides
        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // Vérification des identifiants
        boolean isAuthenticated = checkUserCredentials(email, password);

        if (isAuthenticated) {
            try {
                // Charger le fichier FXML pour la page d'accueil
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur associé (si nécessaire, pour passer des infos comme l'email)
                // AccueilController accueilController = loader.getController();
                // AccueilController.setUserEmail(email); // Si tu veux passer des infos à la page d'accueil

                // Afficher la page d'accueil
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(new Scene(root)); // Change la scène
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Si la connexion échoue, tu pourrais afficher une alerte ou rien faire
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Identifiants incorrects. Veuillez réessayer.");
            alert.showAndWait();
        }
    }

    // Lors du clic sur "Créer un compte"
    @FXML
    void handleCreationCompte(ActionEvent event) {
        try {
            // Charger le fichier FXML pour la page de création de compte
            Parent root = FXMLLoader.load(getClass().getResource("/PageCreationCompte.fxml"));
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
