package controllers;

import entities.Offre;
import entities.TypeContrat;
import entities.TypeOffre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.OffreService;
import services.TypeContratService;
import services.TypeOffreService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AjouterOffreController implements Initializable {

    @FXML
    public ComboBox<String> typeContratCB;
    @FXML
    public ComboBox<String> typeOffreCB;
    public TextField posteTF;
    public TextField entrepriseTF;
    public TextField localisationTF;
    public TextField salaireTF;
    public CheckBox disponibleCB;
    public TextField imageTF;
    public TextField utilisateurTF;
    public Button gererContratBtn;
    public Button gererOffreBtn;

    // Mappage nom -> objet
    private Map<String, TypeContrat> mapTypeContrat = new HashMap<>();
    private Map<String, TypeOffre> mapTypeOffre = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Charger les types de contrat
        loadTypeContrats();

        // Charger les types d'offre
        loadTypeOffres();

        disponibleCB.setSelected(true); // Par défaut, l'offre est disponible
    }

    private void loadTypeContrats() {
        try {
            TypeContratService tcs = new TypeContratService();
            List<TypeContrat> listContrats = tcs.recuperer();
            for (TypeContrat tc : listContrats) {
                mapTypeContrat.put(tc.getNom(), tc);
            }
            typeContratCB.setItems(FXCollections.observableArrayList(mapTypeContrat.keySet()));
        } catch (SQLException e) {
            showError("Erreur de chargement des types de contrat", e.getMessage());
        }
    }

    private void loadTypeOffres() {
        try {
            TypeOffreService tos = new TypeOffreService();
            List<TypeOffre> listOffres = tos.recuperer();
            for (TypeOffre to : listOffres) {
                mapTypeOffre.put(to.getNom(), to);
            }
            typeOffreCB.setItems(FXCollections.observableArrayList(mapTypeOffre.keySet()));
        } catch (SQLException e) {
            showError("Erreur de chargement des types d'offre", e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void afficherTypeContrat(ActionEvent event) {
        loadNewScene("/AfficherTypeContrat.fxml");
    }

    public void afficherTypeOffres(ActionEvent event) {
        loadNewScene("/AfficherTypeOffre.fxml");
    }

    public void afficherOffres(ActionEvent actionEvent) {
        loadNewScene("/AfficherOffre.fxml");
    }

    private void loadNewScene(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            if (gererContratBtn.getScene() != null) {
                gererContratBtn.getScene().setRoot(root);
            }
        } catch (IOException e) {
            showError("Erreur de chargement de la scène", e.getMessage());
        }
    }

    public void ajouterOffre(ActionEvent actionEvent) {
        OffreService os = new OffreService();
        try {
            // Validation des champs
            if (isFormInvalid()) {
                throw new IllegalArgumentException("Tous les champs obligatoires doivent être remplis.");
            }

            double salaire = parseSalaire();
            if (salaire <= 0) {
                throw new IllegalArgumentException("Le salaire doit être positif.");
            }

            // Récupérer les objets à partir du ComboBox
            TypeContrat typeContrat = mapTypeContrat.get(typeContratCB.getValue());
            TypeOffre typeOffre = mapTypeOffre.get(typeOffreCB.getValue());

            if (typeContrat == null || typeOffre == null) {
                throw new IllegalArgumentException("Type de contrat ou d'offre non valide.");
            }

            // Création et insertion
            Offre offre = new Offre(
                    typeContrat,
                    typeOffre,
                    posteTF.getText().trim(),
                    entrepriseTF.getText().trim(),
                    localisationTF.getText().trim(),
                    salaire,
                    disponibleCB.isSelected(),
                    imageTF.getText().trim(),
                    utilisateurTF.getText().trim()
            );

            os.ajouter(offre);
            showSuccess("Offre ajoutée !", "L'offre a été ajoutée avec succès.");

        } catch (SQLException | IllegalArgumentException e) {
            showError("Erreur lors de l'ajout de l'offre", e.getMessage());
        }
    }
    @FXML
    private void handleOffreClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterOffre.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ajouter une Offre");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isFormInvalid() {
        return typeContratCB.getValue() == null || typeOffreCB.getValue() == null ||
                posteTF.getText().trim().isEmpty() || entrepriseTF.getText().trim().isEmpty() ||
                localisationTF.getText().trim().isEmpty() || salaireTF.getText().trim().isEmpty();
    }

    private double parseSalaire() {
        try {
            return Double.parseDouble(salaireTF.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Le salaire doit être un nombre valide.");
        }
    }

    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
