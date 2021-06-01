/**
 * This class PlayingField implements the playing field
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * the value "ist aufgedeckt".
 */
public class PlayingField {

    // Attributes

    /**
     * Hight and Width of the playingfield
     */
    private final int hight = 4;
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
     * Default constructor that creates a new playingfield and the control board
     */
    public PlayingField(){
        controlArray = new boolean [hight][width];
        board = new Card[hight][width];

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
     * If true, then the image of the card is open, if false, then the image is still hidden.
     *
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return whether a selected card is already revealed
     */

    public boolean isOpen(int row, int col) {
        boolean isopen = controlArray[row][col];
        return isopen;
    }

    /**
     * Checks whether all cards has already been turned over.
     * If true, then the images of all cards are open,
     * if false, then min. one image is still hidden.
     *
     * @return whether all cards are open or not.
     */

    public boolean areAllCardsOpen() {
        boolean allCardsOpen = true;
        for(int row = 0; row < controlArray.length ; ++row){
            for (int col = 0; col < controlArray[row].length; ++col) {
                if (isOpen(row,col)) {
                    continue;
                } else allCardsOpen = false;
            }
        }
        return allCardsOpen;
    }

    /*Noch zu implementieren und kommentieren:

    public Card getCard() {}

    public void removeCards(...){}


    */


}
