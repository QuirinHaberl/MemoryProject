package de.uni_passau.se.memory.model;

import de.uni_passau.se.memory.model.Enums.CardSet;
import de.uni_passau.se.memory.model.Enums.CardValue;
import de.uni_passau.se.memory.gui.View;

import java.util.Random;

/**
 * The class {@link PlayingField} implements the {@link PlayingField} of the
 * memory game as a two-dimensional array.
 */
public class PlayingField {

    /**
     * Maximum of the board size
     */
    public static final int MAX_BOARD_SIZE = 8;
    /**
     * Stores the array of the {@link PlayingField}.
     */
    private static Card[][] board;
    /**
     * Stores the used {@link CardSet}.
     */
    private CardSet globalCardSet;

    /**
     * Default-constructor of {@link PlayingField}.
     */
    public PlayingField() {
        //Default playingField
        setBoard(4);
        this.globalCardSet = CardSet.DIGITS;
    }

    /**
     * Gets the {@code board}.
     *
     * @return the {@code board}
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
     * Sets the size of a {@code board}.
     *
     * @param size to be used.
     */
    public void setBoard(int size) {
        board = new Card[size][size];
    }

    /**
     * Fills the {@code board} with {@link Card} elements.
     */
    public void fillWithCards() {
        fillWithValues(CardValue.values());
        shuffleBoard();
    }

    /**
     * Fills the board with cardValues.
     *
     * @param cardValues which are filled into the playingField.
     */
    public void fillWithValues(CardValue[] cardValues) {
        int size = cardValues.length;

        int counter = (int) Math.round(Math.random() * (size));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j = j + 2) {
                board[i][j] = new Card(cardValues[counter % size], globalCardSet);
                board[i][j + 1] = new Card(cardValues[counter % size], globalCardSet);
                counter++;
            }
        }
    }

    /**
     * Shuffles {@link Card} elements of {@code board} using the
     * Fisher-Yates-Algorithm.
     */
    public void shuffleBoard() {
        Random r = new Random();

        //Fisher-Yates-Algorithm
        for (int i = board.length - 1; i > 0; i--) {
            for (int j = board[i].length - 1; j > 0; j--) {
                int m = r.nextInt(i + 1);
                int n = r.nextInt(j + 1);

                Card temp = board[i][j];
                board[i][j] = board[m][n];
                board[m][n] = temp;
            }
        }
    }

    /**
     * Sets the {@link CardSet} to be used
     *
     * @param input passed {@link CardSet}
     * @return true if no error appeared
     */
    public boolean selectCardSet(String input) {
        if (input.length() == 1) {
            if (input.equalsIgnoreCase("L")) {
                setCardSet(CardSet.LETTERS);
                return true;
            } else if (input.equalsIgnoreCase("D")) {
                setCardSet(CardSet.DIGITS);
                return true;
            }
        }
        View.error("This set does not exist!");
        return false;
    }

    /**
     * Sets the field-size to be used.
     *
     * @param input passed size for the {@code field}
     * @return the selected size of the {@code field}
     */
    public int selectBoardSize(String input) {
        if (input.length() == 1 && input.matches("\\d")) {
            int size = Integer.parseInt(input);
            if (size <= MAX_BOARD_SIZE && size % 2 == 0 && size != 0) {
                return size;
            }
        }
        View.error("You can't select this size.");
        return 0;
    }

    /**
     * Gets the cardSet
     *
     * @return a cardSet
     */
    public CardSet getCardSet() {
        return globalCardSet;
    }

    /**
     * Sets the current {@link CardSet}
     *
     * @param cardSet to be used
     */
    public void setCardSet(CardSet cardSet) {
        this.globalCardSet = cardSet;
    }

    /**
     * Gets the size
     *
     * @return size
     */
    public int getSize() {
        return board.length;
    }

    /**
     * Gets a specified {@link Card}.
     *
     * @param row of the selected card
     * @param col of the selected card
     * @return the selected card
     */
    public Card getCard(int row, int col) {
        return getBoard()[row][col];
    }
}
