package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.CardValues;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
import de.uni_passau.se.memory.Model.Enums.TurnStatus;
import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.Player;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Controller of the game.
 */
public class GameController {

    @FXML
    public Button start;

    /**
     * Label for achievements
     */
    @FXML
    private Label achievementLabel;

    /**
     * AnchorPane to indicate that it is the first player's turn
     */
    @FXML
    private AnchorPane key1;

    /**
     * Label for the first player's name
     */
    @FXML
    private Label labelPlayer1;

    /**
     * Label for the first player's score
     */
    @FXML
    private Label labelScore1;

    /**
     * AnchorPane to indicate that it is the second player's turn
     */
    @FXML
    private AnchorPane key2;

    /**
     * Label for the second player's name
     */
    @FXML
    private Label labelPlayer2;

    /**
     * Label for the second player's score
     */
    @FXML
    private Label labelScore2;

    /**
     * AnchorPane to indicate that it is the third player's turn
     */
    @FXML
    private AnchorPane key3;

    /**
     * Label for the third player's name
     */
    @FXML
    private Label labelPlayer3;

    /**
     * Label for the third player's score
     */
    @FXML
    private Label labelScore3;

    /**
     * AnchorPane to indicate that it is the fourth player's turn
     */
    @FXML
    private AnchorPane key4;

    /**
     * Label for the fourth player's name
     */
    @FXML
    private Label labelPlayer4;

    /**
     * Label for the fourth player's score
     */
    @FXML
    private Label labelScore4;

    /**
     * Board to store all buttons of the cards
     */
    @FXML
    private GridPane Board;

    /**
     * HBox to store lives or timer
     */
    @FXML
    private HBox livesAndTime;

    /**
     * To store the first revealed card
     */
    Object firstCard;

    /**
     * To store the second revealed card
     */
    Object secondCard;

    /**
     * Button of the currently first revealed card
     */
    private Button b1;

    /**
     * Button of the currently second revealed card
     */
    private Button b2;

    /**
     * Indicator of the first cards row
     */
    int firstRow;

    /**
     * Indicator of the first cards column
     */
    int firstCol;

    /**
     * Indicator of the second cards row
     */
    int secondRow;

    /**
     * Indicator of the second cards column
     */
    int secondCol;

    /**
     * Stores the current game
     */
    Game game = Wrapper.getInstance().getGame();

    /**
     * Stores the player whose turn it is
     */
    Player activePlayer = game.getPlayerList().getFront();

    /**
     * Stores the size of the current {@link de.uni_passau.se.memory.Model.PlayingField}
     */
    int size = game.getPlayingField().getSize();

    public GameController() {
    }

    /**
     * Shows the current {@link GameController}
     */
    @FXML
    public void initialize() {
        setPlayerLabel();
        startClicked();
        if (game.getPlayerAmount() == 1) {
            if (game.getSinglePlayerMode().equals(SinglePlayerMode.LIFEPOINTS)) {
                setLives();
            } else {
                setTimer();
                game.startTimer();
            }
        }
    }

    /**
     * Starts the game.
     * TODO check ob gebraucht
     */
    public void startClicked() {
        int size = game.getPlayingField().getSize();
        game.getPlayingField().fillWithCards();

        // Filling the Board with Buttons for every card
        for (int row = 0; row < size; row++) {
            Board.getColumnConstraints().add(new ColumnConstraints(100));
            for (int col = 0; col < size; col++) {
                Board.add(newButton("(" + row + " " + col + ")", row, col), col, row);
            }
        }
        game.setGameStatus(GameStatus.RUNNING);
        game.setTurnStatus(TurnStatus.IDLE);
    }

