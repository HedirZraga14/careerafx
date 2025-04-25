package controllers;

import entities.Offre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.OffreService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AfficherOffreController {

    @FXML
    private ListView<Offre> listView;

    @FXML
    private TextField searchField, posteTF, entrepriseTF, localisationTF, salaireTF, imageTF, utilisateurTF, typeContratTF, typeOffreTF;

    @FXML
    private CheckBox disponibleCB;

    private ObservableList<Offre> obs;
    private final OffreService offreService = new OffreService();

    @FXML
    void initialize() {
        try {
            List<Offre> offres = offreService.recuperer();
            obs = FXCollections.observableArrayList(offres);
            listView.setItems(obs);

            listView.setCellFactory(param -> new ListCell<Offre>() {
                @Override
                protected void updateItem(Offre item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(
                                "Nom du poste: "+ item.getNomposte() + " | " +
                                        "Entreprise: " + item.getEntreprise() + " | " +
                                        "Localisation: " + item.getLocalisation() + " | " +
                                        "Salaire: " + item.getSalaire() + " | " +
                                        (item.isDisponibilite() ? "Disponible" : "Indisponible") + " | " +
                                        "Type Offre: " + item.getTypeOffre().getNom() + " | " +
                                        "Type Contrat: " + item.getTypeContrat().getNom()
                        );
                    }
                }
            });

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
        changerDeScene("/AjouterOffre.fxml", actionEvent);
    }

    @FXML
    public void afficherCandidature(ActionEvent actionEvent) {
        changerDeScene("/AfficherCandidaturesUser.fxml", actionEvent);
    }

    @FXML
    public void ajouterCandidature(ActionEvent actionEvent) {
        changerDeScene("/AjouterCandidature.fxml", actionEvent);
    }

    @FXML
    private void startQuiz(ActionEvent actionEvent) {
        try {
            // Charger la nouvelle scène (Quiz.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Quiz.fxml"));
            Parent root = loader.load();

            // Obtenir le stage actuel
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Changer de scène
            stage.setScene(new Scene(root));

            // Afficher la nouvelle scène
            stage.show();

            System.out.println("Quiz démarré!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la scène Quiz.fxml");
        }
    }



    @FXML
    private void modifierOffre(ActionEvent event) {
        try {
            Offre selected = listView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert(Alert.AlertType.WARNING, "Attention", "Veuillez sélectionner une offre.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierOffre.fxml"));
            Parent root = loader.load();

            ModifierOffreController controller = loader.getController();
            controller.setOffre(selected);

            Stage stage = new Stage();
            stage.setTitle("Modifier une Offre");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changerDeScene(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
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

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
}
