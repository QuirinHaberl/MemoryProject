package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NameSelectionController {

    public void usePlayerProfile(ActionEvent actionEvent) {
    }

    public void ok(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Levels.fxml");
    }
}
