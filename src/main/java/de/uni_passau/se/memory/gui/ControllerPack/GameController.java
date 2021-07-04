package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.Enums.CardLetters;
import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.nio.file.Paths;


public class GameController {

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
                Object card = DataDisplay.getInstance().getGame().getCard(row,col).getValue();
                ((CardLetters) card).getPicture();
                button.getStyleClass().add(((CardLetters) card).getPicture());
                //button.setVisible(false);
                System.out.println(id);

            }
        });
        return button;
    }

    public Button start;


    @FXML
    private GridPane Board;

    public void startClicked() {

        int size = DataDisplay.getInstance().getGame().getPlayingField().getSize();
        DataDisplay.getInstance().getGame().getPlayingField().fillLetters();

        for (int row = 0; row < size; row++) {
            Board.getColumnConstraints().add(new ColumnConstraints(100));
            for (int col = 0; col < size; col++) {
                Board.add(newButton("(" + row + " " + col + ")",row,col),col,row);
            }
        }
    }

    /**
     * The other actions
     */
    public void menu(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("StartScreen.fxml");
    }

    public void help(ActionEvent actionEvent) {
        new Window("Rules.fxml");
    }

}
