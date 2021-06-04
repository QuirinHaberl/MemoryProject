/**
 * This class is the control part of the MVC-architecture.
 */
public class Control {

    /**
     * Current {@link GameStatus}
     */
    private GameStatus gameStatus;

    /**
     * Active {@link PlayingField}
     */
    private final PlayingField playingField;


    /**
     * Row and Column of the first revealed {@link Card}
     */
    private int rowFirstCard;
    private int colFirstCard;

    /**
     * Row and Column of the second revealed {@link Card}
     */
    private int rowSecondCard;
    private int colSecondCard;

    /**
     * The Constructor initiates the game and creates a new {@link PlayingField}
     */
    public Control() {
        gameStatus = GameStatus.NOTSTART;
        playingField = new PlayingField();
    }

    /**
     * Reveals a card identified by row and column by comparing the
     * first and the second card.
     *
     * @param row position of the card to be revealed
     * @param col position of the card to be revealed
     * @return the revealed card
     */
    public RevealedCard revealCard(int row, int col) {
        RevealedCard revealedCard = new RevealedCard();
        //Checks whether the card has already been revealed
        if (playingField.isOpen(row, col)) {
            if (gameStatus == GameStatus.RUNNING) {
                setGameStatus(GameStatus.NOTSTART);
            }
            revealedCard.setCanBeRevealed(false);
            return revealedCard;
        } else {
            //Save all data required by Visualization in RevealedCard
            revealedCard.setCanBeRevealed(true);
            revealedCard.setOutput(getControlArray());
            revealedCard.setRevealedCard(playingField.getCard(row, col));
            //Distinction between first and second card to be revealed
            //TODO An dieser Stelle kommt es zu einem Fehler - muss noch überarbetet werden
            if (gameStatus == GameStatus.NOTSTART) {
                rowFirstCard = row;
                colFirstCard = col;
                setGameStatus(GameStatus.RUNNING);
            } else {
                rowSecondCard = row;
                colSecondCard = col;
                setGameStatus(GameStatus.NOTSTART);
            }
            return revealedCard;
        }
    }

    /**
     * Compares two cards for a match.
     */
    public void checkForPairOfCards() {
        Card firstCard = playingField.getCard(rowFirstCard, colFirstCard);
        Card secondCard = playingField.getCard(rowSecondCard, colSecondCard);
        if (firstCard.getValue().equals(secondCard.getValue())) {
            playingField.removeCards(rowFirstCard, colFirstCard,
                    rowSecondCard, colSecondCard);
            //Checks whether end of the game is reached
            if (playingField.areAllCardsOpen()) {
                setGameStatus(GameStatus.END);
            }
        }
    }

    /**
     * Getter for {@code controlArray}
     *
     * @return the {@code controlArray}
     */
    public boolean[][] getControlArray() {
        return playingField.getControlArray();
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