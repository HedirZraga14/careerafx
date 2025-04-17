package controllers;

import entities.Candidature;
import entities.Offre;
import entities.TypeContrat;
import entities.TypeOffre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.OffreService;
import services.candidatureService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AfficherOffreController {

    @FXML
    private ListView<Offre> listView;

    @FXML
    private TextField searchField;

    @FXML
    private TextField posteTF;

    @FXML
    private TextField entrepriseTF;

    @FXML
    private TextField localisationTF;

    @FXML
    private TextField salaireTF;

    @FXML
    private CheckBox disponibleCB;

    @FXML
    private TextField imageTF;

    @FXML
    private TextField utilisateurTF;

    @FXML
    private TextField typeContratTF;

    @FXML
    private TextField typeOffreTF;

    private ObservableList<Offre> obs;

    private final OffreService offreService = new OffreService();

    @FXML
    void initialize() {
        try {
            List<Offre> offres = offreService.recuperer();
            obs = FXCollections.observableArrayList(offres);
            listView.setItems(obs);
        } catch (SQLException e) {
            showError("Erreur lors du chargement des offres", e.getMessage());
        }
    }

    @FXML
    public void supprimerOffre(ActionEvent actionEvent) {
        Offre selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            offreService.supprimer(selected);
            obs.remove(selected);
        } else {
            showError("Suppression", "Veuillez sélectionner une offre à supprimer.");
        }
    }

    @FXML
    public void rechercherOffre(ActionEvent actionEvent) {
        String searchText = searchField.getText().toLowerCase().trim();
        try {
            List<Offre> toutesLesOffres = offreService.recuperer();

            if (searchText.isEmpty()) {
                obs = FXCollections.observableArrayList(toutesLesOffres);
            } else {
                List<Offre> offresFiltrees = toutesLesOffres.stream()
                        .filter(o -> o.getNomposte().toLowerCase().contains(searchText))
                        .collect(Collectors.toList());
                obs = FXCollections.observableArrayList(offresFiltrees);
            }

            listView.setItems(obs);
        } catch (SQLException e) {
            showError("Erreur lors de la recherche", e.getMessage());
        }
    }

    @FXML
    public void ajouterOffre(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterOffre.fxml"));
            Object root = loader.load();

            // Récupérer la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Afficher la nouvelle scène
            Scene scene = new Scene((Parent) root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void clearForm() {
        posteTF.clear();
        entrepriseTF.clear();
        localisationTF.clear();
        salaireTF.clear();
        disponibleCB.setSelected(false);
        imageTF.clear();
        utilisateurTF.clear();
        typeContratTF.clear();
        typeOffreTF.clear();
    }

    private void showError(String title, String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.show();
    }
    @FXML
    public void afficherCandidature(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCandidature.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Afficher la nouvelle scène
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void ajouterCandidature(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCandidature.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
