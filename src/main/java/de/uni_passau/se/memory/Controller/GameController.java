package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.*;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
import de.uni_passau.se.memory.Model.Enums.TurnStatus;
import de.uni_passau.se.memory.gui.Window;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Paths;

/**
 * Controller of the GUI-game.
 */
public class GameController {

    /**
     * Used to calculate the length of a second.
     */
    public static final int ONE_SECOND = 1000;
    /**
     * Used to set the lives for level1.
     */
    public static final int AMOUNT_OF_LIVES_FOR_LEVEL_1 = 4;
    /**
     * Used to set the lives for level2.
     */
    public static final int AMOUNT_OF_LIVES_FOR_LEVEL_2 = 9;
    /**
     * Used to set the lives for level3.
     */
    public static final int AMOUNT_OF_LIVES_FOR_LEVEL_3 = 15;
    /**
     * Used to determine the boardSize for level1.
     */
    public static final int SIZE_LEVEL_1 = 4;
    /**
     * Used to determine the boardSize for level2.
     */
    public static final int SIZE_LEVEL_2 = 6;
    /**
     * Used to determine the boardSize for level3.
     */
    public static final int SIZE_LEVEL_3 = 8;
    /**
     * Max height of achievementLabel.
     */
    private static final double MAX_TEXT_HEIGHT = 55;
    /**
     * Stores the default fontSize for the achievement label, since
     * the fontSize of this label is calculated dynamically.
     */
    private static final double DEFAULT_FONT_SIZE = 18;
    /**
     * The default-font used.
     */
    private static final Font DEFAULT_FONT = Font.font("VT323", DEFAULT_FONT_SIZE);
    /**
     * Time settings for different levels
     */
    private static final int TIME_LEVEL_1 = 120;
    private static final int TIME_LEVEL_2 = 240;
    private static final int TIME_LEVEL_3 = 360;
    /**
     * Stores the current game.
     */
    private final Game game;
    /**
     * Stores the current playingField of a game.
     */
    private final PlayingField playingField;
    /**
     * Stores the size of the current board.
     */
    private final int size;
    /**
     * Stores the current playerList of a game.
     */
    private final PlayerList playerList;
    /**
     * Stores the active player.
     */
    private final Achievements activePlayerAchievements;
    /**
     * Stores the active player.
     */
    private Player activePlayer;
    /**
     * To store the revealed cards
     */
    private Card firstCard;
    private Card secondCard;
    /**
     * Indicator of the first card
     */
    private int firstRow;
    private int firstCol;
    /**
     * Indicator of the second card
     */
    private int secondRow;
    private int secondCol;
    /**
     * The countDown for the play with time
     */
    private CountDownGUI countDown;
    /**
     * Board to store all buttons of the cards
     */
    @FXML
    private GridPane Board;
    /**
     * Button of the currently revealed cards
     */
    private Button b1;
    private Button b2;
    /**
     * Label for achievements
     */
    @FXML
    private Label achievementLabel;
    /**
     * Indicates of the first player
     */
    @FXML
    private AnchorPane key1;
    @FXML
    private Label labelPlayer1;
    @FXML
    private Label labelScore1;
    /**
     * Indicates of the second player
     */
    @FXML
    private AnchorPane key2;
    @FXML
    private Label labelPlayer2;
    @FXML
    private Label labelScore2;
    /**
     * Indicates of the third player
     */
    @FXML
    private AnchorPane key3;
    @FXML
    private Label labelPlayer3;
    @FXML
    private Label labelScore3;
    /**
     * Indicates of the fourth player
     */
    @FXML
    private AnchorPane key4;
    @FXML
    private Label labelPlayer4;
    @FXML
    private Label labelScore4;
    /**
     * HBox to store lives or the time
     */
    @FXML
    private HBox lives;
    /**
     * Button to got back to the main menu
     */
    @FXML
    private Button MainMenuButton;
    /**
     * Button to try the same Game again
     */
    @FXML
    private Button TryAgainButton;

    /**
     * Constructs a new GameController.
     */
    public GameController() {
        this.game = Wrapper.getGame();
        this.playerList = game.getPlayerList();
        this.playingField = game.getPlayingField();
        this.size = playingField.getSize();
        this.activePlayer = playerList.getFront();
        this.activePlayerAchievements = activePlayer.getAchievements();
    }

