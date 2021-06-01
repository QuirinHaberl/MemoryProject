/**
 * The class PlayingField implements the playing field of the memory game
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * the value "ist aufgedeckt".
 */
public class PlayingField {

    /**
     * Hight and Width of the playingfield
     */
    private final int HEIGHT = 4;
    private final int WIDTH = 4;

    /**
     * Array of the playiingfield
     */
    private Card[][] board;

    /**
     * Array of the control board
     */
    private boolean[][] controlArray;

    /**
     * Default constructor that creates a new playingfield and the controlArray
     */
    public PlayingField() {
        controlArray = new boolean[HEIGHT][WIDTH];
        board = new Card[WIDTH][WIDTH];
    }

    /**
     * @return the playingfield
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
     * @return the control board
     */
    public boolean[][] getControlArray() {
        return controlArray;
    }

    /**
     * Checks whether a card identified by row and col has already been turned over.
     * If true, then the image of the card is open, if false, then the image is closed.
     *
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return whether a selected card is already revealed
     */
    public boolean isOpen(int row, int col) {
        return controlArray[row][col];
    }

    /**
     * Checks whether all cards has already been turned over.
     * If true, then the images of all cards are open,
     * if false, at least one image is still hidden.
     *
     * @return whether all cards are open or not.
     */
    public boolean areAllCardsOpen() {
        boolean allCardsOpen = false;
        for (int row = 0; row < controlArray.length; ++row) {
            for (int col = 0; col < controlArray[row].length; ++col) {
                if (!(isOpen(row, col))) {
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
     * Removes a pair from the board-array.
     *
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected car
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected car
     */
    public void removeCards(int row1, int col1, int row2, int col2) {
        board[row1][col1] = null;
        board[row2][col2] = null;
    }
}