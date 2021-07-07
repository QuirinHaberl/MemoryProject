package de.uni_passau.se.memory.gui.ControllerPack;

import de.uni_passau.se.memory.Model.Database;
import de.uni_passau.se.memory.View.DataDisplay;
import de.uni_passau.se.memory.gui.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class NumberOfPlayersController {
    public void twoPlayers(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 2);
    }

    public void threePlayers(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 3);
    }

    public void fourPlayers(ActionEvent actionEvent) {
        AudioClip click=new AudioClip(Paths.get("src/main/resources/de/uni_passau/se/memory/gui/Sound/Click.wav").toUri().toString());
        click.play();
        selectPlayers(actionEvent, 4);
    }

    private void selectPlayers (ActionEvent actionEvent, int number) {
        DataDisplay.getInstance().getGame().setPlayerAmount(number);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        new Window("NameSelection.fxml");
    }
}
