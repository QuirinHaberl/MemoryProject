package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.Enums.CardLetters;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.Player;
import de.uni_passau.se.memory.Model.PlayerList;
import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    @FXML
    public Button start;

    @FXML
    private Label labelPlayer1;

    @FXML
    private Label labelPlayer2;

    @FXML
    private Label labelPlayer3;

    @FXML
    private Label labelPlayer4;

    @FXML
    private GridPane Board;

    int firstRow;
    int firstCol;
    Player activePlayer = DataDisplay.getInstance().getGame().getPlayerList().getFront();
    int size = DataDisplay.getInstance().getGame().getPlayingField().getSize();

    public GameController() {
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
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                button.getStyleClass().removeAll("Card");
                Object card = DataDisplay.getInstance().getGame().getCard(row, col).getValue();
                ((CardLetters) card).getPicture();
                button.getStyleClass().add(((CardLetters) card).getPicture());
                //button.setVisible(false);
                System.out.println(id);
                if (!(DataDisplay.getInstance().getGame().getGameStatus().equals(GameStatus.RUNNING))) {
                    return;
                }

                //Implementation of the game phases
                switch (DataDisplay.getInstance().getGame().getTurnStatus()) {
                    //Is used if the turn hasn't been stated yet.
                    case IDLE:
                        firstRow = row;
                        firstCol = col;
                        Object firstCard = DataDisplay.getInstance().getGame().getCard(row, col).getValue();
                        CardStatus firstCardStatus = DataDisplay.getInstance().getGame().revealFirstCard(firstRow, firstCol);
                        button.getStyleClass().removeAll("Card");
                        button.getStyleClass().add(((CardLetters) firstCard).getPicture());
                        if (firstCardStatus.equals(CardStatus.FOUND)) {
                            break;
                        }
                        break;
                    //Is used if the turn has been stated.
                    case ACTIVE:
                        int secondRow = row;
                        int secondCol = col;
                        Object secondCard = DataDisplay.getInstance().getGame().getCard(secondRow, secondCol).getValue();
                        CardStatus secondCardStatus = DataDisplay.getInstance().getGame().revealSecondCard(secondRow, secondCol);

                        // TODO Hier ist irgendwo noch ein Bug!
                        button.getStyleClass().removeAll("Card");
                        button.getStyleClass().add(((CardLetters) secondCard).getPicture());
                        if (secondCardStatus.equals(CardStatus.FOUND)) {
                            break;
                        } else if (secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                            break;

                        }
                        if (DataDisplay.getInstance().getGame().pairCheck(firstRow, firstCol, secondRow, secondCol)) {
                            if (DataDisplay.getInstance().getGame().areAllCardsOpen()) {
                                // TODO Hier muss Ausgabe erfolgen dass alle Karten gefunden sind
                            }
                        } else {

                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Button b1 = (Button) Board.getChildren().get(firstRow * size + firstCol);
                            b1.getStyleClass().clear();
                            b1.getStyleClass().add("Card");
                            Button b2 = (Button) Board.getChildren().get(secondRow * size + secondCol);
                            b2.getStyleClass().clear();
                            b2.getStyleClass().add("Card");

                            activePlayer = activePlayer.getNext();
                        }
                }
            }
        });
        return button;
    }

    public void startClicked() {
        int size = DataDisplay.getInstance().getGame().getPlayingField().getSize();
        DataDisplay.getInstance().getGame().getPlayingField().fillLetters();

        for (int row = 0; row < size; row++) {
            Board.getColumnConstraints().add(new ColumnConstraints(100));
            for (int col = 0; col < size; col++) {
                Board.add(newButton("(" + row + " " + col + ")", row, col), col, row);
            }
        }
        DataDisplay.getInstance().getGame().setGameStatus(GameStatus.RUNNING);
    }


    /**
     * The other actions
     */
    public void menu(ActionEvent actionEvent) {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }

    public void help(ActionEvent actionEvent) {
        new Window("Rules.fxml");
    }

    public void setPlayerLabel() {
        Label[] playerLabels = {labelPlayer1, labelPlayer2,
                labelPlayer3, labelPlayer4};
        for (int i = 0; i < DataDisplay.getInstance().getGame().
                getPlayerAmount(); i++) {
            playerLabels[i].setText(DataDisplay.getInstance().getGame().
                    getPlayerList().getPlayer(i).getName());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPlayerLabel();
        startClicked();
    }
}
