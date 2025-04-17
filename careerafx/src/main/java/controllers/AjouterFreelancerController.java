package controllers;

import entities.Freelancer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.UserService;
import javafx.scene.Parent;
import javafx.scene.text.Text;

import java.io.File;

public class AjouterFreelancerController {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<String> sexeComboBox;
    @FXML private TextField telField;
    @FXML private TextField adresseField;
    @FXML private TextField experienceField;
    @FXML private Label statusLabel;

    // Champs pour la photo et le CV
    @FXML private ImageView photoPreview;
    @FXML private Text cvText;

    private final UserService userService = new UserService();

    // Variables pour stocker les fichiers sélectionnés
    private File selectedPhoto;
    private File selectedCV;

    @FXML
    private void initialize() {
        // Initialiser la liste déroulante pour le sexe
        sexeComboBox.getItems().addAll("Homme", "Femme", "Autre");
        sexeComboBox.setValue("Choisir un sexe");
    }

    @FXML
    public void handlePhotoUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg"));
        Stage stage = (Stage) photoPreview.getScene().getWindow();
        selectedPhoto = fileChooser.showOpenDialog(stage);

        if (selectedPhoto != null) {
            Image image = new Image(selectedPhoto.toURI().toString());
            photoPreview.setImage(image);
        }
    }

    @FXML
    public void handleCVUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        Stage stage = (Stage) cvText.getScene().getWindow();
        selectedCV = fileChooser.showOpenDialog(stage);

        if (selectedCV != null) {
            cvText.setText(selectedCV.getName());
        }
    }

    @FXML
    private void ajouterFreelancer(ActionEvent event) {
        // Vérification des champs obligatoires
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() ||
                emailField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                confirmPasswordField.getText().isEmpty() || adresseField.getText().isEmpty() ||
                experienceField.getText().isEmpty()) {

            statusLabel.setText("Tous les champs obligatoires doivent être remplis.");
            statusLabel.setTextFill(Color.RED);
            return;
        }

        // Vérification du mot de passe
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            statusLabel.setText("Les mots de passe ne correspondent pas.");
            statusLabel.setTextFill(Color.RED);
            return;
        }

        // Création de l'objet Freelancer
        Freelancer freelancer = new Freelancer();
        freelancer.setNom(nomField.getText());
        freelancer.setPrenom(prenomField.getText());
        freelancer.setEmail(emailField.getText());
        freelancer.setPassword(passwordField.getText());
        freelancer.setSexe(sexeComboBox.getValue());
        freelancer.setTel(telField.getText());

        // Ajouter les chemins des fichiers photo et CV
        freelancer.setPhoto(selectedPhoto != null ? selectedPhoto.getAbsolutePath() : "");
        freelancer.setCv(selectedCV != null ? selectedCV.getAbsolutePath() : "");

        freelancer.setAdresse(adresseField.getText());
        freelancer.setType("freelancer");
        freelancer.setRoles("[\"ROLE_FREELANCER\"]");

        try {
            freelancer.setAnnees_experience(Integer.parseInt(experienceField.getText()));
        } catch (NumberFormatException e) {
            statusLabel.setText("Veuillez entrer un nombre valide pour les années d'expérience.");
            statusLabel.setTextFill(Color.RED);
            return;
        }

        // Appel du service pour ajouter le freelancer
        try {
            userService.ajouterUser(freelancer);
            statusLabel.setText("Freelancer ajouté avec succès !");
            statusLabel.setTextFill(Color.GREEN);
        } catch (Exception e) {
            statusLabel.setText("Erreur lors de l'ajout du Freelancer.");
            statusLabel.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageCreationCompte.fxml"));
            Parent root = loader.load();
            emailField.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Erreur lors du retour.");
            statusLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }
}
