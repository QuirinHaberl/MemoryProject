package de.uni_passau.se.memory.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * The GUI of this program.
 */
public class GUI extends Application {

    /**
     * Starts the gui.
     *
     * @param stage the stage of java-fxml
     * @throws IOException if Game.fxml cant be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));

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
