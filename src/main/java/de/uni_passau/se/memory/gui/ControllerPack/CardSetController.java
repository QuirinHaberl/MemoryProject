package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CardSetController {
    public void cardSetOne(ActionEvent actionEvent) {
    }

    public void cardSetTwo(ActionEvent actionEvent) {
    }

    public void CardSetThree(ActionEvent actionEvent) {
    }

    private void selectCardSet (ActionEvent actionEvent, int number) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Game.fxml");
    }
}
