package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AchievementController implements Initializable {

    @FXML
    private Label playerLabel;

    @FXML
    private Label achievementLabel;

    Game game = Wrapper.getInstance().getGame();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player activePlayer = game.getPlayerList().getFront();
        playerLabel.setText(activePlayer+ " has earned:");
        achievementLabel.setText(activePlayer.getAchievements().getCurrentAchievements());
    }
}
