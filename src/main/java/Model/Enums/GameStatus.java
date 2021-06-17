package Model.Enums;

/**
 * The enumeration of the {@code GameStatus}.
 */
public enum GameStatus {

    /**
     * While the {@link Model.Game} is running, the {@link GameStatus} is set to RUNNING.
     */
    RUNNING,

    /**
     * If the {@link GameStatus} is set to END the game is over.
     */
    END,

    /**
     * If the {@link GameStatus} is set to MENU the {@link Model.Player} can interact with the menu.
     */
    MENU
}