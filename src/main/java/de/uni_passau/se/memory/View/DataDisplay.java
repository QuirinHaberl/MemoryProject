package de.uni_passau.se.memory.View;

import de.uni_passau.se.memory.Model.Game;

/**
 * Wrapper for the Model of the memory game.
 * To allow the communication with the Gui-controllers.
 * The used pattern is the Singleton-Pattern, to generate only one game
 * TODO Evtl. noch mit einem Observer-Pattern umsetzen
 * TODO Ich musste den Game Konstruktor public machen, da die getInstance() aus Game nicht
 *  funktioniert. Vielleicht weiß von euch jemand wie man das fixt.
 */
public class DataDisplay {
    /**
     * The instance of the DataDisplay
     */
    private static final DataDisplay INSTANCE = new DataDisplay();
    private Game game;

    /**
     * Constructs the game
     */
    private DataDisplay() {
        this.game = new Game();
    }

    /**
     * Gets the instance of the DataDisplay
     * @return the Instance
     */
    public static DataDisplay getInstance(){
        return INSTANCE;
    }

    /**
     * Getter of the game
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter of the game
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
