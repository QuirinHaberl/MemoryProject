package de.uni_passau.se.memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The GUI of this program.
 */
public class GUI extends Application {

    /**
     * Stars the gui.
     *
     * @param stage the stage of java-fxml
     * @throws IOException if Game.fxml cant be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("MEMORY");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The Main-methode of the GUI.
     *
     * @param args not in use
     */
    public static void main(String[] args) {

        launch();
    }
}
