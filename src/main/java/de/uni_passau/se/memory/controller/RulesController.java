package de.uni_passau.se.memory.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller for the Rules-window.
 */
public class RulesController {

    /**
     * Closes the Window.
     *
     * @param event when close button clicked
     */
    @FXML
    void CloseWindow(MouseEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}

