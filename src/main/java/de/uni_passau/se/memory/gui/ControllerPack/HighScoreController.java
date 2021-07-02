package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.HighScoreHistory;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class HighScoreController {

    private HighScoreHistory highScoreHistory;

    public HighScoreController () {
        highScoreHistory = HighScoreHistory.getInstance();
        highScoreHistory.loadHighScoreHistory();
        update();
    }

    private void update () {
        //TODO fx:id kann noch nicht verwendet werden
        List<String[]> highScoreList = highScoreHistory.getHighScoreHistory();
    }

    public void back(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }
}
