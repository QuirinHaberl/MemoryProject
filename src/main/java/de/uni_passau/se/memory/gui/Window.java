package de.uni_passau.se.memory.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Window extends Stage {

    Stage stage;

    public Window(String path) {
        stage = this;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        stage.setTitle("MEMORY");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/Card.png")));
        stage.setScene(scene);
        stage.show();
    }
}
