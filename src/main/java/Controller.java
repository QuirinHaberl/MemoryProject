/**
 * This class is the control part of the MVC-architecture.
 */
public class Controller {

    /**
     * Current {@link TurnStatus}
     */
    private TurnStatus turnStatus;

    /**
     * Current {@link GameStatus}
     */
    private GameStatus gameStatus;

    /**
     * Active {@link PlayingField}
     */
    private final PlayingField playingField;


    /**
     * The Constructor initiates the game, the turn and creates a new {@link PlayingField}
     */
    public Controller() {
        turnStatus = TurnStatus.NOTSTARTED;
        gameStatus = GameStatus.RUNNING;
        playingField = new PlayingField();
    }


    /**
     * Getter for {@code playingField}
     *
     * @return the {@code playingField}
     */
    public PlayingField getPlayingField() {
        return playingField;
    }

    /**
     * Getter of {@link TurnStatus}
     *
     * @return the {@link TurnStatus}
     */
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }

    /**
     * Setter of the {@link TurnStatus}
     *
     * @param turnStatus sets the {@link TurnStatus}
     */
    public void setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
    }

    /**
     * Getter of {@link GameStatus}
     *
     * @return the {@link GameStatus}
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Setter of the {@link GameStatus}
     *
     * @param gameStatus sets the {@link GameStatus}
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}