package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.List;


/**
 * Controller of the GUI - game result
 */
public class GameResultController {

    /**
     * AnchorPane for the second place icon
     */
    @FXML
    private AnchorPane first;

    /**
     * Labels for the first player
     */
    @FXML
    private Label player1;
    @FXML
    private Label score1;

    /**
     * AnchorPane for the second place icon
     */
    @FXML
    private AnchorPane second;

    /**
     * Labels for the second player
     */
    @FXML
    private Label player2;
    @FXML
    private Label score2;

    /**
     * AnchorPane for the third place icon
     */
    @FXML
    private AnchorPane third;

    /**
     * Labels for the third player
     */
    @FXML
    private Label player3;
    @FXML
    private Label score3;

    /**
     * AnchorPane for the fourth place icon
     */
    @FXML
    private AnchorPane fourth;

    /**
     * Labels for the fourth player
     */
    @FXML
    private Label player4;
    @FXML
    private Label score4;

    /**
     * Label for winning message
     */
    @FXML
    private Label message;

    /**
     * Game Over Image when loosing a game
     */
    @FXML
    private AnchorPane GameOver;

    /**
     * Labels for the header
     */
    @FXML
    private Label PlaceHeader;
    @FXML
    private Label PointsHeader;
    @FXML
    private Label PlayerNameHeader;

    /**
     * Shows the game results in a sorted list, that starts with the winning
     * player/s.
     */
    @FXML
    public void initialize() {
        setLabels();
        Game game = Wrapper.getInstance().getGame();

        List<String> winningPlayers = game.getPlayerList().winningPlayersToString();
        int highScore =
                game.getPlayerList().getHighestScore();

        game.getDatabase().updateHighScoreHistory(winningPlayers, highScore);
        game.updateGamesPlayed();
        game.getDatabase().storeProgress(game.getPlayerList());

        if (game.isGameWon()) {
            message.setText(printGameSummary(winningPlayers, highScore));
        } else {
            GameOver.setVisible(true);
            PlaceHeader.setVisible(false);
            PointsHeader.setVisible(false);
            PlayerNameHeader.setVisible(false);
            first.setVisible(false);
            score1.setVisible(false);
            player1.setVisible(false);
            AudioClip gameOver = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/GameOver.wav").toUri().toString());
            gameOver.play();
        }
    }

    /**
     * Sets the labels.
     */
    private void setLabels() {
        Game game = Wrapper.getInstance().getGame();
        String[] input = Wrapper.getInstance().getGame().getPlayerList().getSortedPlayerList();

        Label[] playerLabels = {player1, player2, player3, player4};
        Label[] scoreLabels = {score1, score2, score3, score4};
        AnchorPane[] icons = {first, second, third, fourth};

        for (int i = 0; i < game.getPlayerAmount(); i++) {
            playerLabels[i].setVisible(true);
            playerLabels[i].setText(input[i]);
            scoreLabels[i].setVisible(true);
            scoreLabels[i].setText(input[game.getPlayerList().size() + i]);
            icons[i].setVisible(true);
        }
    }

    /**
     * Creates the game summary and returns it.
     *
     * @param winningPlayers of the current game
     * @param highScore      the highest score a player reached
     * @return the game summary
     */
    private String printGameSummary(List<String> winningPlayers, int highScore) {
        if (winningPlayers.size() > 1) {
            return "The game ended in a tie";
        } else {
            return winningPlayers.get(0) + " won the game with a score of " + highScore;
        }
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


    /**
     * Button back to main menu.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void menu(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }

    /**
     * Restarts the current game.
     *
     * @param actionEvent when button clicked
     */
    @FXML
    public void playAgain(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        Wrapper.getInstance().getGame().getPlayerList().resetAllScores();
        new Window("Game.fxml");
    }

}