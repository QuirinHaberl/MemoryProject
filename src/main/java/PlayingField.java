import java.util.Random;

/**
 * The class PlayingField implements the {@link PlayingField} of the memory game
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * whether a card is revealed or not.
 */
public class PlayingField {

    /**
     * Height and Width of the {@link PlayingField}
     */
    //TODO This values are only for the Prototype,
    // later the values are generated by selecting the size of the {@link PlayingField}
    private final int height = 4;
    private final int width = 4;

    /**
     * Array of the {@link PlayingField}
     */
    private Card[][] board;


    /**
     * The Constructor creates a new {@link PlayingField} and fills the board with {@link Card}
     */
    public PlayingField() {
        board = new Card[height][width];
        fillWithCards();
    }

    /**
     * @return the {@code board}
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
     * Getter of the {@link PlayingField} {@code height}
     *
     * @return the {@code height}
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter of the {@link PlayingField} {@code width}
     *
     * @return the {@code width}
     */
    public int getWidth() {
        return width;
    }

    //TODO Setter of height and width should be implemented
    // in this Iteration they are redundant, because they are final.



    /**
     * Checks whether all cards have already been turned over.
     * If true, then the images of all cards are open.
     * If false, at least one image is still hidden.
     *
     * @return whether all cards are open or not.
     */
    public boolean areAllCardsOpen() {
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                if (!(getCard(row, col).getCardStatus().equals(CardStatus.FOUND))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a specified card.
     *
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return the selected card
     */
    public Card getCard(int row, int col) {
        return board[row][col];
    }

    /**
     * Removes a {@link Card} pair from the {@code board}.
     *
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected car
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected car
     */
    public void removeCards(int row1, int col1, int row2, int col2) {
        board[row1][col1].setCardStatus(CardStatus.FOUND);
        board[row2][col2].setCardStatus(CardStatus.FOUND);
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
     * Reveals the first selected {@link Card} of a turn
     *
     * @param firstRow   of the {@link Card}
     * @param firstCol   of the {@link Card}
     * @param controller is needed because of the interaction between view and model
     * @return the image of the first {@link Card} as Integer.
     */
    public int revealFirstCard(int firstRow, int firstCol, Controller controller) {
        controller.setTurnStatus(TurnStatus.ACTIVTURN);
        Card firstCard = getCard(firstRow, firstCol);
        if (!(firstCard.getCardStatus() == CardStatus.FOUND)) {
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
     * @param controller is needed because of the interaction between view and model
     * @return the image of the second {@link Card} as Integer
     */
    public int revealSecondCard(int secondRow, int secondCol, Controller controller) {
        controller.setTurnStatus(TurnStatus.NOTSTARTED);
        Card secondCard = getCard(secondRow, secondCol);
        if (!(secondCard.getCardStatus() == CardStatus.FOUND)) {
            secondCard.setCardStatus(CardStatus.OPEN);
        }
        int secondCardImage = secondCard.visualizeCard(revealCard(secondRow, secondCol));
        return secondCardImage;
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
        if (rowFirstCard==rowSecondCard&&colFirstCard==colSecondCard){
            System.out.println("You've selected the same card twice!");
            return false;
        }
        else if (firstCard.getValue().equals(secondCard.getValue())) {
            System.out.println("You've found a pair!");
            board[rowFirstCard][colFirstCard].setCardStatus(CardStatus.FOUND);
            board[rowSecondCard][colSecondCard].setCardStatus(CardStatus.FOUND);
        }
        return true;
    }

    public void closeAgain(int rowFirstCard, int colFirstCard, int rowSecondCard, int colSecondCard) {
        Card firstCard = getCard(rowFirstCard, colFirstCard);
        Card secondCard = getCard(rowSecondCard, colSecondCard);
        if (!firstCard.getCardStatus().equals(CardStatus.FOUND) && !secondCard.getCardStatus().equals(CardStatus.FOUND)) {
            board[rowFirstCard][colFirstCard].setCardStatus(CardStatus.CLOSED);
            board[rowSecondCard][colSecondCard].setCardStatus(CardStatus.CLOSED);
        }
    }

    /**
     * Fills the {@code board} with {@link Card} elements.
     * TODO This method must be changed in later iterations - Jan
     */
    private void fillWithCards() {
        CardValue[] allCardValues = CardValue.values();
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Card(allCardValues[counter%8]);
                counter++;
            }
        }
        shuffleBoard();
    }

    /**
     * Shuffles {@link Card} elements of {@code board}.
     */
    private void shuffleBoard() {
        Card tmp;
        int randRow, randCol;
        Random r = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                randRow = r.nextInt(board.length - 1);
                randCol = r.nextInt(board.length - 1);
                tmp = board[i][j];
                board[i][j] = board[randRow][randCol];
                board[randRow][randCol] = tmp;
            }
        }
    }
}
