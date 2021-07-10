package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class NameSelectionController implements Initializable {

    /**
     * the first player attributes
     */
    @FXML
    private TextField player1Input;
    @FXML
    private Label nameLabel1;
    @FXML
    private CheckBox checkPlayer1;

    /**
     * the second player attributes
     */
    @FXML
    private TextField player2Input;
    @FXML
    private Label nameLabel2;
    @FXML
    private CheckBox checkPlayer2;

    /**
     * the third player attributes
     */
    @FXML
    private TextField player3Input;
    @FXML
    private Label nameLabel3;
    @FXML
    private CheckBox checkPlayer3;

    /**
     * the fourth player attributes
     */
    @FXML
    private TextField player4Input;
    @FXML
    private Label nameLabel4;
    @FXML
    private CheckBox checkPlayer4;

    /**
     * Initializes the Labels
     *
     * @param location  of the label
     * @param resources of the label
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label[] labels = {nameLabel1, nameLabel2, nameLabel3, nameLabel4};
        TextField[] textFields = {player1Input, player2Input, player3Input, player4Input};
        CheckBox[] checkBoxes = {checkPlayer1, checkPlayer2, checkPlayer3, checkPlayer4};

        //TODO Man könnte die Labels default auf invisible setzen
        for (int i = 0; i < labels.length; i++) {
            labels[i].setVisible(false);
            textFields[i].setVisible(false);
            checkBoxes[i].setVisible(false);
            textFields[i].setText("");
        }

        for (int i = 0; i < Wrapper.getInstance().getGame().getPlayerAmount(); i++) {
            labels[i].setVisible(true);
            textFields[i].setVisible(true);
            checkBoxes[i].setVisible(true);
        }
    }

    /**
     * adds a Player to the playerList in the Model-Game
     */
    public void addPlayers() {
        Game game = Wrapper.getInstance().getGame();
        game.resetPlayerList();
        TextField[] textFields = {player1Input, player2Input, player3Input, player4Input};
        CheckBox[] checkBoxes = {checkPlayer1, checkPlayer2, checkPlayer3, checkPlayer4};
        int playerAmount = game.getPlayerAmount();
        for (int i = 0; i < playerAmount; i++) {
            if (textFields[i].getText().isEmpty()) {
                game.addPlayer("Player" + (i + 1));
            } else {
                game.addPlayer(textFields[i].getText());
            }
        }
        game.getDatabase().setUsesProfiles(checkBoxes, textFields);
        game.getDatabase().resetPlayerProfiles();
        game.getDatabase().loadPlayerProfiles();
        game.getDatabase().useProfile(game.getPlayerList());
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
     * Button ok is clicked
     *
     * @param actionEvent when button clicked
     */
    public void ok(ActionEvent actionEvent) {

        addPlayers();

        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        AudioClip click = new AudioClip(Paths.get(
                "src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        new Window("Levels.fxml");
    }

}
