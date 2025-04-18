package controllers;

import entities.Candidature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import services.CandidatureService;


import java.sql.SQLException;

public class AfficherCandidatureController {

    public TableView<Candidature> candidatureTV;
    public TableColumn<Candidature, Integer> idCol;
    public TableColumn<Candidature, String> utilisateurCol;
    public TableColumn<Candidature, String> statutCol;
    public TableColumn<Candidature, String> cvCol;
    public TableColumn<Candidature, String> lettreCol;
    public Button supprimerBtn;

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        utilisateurCol.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut"));
        cvCol.setCellValueFactory(new PropertyValueFactory<>("cv"));
        lettreCol.setCellValueFactory(new PropertyValueFactory<>("lettreMotivation"));

        refreshTable();
    }

    private void refreshTable() {
        try {
            ObservableList<Candidature> list = FXCollections.observableArrayList(new CandidatureService().getAll());
            candidatureTV.setItems(list);
        } catch (SQLException e) {
            new javafx.scene.control.Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void supprimerCandidature() {
        Candidature selected = candidatureTV.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                new CandidatureService().supprimer(selected.getId());
                refreshTable();
            } catch (SQLException e) {
                new javafx.scene.control.Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
}
