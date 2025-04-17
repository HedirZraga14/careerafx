package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccueilController {

    @FXML
    private Label bienvenueLabel;

    public void setBienvenueMessage(String message) {
        if (bienvenueLabel != null) {
            bienvenueLabel.setText(message);
        }
    }
}
