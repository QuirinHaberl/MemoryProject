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

    /**
     * Checks if the row and the col are within the effective range.
     * 0 < row < hight.
     * 0 < col < width.
     * if not, throw out a IllegalArgumentException.
     * check if the card has been already oppened.
     * if yes, throw out a IllegalArgumentException.
     * @param row
     * @param col
     * @return
     */

    public Card getCard(int row, int col){
        if(row > hight || col > width || hight < 0 || width < 0){
            throw new IllegalArgumentException(" the row or col can not be bigger than hight(width) or samller than 0!")
        }
        if(controlArray[row][col] == true){
            throw new IllegalArgumentException("this card has been already oppened!");
        }
        return board[row][col];
    }

    /**
     * removeCards happens after getCard.
     * so it needn't to check if row and col within a effective range.
     * change the element1 of controlArray to True.
     * change the element2 of controlArray to True.
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     */
    public void removeCards(int row1, int col1, int row2, int col2){
        controlArray[row1][col1] = true;
        controlArray[row2][col2] = true;
    }

}
