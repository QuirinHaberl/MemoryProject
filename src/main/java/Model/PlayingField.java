package Model;

import java.util.Random;

/**
 * The class Model.PlayingField implements the {@link PlayingField} of the memory game
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * whether a card is revealed or not.
 */
public class PlayingField {

    /**
     * Height and Width of the {@link PlayingField}
     */
    private final int height;
    private final int width;

    /**
     * Array of the {@link PlayingField}
     */
    private static Card[][] board;

    /**
     * Current {@link CardSet} used
     */
    private CardSet cardSet;

    /**
     * The Constructor creates a new {@link PlayingField} and fills the board with {@link Card}
     *
     * @param size specifies the size of an array squared
     */
    public PlayingField(int size) {
        this.height = size;
        this.width = size;
        board = new Card[height][width];
    }

    /**
     * @return the {@code board}
     */
    public static Card[][] getBoard() {
        return board;
    }

    /**
     * Sets the current {@link CardSet}
     *
     * @param cardSet to be used
     */
    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }

    /**
     * Fills the {@code board} with {@link Card} elements.
     */
    public void fillWithCards() {
        switch (this.cardSet) {
            //TODO Beide fill-Methoden sind noch sehr ineffizient, aber funktionieren erstmal -Jan
            case DIGITS -> fillDigits();
            case LETTERS -> fillLetters();
            default -> System.err.println("Dieses Set wurde noch nicht implementiert");
        }
        shuffleBoard();
    }

    /**
     * Fills the board with digits
     */
    public void fillDigits() {
        CardDigits[] allCardDigits = CardDigits.values();
        int counter = 0;
        for (int i = 0; i < board.length / 2; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Card(allCardDigits[counter % allCardDigits.length]);
                counter++;
            }
        }
        counter = 0;
        for (int i = getBoard().length / 2; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Card(allCardDigits[counter % allCardDigits.length]);
                counter++;
            }
        }
    }

    /**
     * Fills the board with Letters
     */
    public void fillLetters() {
        CardLetters[] allCardLetters = CardLetters.values();
        int counter = 0;
        for (int i = 0; i < board.length / 2; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Card(allCardLetters[counter % allCardLetters.length]);
                counter++;
            }
        }
        counter = 0;
        for (int i = getBoard().length / 2; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Card(allCardLetters[counter % allCardLetters.length]);
                counter++;
            }
        }
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