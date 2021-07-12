package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * Controller of the GUI number of players window.
 */
public class NumberOfPlayersController {

    /**
     * Sets the player amount to the passed number and forwards to the next
     * window.
     *
     * @param actionEvent when button clicked
     * @param number      of players for the currently configured game
     */
    private void selectPlayers(ActionEvent actionEvent, int number) {
        setGamePlayerAmount(getGame(Wrapper.getInstance()), number);
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }

    /**
     * Sets the playerAmount of a game.
     *
     * @param game         whose playerAmount is set
     * @param playerAmount to be set
     */
    public void setGamePlayerAmount(Game game, int playerAmount) {
        game.setPlayerAmount(playerAmount);
    }

    /**
     * Gets the current game.
     *
     * @param wrapper contains the current game
     * @return the requested game
     */
    public Game getGame(Wrapper wrapper) {
        return wrapper.getGame();
    }

    /**
     * Two is selected as amount of players.
     *
     * @param actionEvent when button clicked
     */
    public void twoPlayers(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 2);
    }

    /**
     * Three is selected as amount of players.
     *
     * @param actionEvent when button clicked
     */
    public void threePlayers(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 3);
    }

    /**
     * Four is selected as amount of players.
     *
     * @param actionEvent when button clicked
     */
    public void fourPlayers(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 4);
    }

    /**
     * Closes the stage
     */
    @FXML
    void CloseStage() {
        System.exit(0);
    }

    /**
     * Minimizes the Window
     *
     * @param event when minimize button clicked
     */
    @FXML
    void MinimizeStage(MouseEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).setIconified(true);
    }

    /**
     * Opens a surprise
     */
    @FXML
    void eeClicked() {
        new Window("EasterEgg.fxml");
    }
}
