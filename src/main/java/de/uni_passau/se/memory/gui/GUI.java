package de.uni_passau.se.memory.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


/**
 * The GUI of this program.
 */
public class GUI extends Application {
    private double xOffSet = 0;
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
        Parent root = FXMLLoader.load(getClass().getResource("StartScreen"
                + ".fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("MEMORY");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/Card.png")));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffSet = event.getSceneX();
                yOffSet = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX()-xOffSet);
                stage.setY(event.getScreenY()-yOffSet);
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
