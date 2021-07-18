package de.uni_passau.se.memory.controller;

import de.uni_passau.se.memory.model.Game;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * Controller of the single player mode.
 */
public class SinglePlayerModeController {

    /**
     * Sets SinglePlayerMode to LIFEPOINTS.
     *
     * @param actionEvent when button clicked
     */
    public void lives(ActionEvent actionEvent) {
        setGamePlayerAmount(getGame(), 1);
        setGameSinglePlayerMode(getGame(),
                SinglePlayerMode.LIFEPOINTS);
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("NameSelection.fxml");
    }

    /**
     * Sets the singlePlayerMode of a given game.
     *
     * @param game             whose singlePlayerMode is set
     * @param singlePlayerMode to be set
     */
    public void setGameSinglePlayerMode(Game game,
                                        SinglePlayerMode singlePlayerMode) {
        game.setSinglePlayerMode(singlePlayerMode);
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
     * Sets SinglePlayerMode to TIME.
     *
     * @param actionEvent when button clicked
     */
    public void time(ActionEvent actionEvent) {
        setGamePlayerAmount(getGame(), 1);
        setGameSinglePlayerMode(getGame(),
                SinglePlayerMode.TIME);
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("NameSelection.fxml");
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
