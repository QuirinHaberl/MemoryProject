package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * Controller of the number of players window.
 */
public class NumberOfPlayersController {

    /**
     * Two is selected as amount of players.
     *
     * @param actionEvent   when button clicked
     */
    public void twoPlayers(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 2);
    }

    /**
     * Three is selected as amount of players.
     *
     * @param actionEvent   when button clicked
     */
    public void threePlayers(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 3);
    }

    /**
     * Four is selected as amount of players.
     *
     * @param actionEvent   when button clicked
     */
    public void fourPlayers(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 4);
    }

    /**
     * Sets the player amount to the passed number and forwards to the next
     * window.
     *
     * @param actionEvent   when button clicked
     * @param number        of players for the currently configured game
     */
    private void selectPlayers (ActionEvent actionEvent, int number) {
        Wrapper.getInstance().getGame().setPlayerAmount(number);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }
}
