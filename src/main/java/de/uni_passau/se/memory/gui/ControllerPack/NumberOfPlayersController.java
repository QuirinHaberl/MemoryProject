package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.Database;
import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NumberOfPlayersController {
    public void twoPlayers(ActionEvent actionEvent) {
        selectPlayers(actionEvent, 2);
    }

    public void threePlayers(ActionEvent actionEvent) {
        selectPlayers(actionEvent, 3);
    }

    public void fourPlayers(ActionEvent actionEvent) {
        selectPlayers(actionEvent, 4);
    }

    private void selectPlayers (ActionEvent actionEvent, int number) {
        DataDisplay.getInstance().getGame().setPlayerAmount(number);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }
}
