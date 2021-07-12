package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.PlayingField;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * Controller of the GUI levels window.
 */
public class LevelsController {

    /**
     * Board sizes of the different levels
     */
    public static final int SIZE_LEVEL_1 = 4;
    public static final int SIZE_LEVEL_2 = 6;
    public static final int SIZE_LEVEL_3 = 8;

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

    /**
     * Level one is selected (16 cards on the board).
     *
     * @param actionEvent when button clicked
     */
    public void level1(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get(
                "src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectLevel(actionEvent, SIZE_LEVEL_1);
    }

    /**
     * Level two is selected (36 cards on the board).
     *
     * @param actionEvent when button clicked
     */
    public void level2(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get(
                "src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectLevel(actionEvent, SIZE_LEVEL_2);
    }

    /**
     * Level three is selected (64 cards on the board).
     *
     * @param actionEvent when button clicked
     */
    public void level3(ActionEvent actionEvent) {
        AudioClip click = new AudioClip(Paths.get(
                "src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectLevel(actionEvent, SIZE_LEVEL_3);
    }

    /**
     * Sets the board size to the selected level and forwards to the next
     * window.
     *
     * @param actionEvent when button clicked
     * @param size        of the current board
     */
    private void selectLevel(ActionEvent actionEvent, int size) {
        setPlayingFieldBoardSize(getGamePlayingField(getGame(Wrapper.getInstance())), size);
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Game.fxml");
    }

    /**
     * Set the size of a given playingField.
     *
     * @param playingField whose size is set
     * @param size         to be set
     */
    public void setPlayingFieldBoardSize(PlayingField playingField, int size) {
        playingField.setBoard(size);
    }

    /**
     * Get the playingField of a game.
     *
     * @param game contains the playingField
     * @return the requested playingField
     */
    public PlayingField getGamePlayingField(Game game) {
        return game.getPlayingField();
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

}
