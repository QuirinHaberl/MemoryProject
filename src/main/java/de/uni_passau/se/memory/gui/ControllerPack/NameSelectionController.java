package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NameSelectionController implements Initializable {

    @FXML
    private TextField player1Input;

    @FXML
    private Label nameLabel1;

    @FXML
    private TextField player2Input;

    @FXML
    private Label nameLabel2;

    @FXML
    private TextField player3Input;

    @FXML
    private Label nameLabel3;

    @FXML
    private TextField player4Input;

    @FXML
    private Label nameLabel4;

    @FXML
    private TextField input = new TextField();

    public void ok(ActionEvent actionEvent) {

        addPlayers();

        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        new Window("Levels.fxml");
    }

    public void addPlayers() {
        Game game = DataDisplay.getInstance().getGame();
        TextField[] textFields = {player1Input, player2Input, player3Input, player4Input};
        int playerAmount = game.getPlayerAmount();
        for (int i = 0; i < playerAmount; i++) {
            game.addPlayer(textFields[i].getText());
        }
        game.getDatabase().loadPlayerProfiles();
        game.useProfile(game.getDatabase().getPlayerProfiles());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label[] labels = {nameLabel1, nameLabel2, nameLabel3, nameLabel4};
        TextField[] textFields = {player1Input, player2Input, player3Input, player4Input};

        //TODO Man könnte die Labels default auf invisible setzen
        for (int i = 0; i < labels.length; i++) {
            labels[i].setVisible(false);
            textFields[i].setVisible(false);
        }
        for (int i = 0; i < DataDisplay.getInstance().getGame().getPlayerAmount(); i++) {
            labels[i].setVisible(true);
            textFields[i].setVisible(true);
        }
    }
}
