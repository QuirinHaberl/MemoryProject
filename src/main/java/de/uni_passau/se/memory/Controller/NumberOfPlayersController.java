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
     * Amount of two players.
     */
    private static final int TWO_PLAYERS = 2;
    /**
     * Amount of three players.
     */
    private static final int THREE_PLAYERS = 3;
    /**
     * Amount of four players.
     */
    private static final int FOUR_PLAYERS = 4;

    /**
     * Sets the player amount to the passed number and forwards to the next
     * window.
     *
     * @param actionEvent when button clicked
     * @param number      of players for the currently configured game
     */
    private void selectPlayers(ActionEvent actionEvent, int number) {
        setGamePlayerAmount(Wrapper.getGame(), number);
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
     * @return the requested game
     */
    public Game getGame() {
        return Wrapper.getGame();
    }

    /**
     * Two is selected as amount of players.
     *
     * @param actionEvent when button clicked
     */
    public void twoPlayers(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, TWO_PLAYERS);
    }

    /**
     * Three is selected as amount of players.
     *
     * @param actionEvent when button clicked
     */
    public void threePlayers(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, THREE_PLAYERS);
    }

    /**
     * Four is selected as amount of players.
     *
     * @param actionEvent when button clicked
     */
    public void fourPlayers(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, FOUR_PLAYERS);
    }

    /**
     * Closes the stage.
     */
    @FXML
    void CloseStage() {
        System.exit(0);
    }

    /**
     * Minimizes the Window.
     *
     * @param event when minimize button clicked
     */
    @FXML
    void MinimizeStage(MouseEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).setIconified(true);
    }

    /**
     * Opens a surprise.
     */
    @FXML
    void eeClicked() {
        new Window("EasterEgg.fxml");
    }
}
