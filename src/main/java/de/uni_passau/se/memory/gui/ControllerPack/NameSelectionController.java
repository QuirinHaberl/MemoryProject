package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameSelectionController {

    @FXML
    private TextField input = new TextField();

    public void usePlayerProfile(ActionEvent actionEvent) {
    }

    public void ok(ActionEvent actionEvent) {
        if(new String[]{input.getText()}[0] == null) {
            DataDisplay.getInstance().getGame().addPlayers(1, new String[]{"Spieler 1"});
        } else {
            DataDisplay.getInstance().getGame().addPlayers(1, new String[]{input.getText()});
        }
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Levels.fxml");
    }
}
