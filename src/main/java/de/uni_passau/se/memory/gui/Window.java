package de.uni_passau.se.memory.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;


/**
 * Class to build a new window.
 */
public class Window extends Stage {

    /**
     * Current new stage
     */
    Stage stage;

    /**
     * Stores the x-Position of the Screen
     */
    private double xOffSet = 0;
    /**
     * Stores the y-Position of the Screen
     */
    private double yOffSet = 0;

    /**
     * Build a new window.
     *
     * @param path to the window to be built
     */
    public Window(String path) {
        stage = this;
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        Scene scene = new Scene(root);

        stage.setTitle("MEMORY");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/Card.png"))));
        //Makes the stage draggable
        root.setOnMousePressed(event -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX()-xOffSet);
            stage.setY(event.getScreenY()-yOffSet);
        });
        stage.setScene(scene);
        stage.show();
    }
}
