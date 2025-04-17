package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexController {

    @FXML
    private Button seConnecterButton;

    @FXML
    void handleConnexion(ActionEvent event) {
        try {
            AnchorPane page = FXMLLoader.load(getClass().getResource("/PageConnexion.fxml"));
            Stage stage = (Stage) seConnecterButton.getScene().getWindow();
            stage.setScene(new Scene(page));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
