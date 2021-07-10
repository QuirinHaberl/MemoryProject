package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    /**
     * TODO Muss Spiel erneut laden
     *
     * @param actionEvent of the GUI
     */
    public void TryAgainClicked(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Game.fxml");
    }

    /**
     * TODO Game muss auch geschlossen werden wenn im Menü --> Main Menu asgewählt wird
     *
     * @param actionEvent of the GUI
     */
    public void MainMenuClicked(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }

    /**
     * To exit the game.
     */
    public void ExitClicked() {
        System.exit(0);
    }
}
