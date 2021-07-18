package de.uni_passau.se.memory.controller;

import de.uni_passau.se.memory.model.Database;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.List;

/**
 * Controller of the {@code HighScoreHistory}.
 */
public class HighScoreController {

    /**
     * Labels for the first player.
     */
    @FXML
    private Label name1;
    @FXML
    private Label score1;

    /**
     * Labels for the second player.
     */
    @FXML
    private Label name2;
    @FXML
    private Label score2;

    /**
     * Labels for the third player.
     */
    @FXML
    private Label name3;
    @FXML
    private Label score3;

    /**
     * Labels for the fourth player.
     */
    @FXML
    private Label name4;
    @FXML
    private Label score4;

    /**
     * Labels for the fifth player.
     */
    @FXML
    private Label name5;
    @FXML
    private Label score5;

    /**
     * Labels for the sixth player.
     */
    @FXML
    private Label name6;
    @FXML
    private Label score6;

    /**
     * Labels for the seventh player.
     */
    @FXML
    private Label name7;
    @FXML
    private Label score7;

    /**
     * Labels for the eighth player.
     */
    @FXML
    private Label name8;
    @FXML
    private Label score8;

    /**
     * Labels for the ninth player.
     */
    @FXML
    private Label name9;
    @FXML
    private Label score9;

    /**
     * Labels for the tenth player.
     */
    @FXML
    private Label name10;
    @FXML
    private Label score10;

    /**
     * Shows the current {@code HighScoreHistory}.
     */
    @FXML
    public void initialize() {
        Database highScoreHistory = Database.getInstance();
        loadHighScoreHistory(Database.getInstance());
        List<String[]> highScoreList = getHighScoreHistory(highScoreHistory);

        Label[] names = {name1, name2, name3, name4, name5, name6, name7, name8, name9, name10};
        Label[] scores = {score1, score2, score3, score4, score5, score6, score7, score8, score9, score10};

        for (int i = 0; i < names.length; i++) {
            names[i].setText(highScoreList.get(i)[0]);
            scores[i].setText(highScoreList.get(i)[1]);
        }
    }

    /**
     * Gets the highScoreHistory.
     *
     * @param database contains the highScoreHistory
     * @return highScoreHistory of the given database
     */
    public List<String[]> getHighScoreHistory(Database database) {
        return database.getHighScoreHistory();
    }

    /**
     * Load the highScoreHistory of a given database.
     *
     * @param database which highScoreHistory is loaded.
     */
    public void loadHighScoreHistory(Database database) {
        database.loadHighScoreHistory();
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

    /**
     * Brings you back to main menu.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void back(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("StartScreen.fxml");
    }
}
