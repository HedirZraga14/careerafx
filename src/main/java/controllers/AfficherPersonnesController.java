package controllers;

import entities.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import services.PersonneService;

import java.sql.SQLException;
import java.util.List;

public class AfficherPersonnesController {
    @javafx.fxml.FXML
    private ListView<Personne> listView;

    ObservableList<Personne> obs;

    PersonneService ps = new PersonneService();
    @FXML
    void initialize(){
        try {
            List<Personne> personnes = ps.recuperer();
            obs = FXCollections.observableArrayList(personnes);
            listView.setItems(obs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void supprimerPersonne(ActionEvent actionEvent) {
        Personne p = listView.getSelectionModel().getSelectedItem();
        try {
            ps.supprimer(p);
            obs.remove(p);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
