package Model;

/**
 * This class is the control part of the MVC-architecture.
 */
public class Game {

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
    private static Card[][] playingField = PlayingField.getBoard();

    /**
     * The Constructor initiates the game, the turn and creates a new {@link PlayingField}
     */
    public Game() {
        turnStatus = TurnStatus.NOTSTARTED;
        gameStatus = GameStatus.RUNNING;
    }

    /**
     * Getter for {@code playingField}
     *
     * @return the {@code playingField}
     */
    public static Card[][] getPlayingField() {
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

    /**
     * Returns a specified card.
     *
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return the selected card
     */
    public static Card getCard(int row, int col) {
        return playingField[row][col];
    }


    /**
     * Reveals a {@link Card} identified by row and column.
     *
     * @param row of the desired card
     * @param col of the desired card
     * @return the {@code value} of a card
     */
    public CardDigits revealCard(int row, int col) {
        return getCard(row, col).getValue();
    }

    /**
     * Reveals the first selected {@link Card} of a turn
     *
     * @param firstRow of the {@link Card}
     * @param firstCol of the {@link Card}
     * @return the current {@link CardStatus} of a {@link Card}
     */
    public CardStatus revealFirstCard(int firstRow, int firstCol) {
        Card firstCard = getCard(firstRow, firstCol);
        if (firstCard.getCardStatus().equals(CardStatus.CLOSED)) {
            firstCard.setCardStatus(CardStatus.OPEN);
            setTurnStatus(TurnStatus.ACTIVTURN);
        }
        return firstCard.getCardStatus();
    }

    /**
     * Reveals the second selected {@link Card} of a turn.
     * This method used to proof additional information about the {@link CardStatus}
     *
     * @param secondRow of the {@link Card}
     * @param secondCol of the {@link Card}
     * @return the current {@link CardStatus} of a {@link Card}
     */
    public CardStatus revealSecondCard(int secondRow, int secondCol) {
        Card secondCard = getCard(secondRow, secondCol);
        if (secondCard.getCardStatus().equals(CardStatus.OPEN)) {
            secondCard.setCardStatus(CardStatus.AlREADYOPEN);
        } else if (secondCard.getCardStatus().equals(CardStatus.CLOSED)) {
            secondCard.setCardStatus(CardStatus.OPEN);
            setTurnStatus(TurnStatus.NOTSTARTED);
        }
        return getCard(secondRow, secondCol).getCardStatus();
    }

    /**
     * Checks weather two selected {@link Card} have the same image and therefore form a pair
     *
     * @param rowFirstCard  : Row of the first {@link Card}
     * @param colFirstCard  : Column of the first {@link Card}
     * @param rowSecondCard : Row of the second {@link Card}
     * @param colSecondCard : Column of the second {@link Card}
     */
    public boolean pairCheck(int rowFirstCard, int colFirstCard, int rowSecondCard, int colSecondCard) {
        Card firstCard = getCard(rowFirstCard, colFirstCard);
        Card secondCard = getCard(rowSecondCard, colSecondCard);
        if (firstCard.getValue().equals(secondCard.getValue())) {
            removeCards(rowFirstCard, colFirstCard, rowSecondCard, colSecondCard);
            return true;
        } else {
            closeCards(rowFirstCard, colFirstCard, rowSecondCard, colSecondCard);
            return false;
        }
    }

    /**
     * Checks whether all cards have already been turned over.
     * If true, then the images of all cards are open.
     * If false, at least one image is still hidden.
     *
     * @return whether all cards are open or not.
     */
    public boolean areAllCardsOpen() {
        for (int row = 0; row < playingField.length; ++row) {
            for (int col = 0; col < playingField[row].length; ++col) {
                if (!(getCard(row, col).getCardStatus().equals(CardStatus.FOUND))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Removes a {@link Card} pair from the {@code board}.
     *
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected card
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected card
     */
    public void removeCards(int row1, int col1, int row2, int col2) {
        playingField[row1][col1].setCardStatus(CardStatus.FOUND);
        playingField[row2][col2].setCardStatus(CardStatus.FOUND);
    }

    /**
     * Closes a {@link Card} pair from the {@code board}.
     *
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected card
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected card
     */
    public void closeCards(int row1, int col1, int row2, int col2) {
        playingField[row1][col1].setCardStatus(CardStatus.CLOSED);
        playingField[row2][col2].setCardStatus(CardStatus.CLOSED);
    }
}