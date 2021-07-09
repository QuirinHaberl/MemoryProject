package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.CardSet;
import de.uni_passau.se.memory.Model.Enums.CardValues;
import de.uni_passau.se.memory.gui.View;

import java.util.Random;

/**
 * The class PlayingField implements the {@link PlayingField} of the memory game
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * whether a card is revealed or not.
 */
public class PlayingField {

    /**
     * Stores the array of the {@link PlayingField}.
     */
    private static Card[][] board;

    int height;

    int width;

    /**
     * Stores the used {@link CardSet}.
     */
    private CardSet globalCardSet;

    /**
     * Default-constructor of {@link PlayingField}.
     */
    public PlayingField() {
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
     * @return the {@code board}
     */
    public Card[][] getBoard() {
        return board;
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
     * Fills the {@code board} with {@link Card} elements.
     */
    public void fillWithCards() {
        fillWithValues(CardValues.values());
        shuffleBoard();
    }

    /**
     * Fills the board with digits
     */
    public void fillWithValues(CardValues[] cardValues) {
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
     * Shuffles {@link Card} elements of {@code board}.
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
     * Sets the field-size to be used
     *
     * @param input passed size for the {@code field}
     * @return the selected size of the {@code field}
     */
    public int selectBoardSize(String input) {
        if (input.length() == 1) {
            if (input.matches("\\d")) {
                int size = Integer.parseInt(input);
                if (size <= 8 && size % 2 == 0 && size != 0) {
                    return size;
                }
            }
        }
        View.error("You can't select this size.");
        return 0;
    }

    /**
     * The Constructor creates a new {@link PlayingField} and fills the board with {@link Card}'s.
     *
     * @param size specifies the size of an array squared
     */
    public PlayingField(int size) {
        this.height = size;
        this.width = size;
        board = new Card[height][height];
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
     * Gets the size
     *
     * @return size
     */
    public int getSize() {
        return board.length;
    }

    /**
     * Sets the height.
     *
     * @param height to be used.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the width
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width.
     *
     * @param width to be used.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Setter of the{@code board}
     *
     * @param cards New {@code board}.
     */
    public static void setBoard(Card[][] cards) {
        board = cards;
    }
}