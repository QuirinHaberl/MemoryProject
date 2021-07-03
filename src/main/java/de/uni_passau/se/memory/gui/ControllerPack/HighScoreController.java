package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.HighScoreHistory;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controller of the {@link HighScoreHistory}.
 */
public class HighScoreController {

    /**
     * HBox for the first player and his high score
     */
    @FXML
    private HBox box1;

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
     * HBox for the second player and his high score
     */
    @FXML
    private HBox box2;

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
     * HBox for the third player and his high score
     */
    @FXML
    private HBox box3;

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
     * HBox for the fourth player and his high score
     */
    @FXML
    private HBox box4;

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
     * HBox for the fifth player and his high score
     */
    @FXML
    private HBox box5;

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
     * HBox for the sixth player and his high score
     */
    @FXML
    private HBox box6;

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
     * HBox for the seventh player and his high score
     */
    @FXML
    private HBox box7;

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
     * HBox for the eighth player and his high score
     */
    @FXML
    private HBox box8;

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
     * HBox for the ninth player and his high score
     */
    @FXML
    private HBox box9;

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
     * HBox for the tenth player and his high score
     */
    @FXML
    private HBox box10;

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
     * Shows the current {@link HighScoreHistory}.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    //TODO Bisher noch keine Möglichkeit gefunden bei der Erstellung bereits
    // die Daten korrekt anzuzeigen
    private void show (ActionEvent actionEvent) {
        HighScoreHistory highScoreHistory = HighScoreHistory.getInstance();
        highScoreHistory.loadHighScoreHistory();
        List<String[]> highScoreList = highScoreHistory.getHighScoreHistory();
        name1.setText(highScoreList.get(0)[0]);
        score1.setText(highScoreList.get(0)[1]);
        box1.setVisible(true);
        name2.setText(highScoreList.get(1)[0]);
        score2.setText(highScoreList.get(1)[1]);
        box2.setVisible(true);
        name3.setText(highScoreList.get(2)[0]);
        score3.setText(highScoreList.get(2)[1]);
        box3.setVisible(true);
        name4.setText(highScoreList.get(3)[0]);
        score4.setText(highScoreList.get(3)[1]);
        box4.setVisible(true);
        name5.setText(highScoreList.get(4)[0]);
        score5.setText(highScoreList.get(4)[1]);
        box5.setVisible(true);
        name6.setText(highScoreList.get(5)[0]);
        score6.setText(highScoreList.get(5)[1]);
        box6.setVisible(true);
        name7.setText(highScoreList.get(6)[0]);
        score7.setText(highScoreList.get(6)[1]);
        box7.setVisible(true);
        name8.setText(highScoreList.get(7)[0]);
        score8.setText(highScoreList.get(7)[1]);
        box8.setVisible(true);
        name9.setText(highScoreList.get(8)[0]);
        score9.setText(highScoreList.get(8)[1]);
        box9.setVisible(true);
        name10.setText(highScoreList.get(9)[0]);
        score10.setText(highScoreList.get(9)[1]);
        box10.setVisible(true);

    }

    /**
     * Brings you back to main menu.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void back(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }
}
