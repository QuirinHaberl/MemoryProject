package Model;

/**
 * This class is the control part of the MVC-architecture.
 */
public class Game {

    /**
     * Stores the current {@link TurnStatus}.
     */
    private TurnStatus turnStatus;

    /**
     * Stores the current {@link GameStatus}.
     */
    private GameStatus gameStatus;

    /**
     * Stores the {@link PlayingField}.
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
     * Gets the the board of a {@code playingField}.
     *
     * @return the board of a {@code playingField}
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
     * Sets the {@link TurnStatus}.
     *
     * @param turnStatus sets the {@link TurnStatus}
     */
    public void setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
    }

    /**
     * Gets the {@link GameStatus}.
     *
     * @return the {@link GameStatus}
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Sets the {@link GameStatus}.
     *
     * @param gameStatus sets the {@link GameStatus}
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * Gets a specified {@link Card}.
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
    public CardValue revealCard(int row, int col) {
        return getCard(row, col).getValue();
    }

    /**
     * Reveals the first selected {@link Card} of a turn.
     *
     * @param firstRow   of the {@link Card}
     * @param firstCol   of the {@link Card}
     * @return the image of the first {@link Card} as Integer.
     */
    public int revealFirstCard(int firstRow, int firstCol) {
        Card firstCard = getCard(firstRow, firstCol);
        if (firstCard.getCardStatus().equals(CardStatus.FOUND)) {
            return 0;
        } else {
            setTurnStatus(TurnStatus.ACTIVTURN);
            firstCard.setCardStatus(CardStatus.OPEN);
        }
        int firstCardImage = firstCard.visualizeCard(revealCard(firstRow, firstCol));
        return firstCardImage;
    }

    /**
     * Reveals the second selected {@link Card} of a turn.
     * This method is needed, because additionally information has to be proofed
     *
     * @param secondRow  of the {@link Card}
     * @param secondCol  of the {@link Card}
     * @return the image of the second {@link Card} as Integer
     */
    public int revealSecondCard(int secondRow, int secondCol) {
        Card secondCard = getCard(secondRow, secondCol);
        if (secondCard.getCardStatus() == CardStatus.FOUND) {
            return 0;
        } else {
            if (secondCard.getCardStatus() == CardStatus.OPEN) {
                return 9;
            } else {
                secondCard.setCardStatus(CardStatus.OPEN);
                setTurnStatus(TurnStatus.NOTSTARTED);
            }
        }
        int secondCardImage = secondCard.visualizeCard(revealCard(secondRow, secondCol));
        return secondCardImage;
    }

    /**
     * Checks whether two selected {@link Card}'s have the same value and form a pair.
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
            playingField[rowFirstCard][colFirstCard].setCardStatus(CardStatus.FOUND);
            playingField[rowSecondCard][colSecondCard].setCardStatus(CardStatus.FOUND);
            return true;
        } else {
            playingField[rowFirstCard][colFirstCard].setCardStatus(CardStatus.CLOSED);
            playingField[rowSecondCard][colSecondCard].setCardStatus(CardStatus.CLOSED);
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
}