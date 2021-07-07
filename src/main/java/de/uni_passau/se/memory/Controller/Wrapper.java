package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Enums.CardSet;
import de.uni_passau.se.memory.Model.Game;

/**
 * Wrapper for the Model of the memory game.
 * To allow the communication with the Gui-controllers.
 * The used pattern is the Singleton-Pattern, to generate only one game
 */
public class Wrapper {
    /**
     * The instance of the DataDisplay
     */
    private static final Wrapper INSTANCE = new Wrapper();
    private Game game;

    /**
     * Constructs the game
     */
    private Wrapper() {
        this.game = new Game();
        game.getPlayingField().setCardSet(CardSet.PICTURES);
    }

    /**
     * Gets the instance of the DataDisplay
     *
     * @return the Instance
     */
    public static Wrapper getInstance() {
        return INSTANCE;
    }

    /**
     * Getter of the game
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter of the game
     *
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
