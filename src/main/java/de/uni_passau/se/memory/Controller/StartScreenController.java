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
 * Controller of the start screen window.
 */
public class StartScreenController {

    /**
     * Sets player mode on single player mode and forwards to the next window.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void singlePlayer(ActionEvent actionEvent) {
        Wrapper.getInstance().getGame().setPlayerAmount(1);
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click=new AudioClip(Paths.get(
                "src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("SinglePlayerMode.fxml");
    }

    /**
     * Sets player mode on multi player mode and forwards to the next window.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void multiPlayer(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click=new AudioClip(Paths.get(
                "src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("NumberOfPlayers.fxml");
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
     * Opens the high score window.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void highscore(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click=new AudioClip(Paths.get(
                "src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("HighScore.fxml");
    }

    /**
     * Quits the whole game.
     *
     */
    public void quit() {
        System.exit(0);
    }
}
