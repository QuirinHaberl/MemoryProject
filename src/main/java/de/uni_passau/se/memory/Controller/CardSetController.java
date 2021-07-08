package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller of the card set
 */
public class CardSetController {

    /**
     * Sets the card set on card set one.
     *
     * @param actionEvent when button clicked
     */
    public void cardSetOne(ActionEvent actionEvent) {
    }

    /**
     * Sets the card set on card set one.
     *
     * @param actionEvent when button clicked
     */
    public void cardSetTwo(ActionEvent actionEvent) {
    }

    /**
     * Sets the card set on card set two.
     *
     * @param actionEvent when button clicked
     */
    public void CardSetThree(ActionEvent actionEvent) {
    }

    /**
     * Sets the card set on card set three.
     *
     * @param actionEvent when button clicked
     */
    private void selectCardSet (ActionEvent actionEvent, int number) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Game.fxml");
    }
}
