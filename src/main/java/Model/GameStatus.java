package Model;

/**
 * Enum of the game status.
 */
public enum GameStatus {
    /**
     * while the game is running, the {@link GameStatus} is set to RUNNING.
     */
    RUNNING,

    /**
     * If the {@link GameStatus} is END the game is over.
     */
    END,

    /**
     * If the {@link GameStatus} is MENU the Player can interact with the menu.
     */
    MENU
}