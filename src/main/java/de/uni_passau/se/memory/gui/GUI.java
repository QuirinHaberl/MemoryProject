package de.uni_passau.se.memory.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


/**
 * The GUI of this program.
 */
public class GUI extends Application {
    /**
     * Stores the x-Position of the Screen
     */
    private double xOffSet = 0;
    /**
     * Stores the y-Position of the Screen
     */
    private double yOffSet = 0;

    /**
     * The Main-methode of the GUI.
     *
     * @param args not in use
     */
    public static void main(String[] args) {

        launch();
    }

    /**
     * Starts the gui.
     *
     * @param stage the stage of java-fxml
     * @throws IOException if Game.fxml cant be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartScreen"
                + ".fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("MEMORY");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/Card.png"))));
        //Makes Stage draggable
        root.setOnMousePressed(event -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        });
        stage.setScene(scene);
        stage.show();
    }
}
