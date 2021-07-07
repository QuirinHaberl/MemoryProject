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

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class GameController {

    @FXML
    public Button start;

    @FXML
    private Label achievementLabel;

    @FXML
    private AnchorPane key1;

    @FXML
    private Label labelPlayer1;

    @FXML
    private Label labelScore1;

    @FXML
    private AnchorPane key2;

    @FXML
    private Label labelPlayer2;

    @FXML
    private Label labelScore2;

    @FXML
    private AnchorPane key3;

    @FXML
    private Label labelPlayer3;

    @FXML
    private Label labelScore3;

    @FXML
    private AnchorPane key4;

    @FXML
    private Label labelPlayer4;

    @FXML
    private Label labelScore4;

    @FXML
    private GridPane Board;
    Object firstCard;
    Object secondCard;

    private Button b1;
    private Button b2;

    int firstRow;
    int firstCol;
    int secondRow;
    int secondCol;
    Game game = Wrapper.getInstance().getGame();
    Player activePlayer = game.getPlayerList().getFront();
    int size = game.getPlayingField().getSize();

    public GameController() {
    }

    @FXML
    public void initialize() {
        setPlayerLabel();
        startClicked();
    }

    /**
     * TODO check ob gebraucht
     */
    public void startClicked() {
        int size = game.getPlayingField().getSize();
        game.getPlayingField().fillWithCards();

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

    public void setPlayerLabel() {
        Label[] playerLabels = {labelPlayer1, labelPlayer2,
                labelPlayer3, labelPlayer4};
        Label[] scoreLabels = {labelScore1, labelScore2,
                labelScore3, labelScore4};
        AnchorPane[] keyAnchorPanes = {key1, key2,
                key3, key4};

        //TODO Man könnte die auch default invisible setzten
        //@Quirin du kannst die AnchorPanes gerne hier löschen, wenn du was anderes vor hast.

        for (int i = 0; i < scoreLabels.length; i++) {
            scoreLabels[i].setVisible(false);
            keyAnchorPanes[i].setVisible(false);
        }

        for (int i = 0; i < game.
                getPlayerAmount(); i++) {
            playerLabels[i].setText(game.getPlayerList().getPlayer(i).getName());
            scoreLabels[i].setVisible(true);
            scoreLabels[i].setText("Score: " + game.getPlayerList().getPlayer(i).getScore());
            keyAnchorPanes[i].setVisible(true);
        }
    }


    public void updateAllScores() {
        Label[] scoreLabels = {labelScore1, labelScore2, labelScore3, labelScore4};
        for (int i = 0; i < game.getPlayerAmount(); i++) {
            scoreLabels[i].setText("Score: " + game.getPlayerList().getPlayer(i).getScore());
        }
    }

    public void buttonClicked(ActionEvent event, Button button, String id, int row, int col) {

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
                executeActive(button, id, row, col);
                break;
        }
    }

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

    public void executeActive(Button button, String id, int row, int col) {

        b2 = button;

        secondCard = game.getCard(secondRow, secondCol).getValue();

        b2.getStyleClass().removeAll("Card");
        b2.getStyleClass().add(((CardValues) secondCard).getPicture());

        AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Unlock.wav").toUri().toString());
        unlock.play();
        checkIfWon();


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
        }

    }

    public void checkIfWon() {
        if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {

            checkAchievementsDuringGame();
            if (game.areAllCardsOpen()) {
                //TODO
                AudioClip found = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/GameWon.wav").toUri().toString());
                found.play();
                System.out.println("Spiel gewonnen!");
                //new Window("GameResultController.fxml");

                checkAchievementsAfterGame();

                game.storeProgress();
            }
        }
    }

    public void checkAchievementsDuringGame() {
        String achievement = game.checkForAchievements(activePlayer);
        if (!achievement.isEmpty()) {
            achievementLabel.setStyle("-fx-font-size: 15pt;");
            achievementLabel.setText(activePlayer.getName() + " has earned:\n" + achievement);
            AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Achievement.wav").toUri().toString());
            unlock.play();
        }
    }

    public void checkAchievementsAfterGame() {
        String achievement = game.checkForAchievements(game.getPlayerList());
        if (!achievement.isEmpty()) {
            achievementLabel.setStyle("-fx-font-size: 15pt;");
            achievementLabel.setText(achievement);
            AudioClip unlock = new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Achievement.wav").toUri().toString());
            unlock.play();
        }
    }

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
}

