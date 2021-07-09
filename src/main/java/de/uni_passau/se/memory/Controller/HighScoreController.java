package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Database;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.List;

/**
 * Controller of the {@code HighScoreHistory}.
 */
public class HighScoreController {

    /**
     * Label for the first player
     */
    @FXML
    private Label name1;

    /**
     * Label for the high score of the first player
     */
    @FXML
    private Label score1;

    /**
     * Label for the second player
     */
    @FXML
    private Label name2;

    /**
     * Label for the high score of the second player
     */
    @FXML
    private Label score2;

    /**
     * Label for the third player
     */
    @FXML
    private Label name3;

    /**
     * Label for the high score of the third player
     */
    @FXML
    private Label score3;

    /**
     * Label for the fourth player
     */
    @FXML
    private Label name4;

    /**
     * Label for the high score of the fourth player
     */
    @FXML
    private Label score4;

    /**
     * Label for the fifth player
     */
    @FXML
    private Label name5;

    /**
     * Label for the high score of the fifth player
     */
    @FXML
    private Label score5;

    /**
     * Label for the sixth player
     */
    @FXML
    private Label name6;

    /**
     * Label for the high score of the sixth player
     */
    @FXML
    private Label score6;

    /**
     * Label for the seventh player
     */
    @FXML
    private Label name7;

    /**
     * Label for the high score of the seventh player
     */
    @FXML
    private Label score7;

    /**
     * Label for the eighth player
     */
    @FXML
    private Label name8;

    /**
     * Label for the high score of the eighth player
     */
    @FXML
    private Label score8;

    /**
     * Label for the ninth player
     */
    @FXML
    private Label name9;

    /**
     * Label for the high score of the ninth player
     */
    @FXML
    private Label score9;

    /**
     * Label for the tenth player
     */
    @FXML
    private Label name10;

    /**
     * Label for the high score of the tenth player
     */
    @FXML
    private Label score10;

    /**
     * Shows the current {@code HighScoreHistory}.
     */
    @FXML
    public void initialize () {
        Database highScoreHistory = Database.getInstance();
        highScoreHistory.loadHighScoreHistory();
        List<String[]> highScoreList = highScoreHistory.getHighScoreHistory();

        Label[] names = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10};
        Label[] scores = {score1, score2, score3, score4, score5, score6, score7, score8, score9, score10};

        for (int i = 0; i < names.length; i++) {
            names[i].setText(highScoreList.get(i)[0]);
            scores[i].setText(highScoreList.get(i)[1]);
        }
    }

    /**
     * Brings you back to main menu.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void back(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("StartScreen.fxml");
    }
}