    /**
     * Filling the Stage with Objects
     *
     * @param id    of the new button
     * @param row   of position of the new button
     * @param col   of position of the new button
     * @return      the new button
     */
    public Node newButton(String id, int row, int col) {
        Button button = new Button();
        button.setPrefSize(100, 70);
        String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
        button.getStylesheets().add(css);
        button.getStyleClass().add("Card");
        Image image = new Image(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Images/Cursor.png").toUri().toString());
        button.setCursor(new ImageCursor(image));
        button.setId(id);



        //TODO das hier sieht nicht gut aus :c
        button.setOnAction(event -> buttonClicked(event, button, id, row, col));
        return button;
    }

    /**
     * The other actions
     * TODO Game muss auch geschlossen werden wenn im Menü --> Main Menu asgewählt wird
     */
    public void menu(ActionEvent actionEvent) {

        new Window("StartScreen.fxml");

    }

    /**
     * TODO erneutes laden muss noch geschehen
     *
     * @param actionEvent
     */
    public void TryAgainClicked(ActionEvent actionEvent) {
        new Window("Game.fxml");
    }

    public void ExitClicked(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void help(ActionEvent actionEvent) {
        new Window("Rules.fxml");
    }

    /**
     * Makes the information about the current players visible.
     */
    public void setPlayerLabel() {
        Label[] playerLabels = {labelPlayer1, labelPlayer2,
                labelPlayer3, labelPlayer4};
        Label[] scoreLabels = {labelScore1, labelScore2,
                labelScore3, labelScore4};

        for (int i = 0; i < game.
                getPlayerAmount(); i++) {
            playerLabels[i].setText(game.getPlayerList().getPlayer(i).getName());
            scoreLabels[i].setVisible(true);
            scoreLabels[i].setText("Score: " + game.getPlayerList().getPlayer(i).getScore());
        }
        key1.setVisible(true);
    }

    /**
     * Updates the scores of all players of the current game.
     */
    public void updateAllScores() {
        Label[] scoreLabels = {labelScore1, labelScore2, labelScore3, labelScore4};
        for (int i = 0; i < game.getPlayerAmount(); i++) {
            scoreLabels[i].setText("Score: " + game.getPlayerList().getPlayer(i).getScore());
        }
    }

    /**
     * Opens a card and performs a turn.
     *
     * @param event     when button clicked
     * @param button    which is on action
     * @param id        id of the current button
     * @param row       row of the current button
     * @param col       column of the current button
     */
    public void buttonClicked(ActionEvent event, Button button, String id, int row, int col) {
        if (game.getPlayerList().getCount() == 1 && game.getSinglePlayerMode().equals(SinglePlayerMode.LIFEPOINTS)) {
            setLives();
        }

        if (game.getPlayerList().getCount() == 1 && game.getSinglePlayerMode().equals(SinglePlayerMode.TIME)) {
            if (game.getTime() == null) {
                game.setGameWon(false);
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                new Window("GameResult.fxml");
            } else {
                //TODO timer aktuallieseren
            }
        }

        //Implementation of the game phases
        switch (game.getTurnStatus()) {

            //Is used if the turn hasn't been stated yet.
            case IDLE:
                firstRow = row;
                firstCol = col;
                CardStatus firstCardStatus = game.revealFirstCard(firstRow, firstCol);
                if (firstCardStatus.equals(CardStatus.FOUND)) {
                    break;
                }
                executeIdle(button, id, row, col);
                break;

            //Is used if the turn has been stated.
            case ACTIVE:
                secondRow = row;
                secondCol = col;
                CardStatus secondCardStatus = game.revealSecondCard(secondRow, secondCol);
                if (secondCardStatus.equals(CardStatus.FOUND) ||
                        secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                    break;
                }
                executeActive(button, id, row, col, event);
                break;
        }
    }

    /**
     * Performs a turn for a first revealed card.
     *
     * @param button    which is on action
     * @param id        id of the current button
     * @param row       row of the current button
     * @param col       column of the current button
     */
    public void executeIdle(Button button, String id, int row, int col) {

        closeCards();

        b1 = button;
        firstCard = game.getCard(row, col).getValue();

        b1.getStyleClass().removeAll("Card");
        b1.getStyleClass().add(((CardValues) firstCard).getPicture());

        AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Unlock.wav").toUri().toString());
        unlock.play();

        game.setTurnStatus(TurnStatus.ACTIVE);
    }

    /**
     * Performs a turn for a second revealed card.
     *
     * @param button    which is on action
     * @param id        id of the current button
     * @param row       row of the current button
     * @param col       column of the current button
     */
    public void executeActive(Button button, String id, int row, int col,
                              ActionEvent event) {

        b2 = button;

        secondCard = game.getCard(secondRow, secondCol).getValue();

        b2.getStyleClass().removeAll("Card");
        b2.getStyleClass().add(((CardValues) secondCard).getPicture());

        AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Unlock.wav").toUri().toString());
        unlock.play();
        checkIfWon(event);


        //Setzte Pointer auf nächsten
        game.setTurnStatus(TurnStatus.IDLE);
        if (firstCard.equals(secondCard)) {
            activePlayer.updateScore();

            //Ineffizient, funktioniert aber :3
            updateAllScores();
            AudioClip found = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Found.wav").toUri().toString());
            found.play();
            b1.setVisible(false);
            b2.setVisible(false);
        } else {
            activePlayer.getAchievements().resetPairCounterStreak();
            activePlayer = activePlayer.getNext();
            if (game.getPlayerList().getCount() == 1
                    && game.getSinglePlayerMode().equals(SinglePlayerMode.LIFEPOINTS)) {
                game.getPlayerList().getPlayer(0).reducelifes();
                updateLives();
                if (game.getPlayerList().getPlayer(0).getlifes() == 0) {
                    game.setGameWon(false);
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    new Window("GameResult.fxml");
                }
            }
        }

    }

    /**
     * Checks if a player has won the game.
     */
    public void checkIfWon(ActionEvent event) {
        if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {

            checkAchievementsDuringGame();
            if (game.areAllCardsOpen()) {
                //TODO
                AudioClip found = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/GameWon.wav").toUri().toString());
                found.play();
                game.setGameWon(true);

                checkAchievementsAfterGame();

                game.storeProgress();

                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                new Window("GameResult.fxml");
            }
        }
    }

    /**
     * Checks achievements for the current player during the game.
     */
    public void checkAchievementsDuringGame() {
        String achievement = game.checkForAchievements(activePlayer);
        if (!achievement.isEmpty()) {
            achievementLabel.setStyle("-fx-font-size: 15pt;");
            achievementLabel.setText(activePlayer.getName() + " has earned:\n" + achievement);
            AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Achievement.wav").toUri().toString());
            unlock.play();
        }
    }

    /**
     * Checks achievements for all players after game.
     */
    public void checkAchievementsAfterGame() {
        String achievement = game.checkForAchievements(game.getPlayerList());
        if (!achievement.isEmpty()) {
            achievementLabel.setStyle("-fx-font-size: 15pt;");
            achievementLabel.setText(achievement);
            AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Achievement.wav").toUri().toString());
            unlock.play();
        }
    }

    /**
     * Closes the two opened cards.
     */
    public void closeCards() {
        try {
            b1.getStyleClass().clear();
            b1.getStyleClass().add("Card");
            b2.getStyleClass().clear();
            b2.getStyleClass().add("Card");
        } catch (NullPointerException e) {
            //Die Buttons wurden noch nicht geklickt und sind deswegen leer.
        }
    }

    /**
     * Visualizes the lives.
     */
    private void setLives () {
        for (int i = 0; i < game.getBoard().length; i++) {
            livesAndTime.getChildren().add(newHeart(String.valueOf(i)));
            livesAndTime.getChildren().get(i).setVisible(true);
        }
        game.getPlayerList().getPlayer(0).setlifes(game.getBoard().length * 2);
    }

    /**
     * Creates a new heart.
     *
     * @param id of the new heart
     * @return   the new heart
     */
    public Node newHeart(String id) {
        AnchorPane heart = new AnchorPane();
        heart.setPrefSize(40, 40);
        String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
        heart.getStylesheets().add(css);
        heart.getStyleClass().add("LifeFull");
        //Image image = new Image(Paths.get("src/main/resources/de/uni_passau"
        //+ "/se/memory/gui/Images/Cursor.png").toUri().toString());
        heart.setId(id);

        return heart;
    }

    /**
     * Updates the lives.
     */
    private void updateLives() {
        int id = game.getPlayerList().getPlayer(0).getlifes();
        if ((id % 2) == 0) {
            livesAndTime.getChildren().get(id/2).getStyleClass().removeAll(
                    "LifeFull");
            livesAndTime.getChildren().get(id/2).getStyleClass().add(
                    "LifeEmpty");
        } else {
            livesAndTime.getChildren().get((id+1)/2).getStyleClass().removeAll(
                    "LifeFull");
            //TODO add LifeHalfFull
            livesAndTime.getChildren().get((id+1)/2).getStyleClass().add(
                    "LifeHalfFull");
        }
    }

    /**
     * Visualizes the timer.
     */
    private void setTimer () {
        Label timer = new Label();
        timer.setPrefSize(120, 40);
        String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
        timer.getStylesheets().add(css);
        timer.getStyleClass().add("text15");
        timer.setVisible(true);
        livesAndTime.getChildren().add(timer);
    }
}

