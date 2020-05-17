package pl.gazda.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlPaneController {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button aboutButton;

    public Button getAddButton() {
        return addButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getAboutButton() {
        return aboutButton;
    }
}
