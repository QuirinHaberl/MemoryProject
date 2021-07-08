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
        name1.setText(highScoreList.get(0)[0]);
        score1.setText(highScoreList.get(0)[1]);
        name2.setText(highScoreList.get(1)[0]);
        score2.setText(highScoreList.get(1)[1]);
        name3.setText(highScoreList.get(2)[0]);
        score3.setText(highScoreList.get(2)[1]);
        name4.setText(highScoreList.get(3)[0]);
        score4.setText(highScoreList.get(3)[1]);
        name5.setText(highScoreList.get(4)[0]);
        score5.setText(highScoreList.get(4)[1]);
        name6.setText(highScoreList.get(5)[0]);
        score6.setText(highScoreList.get(5)[1]);
        name7.setText(highScoreList.get(6)[0]);
        score7.setText(highScoreList.get(6)[1]);
        name8.setText(highScoreList.get(7)[0]);
        score8.setText(highScoreList.get(7)[1]);
        name9.setText(highScoreList.get(8)[0]);
        score9.setText(highScoreList.get(8)[1]);
        name10.setText(highScoreList.get(9)[0]);
        score10.setText(highScoreList.get(9)[1]);
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
