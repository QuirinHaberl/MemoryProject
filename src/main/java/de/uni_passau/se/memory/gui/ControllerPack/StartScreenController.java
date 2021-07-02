package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController {
    public void singlePlayer(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }

    public void multiPlayer(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NumberOfPlayers.fxml");
    }

    public void highscore(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("HighScore.fxml");
    }

    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
