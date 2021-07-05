package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Controller;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController {

    /**
     * Constructs a new game instance
     */
    public StartScreenController() {
        DataDisplay.getInstance().getGame();
    }

    public void singlePlayer(ActionEvent actionEvent) {
        DataDisplay.getInstance().getGame().setPlayerAmount(1);
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }

    public void multiPlayer(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NumberOfPlayers.fxml");
    }

    public void highscore(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("HighScore.fxml");
    }

    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
