package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.CardValues;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
import de.uni_passau.se.memory.Model.Enums.TurnStatus;
import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.Player;
import de.uni_passau.se.memory.Model.PlayerList;
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
     * //TODO
     */
    public static final double DEFAULT_FONT_SIZE = 18;
    public static final Font DEFAULT_FONT = Font.font("VT323", DEFAULT_FONT_SIZE);

    /**
     * Stores the current game.
     */
    Game game = Wrapper.getInstance().getGame();

    /**
     * Stores the size of the current board.
     */
    int size = game.getPlayingField().getSize();

    /**
     * Board sizes of the different levels
     */
    public static final int SIZE_LEVEL_1 = 4;
    public static final int SIZE_LEVEL_2 = 6;

    /**
     * Stores the active player.
     */
    Player activePlayer = game.getPlayerList().getFront();

    /**
     * To store the revealed cards
     */
    Object firstCard;
    Object secondCard;

    /**
     * Indicator of the first card
     */
    int firstRow;
    int firstCol;

    /**
     * Indicator of the second card
     */
    int secondRow;
    int secondCol;

    /**
     * The countDown for the play with time
     */
    CountDownGUI countDown;

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
     * Preferred dimensions for new Buttons
     */
    public static final int PREF_WIDTH_BUTTON = 100;
    public static final int PREF_HEIGHT_BUTTON = 70;

    /**
     * Label for achievements
     */
    @FXML
    private Label achievementLabel;

    /**
     * Max height of achievementLabel
     */
    public static final double MAX_TEXT_HEIGHT = 55;

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
    private HBox livesAndTime;

    /**
     * Time settings for different levels
     */
    public static final int TIME_LEVEL_1 = 120;
    public static final int TIME_LEVEL_2 = 240;
    public static final int TIME_LEVEL_3 = 360;

    /**
     * Preferred dimensions for timer
     */
    public static final int PREF_WIDTH_TIMER = 120;
    public static final int PREF_HEIGHT_TIMER = 40;

    /**
     * Definition of one second
     */
    public static final int ONE_SECOND = 1000;

    /**
     * Life amount for different levels
     */
    public static final int AMOUNT_OF_LIVES_FOR_LEVEL_1 = 4;
    public static final int AMOUNT_OF_LIVES_FOR_LEVEL_2 = 9;
    public static final int AMOUNT_OF_LIVES_FOR_LEVEL_3 = 15;

    /**
     * Preferred dimensions for Hearts
     */
    public static final int PREF_WIDTH_HEART = 40;
    public static final int PREF_HEIGHT_HEART = 40;

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
     * Filling the Stage with Objects
     *
     * @param id  of the new button
     * @param row of position of the new button
     * @param col of position of the new button
     * @return the new button
     */
    public Node newButton(String id, int row, int col) {
        Button button = new Button();
        button.setPrefSize(PREF_WIDTH_BUTTON, PREF_HEIGHT_BUTTON);
        String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
        button.getStylesheets().add(css);
        button.getStyleClass().add("Card");
        Image image = new Image(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Images/Cursor.png").toUri().toString());
        button.setCursor(new ImageCursor(image));
        button.setId(id);

        button.setOnAction(event -> buttonClicked(event, button, row, col));
        return button;
    }

    /**
     * Starts the game.
     * Fills the board with cards and does the settings in Model-Game
     */
    public void start() {
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
     * Initialises the Game and the labels
     */
    @FXML
    public void initialize() {
        setPlayerLabel();
        start();
        if (game.getPlayerAmount() == 1) {
            if (game.getSinglePlayerMode().equals(SinglePlayerMode.LIFEPOINTS)) {
                setLives();
            } else {
                if (size == SIZE_LEVEL_1) {
                    countDown = new CountDownGUI(TIME_LEVEL_1);
                } else if (size == SIZE_LEVEL_2) {
                    countDown = new CountDownGUI(TIME_LEVEL_2);
                } else countDown = new CountDownGUI(TIME_LEVEL_3);
            }
        }
    }

    /**
     * Visualizes the lives.
     */
    private void setLives() {
        int amountOfLives = getAmountOfHearts();

        for (int i = 0; i < amountOfLives; i++) {
            livesAndTime.getChildren().add(newHeart(String.valueOf(i)));
            livesAndTime.getChildren().get(i).setVisible(true);
        }
        game.getPlayerList().getPlayer(1).setLives(amountOfLives * 2);
    }

    /**
     * Getter for the amount of hearts dependent on the specific board size.
     *
     * @return amount of hearts
     */
    private int getAmountOfHearts() {
        int amountOfLives;

        amountOfLives = switch (size) {
            case SIZE_LEVEL_1 -> AMOUNT_OF_LIVES_FOR_LEVEL_1;
            case SIZE_LEVEL_2 -> AMOUNT_OF_LIVES_FOR_LEVEL_2;
            default -> AMOUNT_OF_LIVES_FOR_LEVEL_3;
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
        heart.setPrefSize(PREF_WIDTH_HEART, PREF_HEIGHT_HEART);
        String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
        heart.getStylesheets().add(css);
        heart.getStyleClass().add("LifeFull");
        heart.setId(id);

        return heart;
    }

    /**
     * Updates the lives.
     */
    private void updateLives() {
        int id = game.getPlayerList().getPlayer(0).getLives();
        if ((id % 2) == 0) {
            livesAndTime.getChildren().get(id / 2).getStyleClass().removeAll(
                    "LifeEmptyHalf");
            livesAndTime.getChildren().get(id / 2).getStyleClass().add(
                    "LifeEmpty");
        } else {
            livesAndTime.getChildren().get((id - 1) / 2).getStyleClass().removeAll(
                    "LifeFull");
            //TODO add LifeHalfFull
            livesAndTime.getChildren().get((id - 1) / 2).getStyleClass().add(
                    "LifeEmptyHalf");
        }
    }

    /**
     * Sets the player label in GUI
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
     * Performs a turn for a first revealed card.
     *
     * @param button which is on action
     * @param row    row of the current button
     * @param col    column of the current button
     */
    public void executeIdle(Button button, int row, int col) {

        closeCards();

        b1 = button;
        firstCard = game.playingField.getCard(row, col).getValue();

        b1.getStyleClass().removeAll("Card");
        b1.getStyleClass().add(((CardValues) firstCard).getPicture());

        AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Unlock.wav").toUri().toString());
        unlock.play();

        game.setTurnStatus(TurnStatus.ACTIVE);
    }

    /**
     * Performs a turn for a second revealed card.
     *
     * @param button which is on action
     * @param event when button is clicked
     */
    public void executeActive(Button button,
                              ActionEvent event) {

        b2 = button;

        secondCard = game.playingField.getCard(secondRow, secondCol).getValue();

        b2.getStyleClass().removeAll("Card");
        b2.getStyleClass().add(((CardValues) secondCard).getPicture());

        AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Unlock.wav").toUri().toString());
        unlock.play();

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
            activePlayer.getAchievements().setPairCounterStreak(0);
            activePlayer = activePlayer.getNext();
            updatePointer();
            if (game.getPlayerList().size() == 1
                    && game.getSinglePlayerMode().equals(SinglePlayerMode.LIFEPOINTS)) {
                game.getPlayerList().getPlayer(0).reduceLives();
                updateLives();
                if (game.getPlayerList().getPlayer(0).getLives() == 0) {
                    game.setGameResult(false);

                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    new Window("GameResult.fxml");
                }
            }
        }
        checkIfWon(event);

    }

    //test

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
     * Checks if a player has won the game.
     */
    public void checkIfWon(ActionEvent event) {
        if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {

            checkAchievementsDuringGame();
            if (game.areAllCardsFound()) {
                //TODO
                AudioClip found = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/GameWon.wav").toUri().toString());
                found.play();
                game.setGameResult(true);

                checkAchievementsAfterGame();

                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                new Window("GameResult.fxml");
            }
        }
    }

    /**
     * Getter of the player list size
     *
     * @param playerList the current game
     * @return size of the playerList
     */
    public int getPlayerListSize(PlayerList playerList){
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
        if (getPlayerListSize(game.getPlayerList()) == 1
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
                firstRow = row;
                firstCol = col;
                CardStatus firstCardStatus = game.revealFirstCard(firstRow, firstCol);
                if (firstCardStatus.equals(CardStatus.FOUND)) {
                    break;
                }
                executeIdle(button, row, col);
            }

            //Is used if the turn has been stated.
            case ACTIVE -> {
                secondRow = row;
                secondCol = col;
                CardStatus secondCardStatus = game.revealSecondCard(secondRow, secondCol);
                if (secondCardStatus.equals(CardStatus.FOUND) ||
                        secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                    break;
                }
                executeActive(button, event);
            }
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
     * Checks achievements for the current player during the game.
     */
    public void checkAchievementsDuringGame() {
        String achievement = game.checkForAchievementsInGame(activePlayer);
        if (!achievement.isEmpty()) {
            achievementLabel.setFont(DEFAULT_FONT);
            achievementLabel.setText(activePlayer.getName() + " has earned:\n" + achievement);
            //automatic text size adjustment
            Text tmpText = new Text(achievementLabel.getText());
            tmpText.setFont(DEFAULT_FONT);
            double textHeight = tmpText.getLayoutBounds().getHeight();
            //check if text height is smaller than maximum height allowed
            if (textHeight <= MAX_TEXT_HEIGHT) {
                achievementLabel.setFont(DEFAULT_FONT);
            } else {
                //calculate new font size if too big
                double newFontSize = DEFAULT_FONT_SIZE * MAX_TEXT_HEIGHT / textHeight;
                achievementLabel.setFont(Font.font(DEFAULT_FONT.getFamily(), newFontSize));
            }
            AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Achievement.wav").toUri().toString());
            unlock.play();
        }
    }

    /**
     * Checks achievements for all players after game.
     */
    public void checkAchievementsAfterGame() {
        String achievement = game.checkForAchievementsAfterGame(game.getPlayerList());
        if (!achievement.isEmpty()) {
            achievementLabel.setStyle("-fx-font-size: 15pt;");
            achievementLabel.setText(achievement);
            AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Achievement.wav").toUri().toString());
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
     * Calls mainMenu to enable the Window to be closed
     */
    public void menu() {
        mainMenu();
    }

    /**
     * Closes the current window and opens the Start Screen
     */
    public void mainMenu() {
        ((Stage) (MainMenuButton.getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }

    /**
     * TODO erneutes laden muss noch geschehen
     */
    public void TryAgainClicked() {
        tryAgainButtonClicked();
    }

    public void tryAgainButtonClicked() {
        ((Stage) (TryAgainButton.getScene().getWindow())).close();
        new Window("Game.fxml");
    }

    /**
     * Closes the whole Game
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
     * This is a inner class for the timer of a {@link Game} in the GUI.
     * The default time is 120 seconds (2 minutes)
     */
    public class CountDownGUI extends Pane {

        Label timer = new Label();
        private Timeline animation;
        private int time;

        /**
         * Initiates the countDown for the GUI.
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
         * Actualizes the time label after every second
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
            timer.setStyle("-fx-font-size: 20pt;");
            timer.setPrefSize(PREF_WIDTH_TIMER, PREF_HEIGHT_TIMER);
            String css = Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Style.css").toUri().toString();
            timer.getStylesheets().add(css);
            timer.getStyleClass().add("text15");
            timer.setVisible(true);
            livesAndTime.getChildren().add(timer);
        }

        /**
         * Getter of the time of the GUI timer
         *
         * @return time
         */
        public int getGUITime() {
            return time;
        }

        /**
         * Pauses the timer
         */
        public void pauseTimer() {
            if (game.getPlayerList().size() == 1 &&
                    game.getSinglePlayerMode().equals(SinglePlayerMode.TIME)) {
                animation.pause();
            }
        }
    }
}
