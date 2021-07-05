package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Controller;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LevelsController {

    public LevelsController(){
    }

    public void level1(ActionEvent actionEvent) {
        selectLevel(actionEvent, 4);
    }

    public void level2(ActionEvent actionEvent) {
        selectLevel(actionEvent, 6);
    }

    public void level3(ActionEvent actionEvent) {
        selectLevel(actionEvent, 8);
    }

    private void selectLevel(ActionEvent actionEvent, int size) {
        DataDisplay.getInstance().getGame().getPlayingField().setBoard(size);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Game.fxml");
    }


}
