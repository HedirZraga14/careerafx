package controllers;

import entities.Freelancer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.FreelancerService;

import java.io.IOException;
import java.util.Optional;

public class ProfileCRUDController {

    @FXML
    private TableView<Freelancer> profileTable;

    @FXML
    private TableColumn<Freelancer, String> colNom;

    @FXML
    private TableColumn<Freelancer, String> colPrenom;

    @FXML
    private TableColumn<Freelancer, String> colEmail;

    @FXML
    private TableColumn<Freelancer, String> colDomaine;

    @FXML
    private TableColumn<Freelancer, String> colTel;

    @FXML
    private Button btnModifier;

    private final FreelancerService freelancerService = new FreelancerService();
    private String userEmail;

    // initialize automatiquement si le FXML appelle initialize()
    @FXML
    public void initialize() {
        if (userEmail != null && !userEmail.isEmpty()) {
            initializeTable();
        }
    }

    // Méthode pour définir l'email et initialiser la table
    public void setUserData(String email) {
        if (email == null || email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "L'email ne peut pas être vide.");
            return;
        }
        this.userEmail = email;

        // S'assurer que la table est prête
        if (profileTable != null) {
            initializeTable();
        }
    }

    // Initialisation des colonnes du tableau
    private void initializeTable() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDomaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        refreshTable();
    }

    // Rafraîchir la table avec les données filtrées par email
    private void refreshTable() {
        if (userEmail == null || userEmail.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "L'email de l'utilisateur est invalide.");
            return;
        }
        ObservableList<Freelancer> data = FXCollections.observableArrayList(
                freelancerService.afficherFreelancersByEmail(userEmail)
        );
        profileTable.setItems(data);
    }

    // Gestion de la modification du numéro de téléphone d'un freelancer
    @FXML
    private void handleModifier() {
        Freelancer selected = profileTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Veuillez sélectionner un freelancer à modifier.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(selected.getTel());
        dialog.setTitle("Modifier le numéro de téléphone");
        dialog.setHeaderText("Modification pour " + selected.getNom() + " " + selected.getPrenom());
        dialog.setContentText("Nouveau numéro de téléphone :");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newTel -> {
            selected.setTel(newTel);
            boolean success = freelancerService.modifierFreelancer(selected);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Modification réussie !");
                refreshTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Échec de la modification.");
            }
        });
    }

    // Ouvrir la fenêtre de gestion de profil
    @FXML
    private void handleProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileCRUD.fxml"));
            Parent root = loader.load();

            ProfileCRUDController crudController = loader.getController();
            crudController.setUserData(userEmail);

            Stage stage = new Stage();
            stage.setTitle("Gestion de Profil");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Impossible d'ouvrir l'interface de gestion de profil.");
        }
    }

    // Fermer la fenêtre
    @FXML
    private void handleClose() {
        Stage stage = (Stage) profileTable.getScene().getWindow();
        stage.close();
    }

    // Afficher une alerte
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
