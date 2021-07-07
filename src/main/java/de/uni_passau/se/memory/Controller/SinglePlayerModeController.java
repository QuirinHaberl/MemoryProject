package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller of the single player mode
 */
public class SinglePlayerModeController {

    /**
     * Sets SinglePlayerMode to lifePOINTS
     *
     * @param actionEvent when button clicked
     */
    public void lives(ActionEvent actionEvent) {
        Wrapper.getInstance().getGame().setSinglePlayerMode(SinglePlayerMode.LIFEPOINTS);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }

    /**
     * Sets SinglePlayerMode to TIME
     *
     * @param actionEvent when button clicked
     */
    public void time(ActionEvent actionEvent) {
        Wrapper.getInstance().getGame().setSinglePlayerMode(SinglePlayerMode.TIME);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }
}
