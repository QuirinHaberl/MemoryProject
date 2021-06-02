/**
 * The class PlayingField implements the playing field of the memory game
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * the value "ist aufgedeckt".
 */
public class PlayingField {

    //Attributes
    /**
     * Hight and Width of the playingfield
     */
    private final int height = 4;
    private final int width = 4;

    /**
     * Array of the playiingfield
     */
    private Card[][] board;

    /**
     * Array of the control board
     */
    private boolean[][] controlArray;

    //Constructor
    /**
     * Default constructor that creates a new playingfield and the controlArray
     */
    public PlayingField() {
        controlArray = new boolean[height][width];
        board = new Card[height][width];
    }

    //Methods
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
     * * Returns a specified card.
     * Checks if the row and the col are within the effective range.
     * 0 < row < hight.
     * 0 < col < width.
     * if not, throw out a IllegalArgumentException.
     * check if the card has been already oppened.
     * if yes, throw out a IllegalArgumentException.
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return the selected card
     */

    public Card getCard(int row, int col){
        if(row > height - 1 || col > width - 1 || height < 0 || width < 0){
            throw new IllegalArgumentException(" the row or col can not be bigger than hight(width) or samller than 0!");
        }
        if(controlArray[row][col] == true){
            throw new IllegalArgumentException("this card has been already oppened!");
        }
        return board[row][col];
    }

    /**
     * Removes a pair from the board-array.
     * removeCards happens after getCard.
     * so it needn't to check if row and col within a effective range.
     * change the element1 of controlArray to True.
     * change the element2 of controlArray to True.
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected car
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected car
     */
    public void removeCards(int row1, int col1, int row2, int col2){
        controlArray[row1][col1] = true;
        controlArray[row2][col2] = true;
        board[row1][col1] = null;
        board[row2][col2] = null;
    }
}