package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Database;
import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.PlayerList;
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

/**
 * Controller for nameSelection.
 */
public class NameSelectionController implements Initializable {

    /**
     * The first player attributes.
     */
    @FXML
    private TextField player1Input;
    @FXML
    private Label nameLabel1;
    @FXML
    private CheckBox checkPlayer1;

    /**
     * The second player attributes.
     */
    @FXML
    private TextField player2Input;
    @FXML
    private Label nameLabel2;
    @FXML
    private CheckBox checkPlayer2;

    /**
     * The third player attributes.
     */
    @FXML
    private TextField player3Input;
    @FXML
    private Label nameLabel3;
    @FXML
    private CheckBox checkPlayer3;

    /**
     * The fourth player attributes.
     */
    @FXML
    private TextField player4Input;
    @FXML
    private Label nameLabel4;
    @FXML
    private CheckBox checkPlayer4;

    /**
     * Initializes the Labels.
     *
     * @param location  of the label
     * @param resources of the label
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label[] labels = {nameLabel1, nameLabel2, nameLabel3, nameLabel4};
        TextField[] textFields = {player1Input, player2Input, player3Input, player4Input};
        CheckBox[] checkBoxes = {checkPlayer1, checkPlayer2, checkPlayer3, checkPlayer4};

        for (int i = 0; i < getGamePlayerAmount(getGame()); i++) {
            labels[i].setVisible(true);
            textFields[i].setVisible(true);
            checkBoxes[i].setVisible(true);
        }
    }


    /**
     * Adds a Player to the playerList in the Model-Game.
     */
    public void addPlayers() {
        Game game = getGame();
        resetGamePlayerList(game);
        TextField[] textFields = {player1Input, player2Input, player3Input, player4Input};
        CheckBox[] checkBoxes = {checkPlayer1, checkPlayer2, checkPlayer3, checkPlayer4};
        int playerAmount = getGamePlayerAmount(game);
        for (int i = 0; i < playerAmount; i++) {
            if (textFields[i].getText().isEmpty()) {
                addPlayerToGame(game, "Player" + (i + 1));
            } else {
                addPlayerToGame(game, textFields[i].getText());
            }
        }
        setGameProfiles(getGameDatabase(game), checkBoxes, textFields);
        resetGameProfiles(getGameDatabase(game));
        loadGameProfiles(getGameDatabase(game));
        useGameProfile(getGameDatabase(game), getGamePlayerList(game));
    }

    /**
     * Gets the playerList of a game.
     *
     * @param game whose playerList is requested
     * @return the playerList
     */
    public PlayerList getGamePlayerList(Game game) {
        return game.getPlayerList();
    }

    /**
     * The playerProfiles of all players in a given playerList are loaded.
     *
     * @param database   contains the playerProfiles
     * @param playerList contains the players
     */
    public void useGameProfile(Database database, PlayerList playerList) {
        database.useProfile(playerList);
    }

    /**
     * Loads playerProfiles from profiles.csv
     *
     * @param database which stores the playerProfiles
     */
    public void loadGameProfiles(Database database) {
        database.loadPlayerProfiles();
    }

    /**
     * Resets the playerProfiles in a given database.
     *
     * @param database contains the playerProfiles to be reset
     */
    public void resetGameProfiles(Database database) {
        database.resetPlayerProfiles();
    }

    /**
     * Gets the database of a given game.
     *
     * @param game contains the database
     * @return the requested database
     */
    public Database getGameDatabase(Game game) {
        return game.getDatabase();
    }

    /**
     * Sets the which player wants to use a profile.
     *
     * @param database   containing the profiles
     * @param checkBoxes contains the which player wants to use a profile
     * @param textFields contains whether a player is a default player
     */
    public void setGameProfiles(Database database, CheckBox[] checkBoxes,
                                TextField[] textFields) {
        database.setUsesProfiles(checkBoxes, textFields);
    }

    /**
     * Adds a player the given game.
     *
     * @param game in which a player is added.
     * @param name of the player to be added.
     */
    public void addPlayerToGame(Game game, String name) {
        game.addPlayer(name);
    }

    /**
     * Gets the amount of player of a given game.
     *
     * @param game containing the amount of players
     * @return the playerAmount
     */
    public int getGamePlayerAmount(Game game) {
        return game.getPlayerAmount();
    }

    /**
     * Resets the playerList of a game.
     *
     * @param game whose playerList to be reset
     */
    public void resetGamePlayerList(Game game) {
        game.resetPlayerList();
    }

    /**
     * Gets a game of a given wrapper.
     *
     * @return the requested game
     */
    public Game getGame() {
        return Wrapper.getGame();
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
     * ok-button is clicked.
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
