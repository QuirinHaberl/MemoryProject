package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Database;
import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Controller of the game result
 */
public class GameResultController {

    /**
     * AnchorPane for the second place icon
     */
    @FXML
    private AnchorPane first;

    /**
     * Label for the first player's name
     */
    @FXML
    private Label player1;

    /**
     * Label for the first player's score
     */
    @FXML
    private Label score1;

    /**
     * AnchorPane for the second place icon
     */
    @FXML
    private AnchorPane second;

    /**
     * Label for the second player's name
     */
    @FXML
    private Label player2;

    /**
     * Label for the second player's score
     */
    @FXML
    private Label score2;

    /**
     * AnchorPane for the third place icon
     */
    @FXML
    private AnchorPane third;

    /**
     * Label for the third player's name
     */
    @FXML
    private Label player3;

    /**
     * Label for the third player's score
     */
    @FXML
    private Label score3;

    /**
     * AnchorPane for the fourth place icon
     */
    @FXML
    private AnchorPane fourth;

    /**
     * Label for the fourth player's name
     */
    @FXML
    private Label player4;

    /**
     * Label for the fourth player's score
     */
    @FXML
    private Label score4;

    /**
     * Label for winning message
     */
    @FXML
    private Label message;

    /**
     * Shows the game results in a sorted list, that starts with the winning
     * player/s.
     */
    @FXML
    public void initialize () {
        setLabels();
        Game game = Wrapper.getInstance().getGame();
        if (game.isGameWon()) {
                String[] winningPlayers = game.getPlayerList().winningPlayersToString();
            int[] highScore =
                    game.getPlayerList().getHighestScore();
            message.setText(printGameSummary(winningPlayers, highScore[0]));
        }
    }

    /**
     * Brings you back to main menu.
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
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        Wrapper.getInstance().getGame().getPlayerList().resetAllScores();
        new Window("Game.fxml");
    }

    /**
     * Sets the labels.
     */
    private void setLabels() {
        Game game = Wrapper.getInstance().getGame();
        String[] input = Wrapper.getInstance().getGame().getPlayerList().getSorted();

        Label[] playerLabels = {player1, player2, player3, player4};
        Label[] scoreLabels = {score1, score2, score3, score4};
        AnchorPane[] icons = {first, second, third, fourth};

        for (int i = 0; i < game.getPlayerAmount(); i++) {
            playerLabels[i].setVisible(true);
            playerLabels[i].setText(input[i]);
            scoreLabels[i].setVisible(true);
            scoreLabels[i].setText(input[game.getPlayerList().getCount()+i]);
            icons[i].setVisible(true);
        }
    }

    /**
     * Creates the game summary and returns it.
     *
     * @param winningPlayers    of the current game
     * @param highScore         the highest score a player reached
     * @return                  the game summary
     */
    private String printGameSummary(String[] winningPlayers, int highScore) {
        if (winningPlayers.length > 1) {
            return "The game ended in a tie";
        } else {
            return winningPlayers[0] + " won the game with a score of " + highScore;
        }
    }
}