    /**
     * Generates new buttons.
     *
     * @param id  of the new button
     * @param row of position of the new button
     * @param col of position of the new button
     * @return the new button
     */
    public Button generateButton(String id, int row, int col) {
        Button button = new Button();
        String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
        button.getStyleClass().clear();
        button.getStylesheets().add(css);
        button.getStyleClass().add("Card");
        Image image = new Image(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Images/Cursor.png").toUri().toString());
        button.setCursor(new ImageCursor(image));
        button.setId(id);

        button.setOnAction(event -> buttonClicked(event, button, row, col));
        return button;
    }

    /**
     * Initialises the Game and the labels
     */
    @FXML
    public void initialize() {
        setPlayerLabel();
        initiateGame();
        resetAllAchievements();
        if (game.getPlayerAmount() == 1) {
            if (game.getSinglePlayerMode().equals(SinglePlayerMode.LIFEPOINTS)) {
                setLives();
            } else {
                if (size == SIZE_LEVEL_1) {
                    countDown = new CountDownGUI(TIME_LEVEL_1);
                } else if (size == SIZE_LEVEL_2) {
                    countDown = new CountDownGUI(TIME_LEVEL_2);
                } else if (size == SIZE_LEVEL_3) {
                    countDown = new CountDownGUI(TIME_LEVEL_3);
                }
            }
        }
    }

    /**
     * Starts the game.
     */
    public void initiateGame() {
        playingField.fillWithCards();

        // Filling the Board with Buttons for every card
        for (int row = 0; row < size; row++) {
            Board.getColumnConstraints().add(new ColumnConstraints(100));
            for (int col = 0; col < size; col++) {
                Board.add(generateButton("(" + row + " " + col + ")", row, col), col, row);
            }
        }
        game.setGameStatus(GameStatus.RUNNING);
        game.setTurnStatus(TurnStatus.IDLE);
    }

    /**
     * Visualizes the lives.
     */
    private void setLives() {
        int amountOfLives = getAmountOfHearts();

        for (int i = 0; i < amountOfLives; i++) {
            lives.getChildren().add(newHeart(String.valueOf(i)));
            lives.getChildren().get(i).setVisible(true);
        }

        playerList.setPlayerLives(playerList.getPlayer(1), amountOfLives * 2);
    }

    /**
     * Gets the amount of hearts.
     *
     * @return amount of hearts
     */
    private int getAmountOfHearts() {
        int amountOfLives;

        amountOfLives = switch (size) {
            case SIZE_LEVEL_1 -> AMOUNT_OF_LIVES_FOR_LEVEL_1;
            case SIZE_LEVEL_2 -> AMOUNT_OF_LIVES_FOR_LEVEL_2;
            case SIZE_LEVEL_3 -> AMOUNT_OF_LIVES_FOR_LEVEL_3;
            default -> throw new IllegalArgumentException();
        };

        return amountOfLives;
    }

    /**
     * Creates a new heart.
     *
     * @param id of the new heart
     * @return the new heart
     */
    public Node newHeart(String id) {
        AnchorPane heart = new AnchorPane();
        String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
        heart.getStyleClass().clear();
        heart.getStyleClass().add("Heart");
        heart.getStylesheets().add(css);
        heart.getStyleClass().add("LifeFull");
        heart.setId(id);

        return heart;
    }

    /**
     * Updates the lives.
     */
    private void updateLives() {
        int id = playerList.getPlayerLives(playerList.getPlayer(0));
        if ((id % 2) == 0) {
            lives.getChildren().get(id / 2).getStyleClass().removeAll(
                    "LifeEmptyHalf");
            lives.getChildren().get(id / 2).getStyleClass().add(
                    "LifeEmpty");
        } else {
            lives.getChildren().get((id - 1) / 2).getStyleClass().removeAll(
                    "LifeFull");
            lives.getChildren().get((id - 1) / 2).getStyleClass().add(
                    "LifeEmptyHalf");
        }
    }

    /**
     * Sets the player label in GUI.
     */
    public void setPlayerLabel() {
        Label[] playerLabels = {labelPlayer1, labelPlayer2,
                labelPlayer3, labelPlayer4};
        Label[] scoreLabels = {labelScore1, labelScore2,
                labelScore3, labelScore4};

        for (int i = 0; i < game.getPlayerAmount(); i++) {
            playerLabels[i].setText(playerList.getPlayerName(playerList.getPlayer(i)));

            scoreLabels[i].setVisible(true);
            scoreLabels[i].setText("Score: " + playerList.getPlayerScore(playerList.getPlayer(i)));
        }
        key1.setVisible(true);
    }

    /**
     * Performs a turn for the first revealed card.
     *
     * @param row row of the current button
     * @param col column of the current button
     */
    public void executeIdle(int row, int col) {

        firstCard = playingField.getCard(row, col);

        b1.getStyleClass().add(firstCard.getCardPicture(firstCard.getValue()));

        AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Unlock.wav").toUri().toString());
        unlock.play();

        game.setTurnStatus(TurnStatus.ACTIVE);
    }

    /**
     * Performs a turn for the second revealed card.
     *
     * @param event when button is clicked
     */
    public void executeActive(ActionEvent event) {

        secondCard = playingField.getCard(secondRow, secondCol);

        b2.getStyleClass().add(secondCard.getCardPicture(secondCard.getValue()));

        AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Unlock.wav").toUri().toString());
        unlock.play();

        //Set pointer to the next player.
        game.setTurnStatus(TurnStatus.IDLE);
        if (firstCard.getValue().equals(secondCard.getValue())) {
            activePlayer.updateScore();
            updatePlayerScores();
            AudioClip found = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Found.wav").toUri().toString());
            found.play();
            b1.setVisible(false);
            b2.setVisible(false);
        } else {
            activePlayer = activePlayer.getNext();
            activePlayerAchievements.setPairCounterStreak(0);
            updatePointer();
            if (playerList.size() == 1
                    && game.getSinglePlayerMode().equals(SinglePlayerMode.LIFEPOINTS)) {
                playerList.reducePlayerLives(playerList.getPlayer(0));
                updateLives();
                if (playerList.getPlayerLives(playerList.getPlayer(0)) == 0) {
                    game.setGameResult(false);

                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    new Window("GameResult.fxml");
                }
            }
        }
        checkIfWon(event);
    }

    /**
     * Updates the scores of all players of the current game.
     */
    public void updatePlayerScores() {
        Label[] scoreLabels = {labelScore1, labelScore2, labelScore3, labelScore4};
        for (int i = 0; i < game.getPlayerAmount(); i++) {
            scoreLabels[i].setText("Score: " + playerList.getPlayerScore(
                    playerList.getPlayer(i)));
        }
    }

    /**
     * Checks if a player has won the game.
     *
     * @param event when button clicked
     */
    public void checkIfWon(ActionEvent event) {
        if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {

            checkAchievementsDuringGame();
            if (game.areAllCardsFound()) {
                AudioClip found = new AudioClip(Paths.get("src/main/" +
                        "resources/de/uni_passau/se/memory/gui/Sound/GameWon.wav").
                        toUri().toString());
                found.play();
                game.setGameResult(true);

                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                new Window("GameResult.fxml");
            }
        }
    }

    /**
     * Gets the size of the playerList.
     *
     * @param playerList the current game
     * @return size of the playerList
     */
    public int getPlayerListSize(PlayerList playerList) {
        return playerList.size();
    }

    /**
     * Opens a card and performs a turn.
     *
     * @param event  when button clicked
     * @param button which is on action
     * @param row    row of the current button
     * @param col    column of the current button
     */
    public void buttonClicked(ActionEvent event, Button button, int row, int col) {

        // This is only for the single player mode play with time
        if (getPlayerListSize(playerList) == 1
                && game.getSinglePlayerMode().equals(SinglePlayerMode.TIME)) {
            if (countDown.getGUITime() == 0) {
                game.setGameResult(false);

                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                new Window("GameResult.fxml");
            }
            if (countDown.animation.getStatus().equals(Animation.Status.PAUSED)) {
                countDown.animation.play();
            }
        }

        //Implementation of the game phases
        switch (game.getTurnStatus()) {

            //Is used if the turn hasn't been stated yet.
            case IDLE -> {
                closeCards();

                firstRow = row;
                firstCol = col;
                b1 = button;

                CardStatus firstCardStatus = game.revealFirstCard(firstRow, firstCol);
                if (firstCardStatus.equals(CardStatus.FOUND)) {
                    break;
                }
                executeIdle(row, col);
            }

            //Is used if the turn has been stated.
            case ACTIVE -> {
                secondRow = row;
                secondCol = col;
                b2 = button;
                CardStatus secondCardStatus = game.revealSecondCard(secondRow, secondCol);
                if (secondCardStatus.equals(CardStatus.FOUND) ||
                        secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                    break;
                }
                executeActive(event);
            }
        }
    }

    /**
     * Closes the two opened cards.
     */
    public void closeCards() {
            b1.getStyleClass().clear();
            b1.getStyleClass().add("Card");
            b2.getStyleClass().clear();
            b2.getStyleClass().add("Card");
    }

    /**
     * Checks achievements for the current player during the game.
     */
    public void checkAchievementsDuringGame() {
        String achievement = game.checkForAchievementsInGame(activePlayer);
        if (!achievement.isEmpty()) {
            achievementLabel.setFont(DEFAULT_FONT);
            achievementLabel.setText(activePlayer.getName() + " has earned:\n" + achievement);

            //Automatic text size adjustment
            Text tmpText = new Text(achievementLabel.getText());
            tmpText.setFont(DEFAULT_FONT);
            double textHeight = tmpText.getLayoutBounds().getHeight();

            //Check if text height is smaller than maximum height allowed
            if (textHeight <= MAX_TEXT_HEIGHT) {
                achievementLabel.setFont(DEFAULT_FONT);
            } else {

                //Calculate new font size the achievement text doesn't fit.
                double newFontSize = DEFAULT_FONT_SIZE * MAX_TEXT_HEIGHT / textHeight;
                achievementLabel.setFont(Font.font(DEFAULT_FONT.getFamily(), newFontSize));
            }
            AudioClip unlock = new AudioClip(Paths.get("src/main/resources/" +
                    "de/uni_passau/se/memory/gui/Sound/Achievement.wav").toUri().toString());
            unlock.play();
        }
    }

    /**
     * Updates the player pointers.
     */
    private void updatePointer() {
        Label[] playerLabels = {labelPlayer1, labelPlayer2,
                labelPlayer3, labelPlayer4};
        AnchorPane[] keyAnchorPanes = {key1, key2,
                key3, key4};
        for (int i = 0; i < game.getPlayerAmount(); i++) {
            keyAnchorPanes[i].setVisible(playerLabels[i].getText().equals(activePlayer.getName()));
        }
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
     * Calls mainMenu to enable the Window to be closed.
     */
    public void menu() {
        mainMenu();
    }

    /**
     * Closes the current window and opens the Start Screen.
     */
    public void mainMenu() {
        ((Stage) (MainMenuButton.getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }

    /**
     * Executed it the tryAgain-button is clicked.
     */
    public void startNewTry() {
        tryAgainButtonClicked();
    }

    /**
     * Executed if the tryAgainButton is clicked.
     */
    public void tryAgainButtonClicked() {
        ((Stage) (TryAgainButton.getScene().getWindow())).close();
        new Window("Game.fxml");
    }

    /**
     * Closes the whole Game.
     */
    public void ExitClicked() {
        System.exit(0);
    }

    /**
     * Opens the Rules and pauses the time if there is one.
     */
    public void help() {
        if (game.getSinglePlayerMode().equals(SinglePlayerMode.TIME)) {
            countDown.pauseTimer();
        }
        new Window("Rules.fxml");
    }

    /**
     * Resets the inGame achievements of all players.
     */
    public void resetAllAchievements() {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.resetPlayerAchievements(playerList.getPlayer(i));
        }
    }

    /**
     * This is a inner class for the timer of a {@link Game} in the GUI.
     * The default time is 120 seconds.
     */
    private class CountDownGUI extends Pane {

        Label timer = new Label();
        private Timeline animation;
        private int time;

        /**
         * Initiates the countDown for the GUI.
         *
         * @param time to start the countDown
         */
        public CountDownGUI(int time) {
            this.time = time;
            setTimer(timer);
            animation = new Timeline(new KeyFrame(Duration.millis(ONE_SECOND),
                    e -> setTimeLabel()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }

        /**
         * Actualizes the time label after every second.
         */
        public void setTimeLabel() {
            if (time == 0) {
                animation = null;
            } else {
                time--;
                String s = time + "";
                timer.setText("Time: " + s);
            }
        }

        /**
         * Visualizes the timer.
         */
        private void setTimer(Label timer) {
            timer.setTextFill(Color.WHITE);
            String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
            timer.getStylesheets().add(css);
            timer.getStyleClass().clear();
            timer.getStyleClass().add("Timer");
            timer.setVisible(true);
            lives.getChildren().add(timer);
        }

        /**
         * Gets the time of the GUI timer.
         *
         * @return time
         */
        public int getGUITime() {
            return time;
        }

        /**
         * Pauses the timer.
         */
        public void pauseTimer() {
            if (playerList.size() == 1 &&
                    game.getSinglePlayerMode().equals(SinglePlayerMode.TIME)) {
                animation.pause();
            }
        }
    }
}
