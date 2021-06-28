module de.uni_passau.se.mvc_rng {
    requires javafx.controls;
    requires javafx.fxml;

    // FXML uses reflection to inject references to the GUI into the controller
    opens de.uni_passau.se.memory to javafx.fxml, javafx.graphics;
}