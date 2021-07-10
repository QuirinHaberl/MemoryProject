package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;

/**
 * Controller of the levels window.
 */
public class LevelsController {

    /**
     * Closes the stage
     * @param event when close button clicked
     */
    @FXML
    void CloseStage(MouseEvent event) {
        System.exit(0);
    }

    /**
     * Minimizes the Window
     * @param event when minimize button clicked
     */
    @FXML
    void MinimizeStage(MouseEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).setIconified(true);
    }

    /**
     * Opens a surprise
     * @param event when logo ist clicked
     */
    @FXML
    void eeClicked(ActionEvent event) {
        new Window("EasterEgg.fxml");
    }

    /**
     * Level one is selected (16 cards on the board).
     *
     * @param actionEvent   when button clicked
     */
    public void level1(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectLevel(actionEvent, 4);
    }

    /**
     * Level two is selected (36 cards on the board).
     *
     * @param actionEvent   when button clicked
     */
    public void level2(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectLevel(actionEvent, 6);
    }

    /**
     * Level three is selected (64 cards on the board).
     *
     * @param actionEvent   when button clicked
     */
    public void level3(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectLevel(actionEvent, 8);
    }

    /**
     * Sets the board size to the selected level and forwards to the next
     * window.
     *
     * @param actionEvent   when button clicked
     * @param size          of the current board
     */
    private void selectLevel(ActionEvent actionEvent, int size) {
        Wrapper.getInstance().getGame().getPlayingField().setBoard(size);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Game.fxml");
    }


}
