import java.util.Random;

/**
<<<<<<< HEAD
 * The class PlayingField implements the playing field of the memory game
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * the value "ist aufgedeckt".
 */
public class PlayingField {

    //Attributes
    /**
     * Hight and Width of the playingfield
=======
 * The class PlayingField implements the {@link PlayingField} of the memory game
 * as a two-dimensional array.
 * In addition, a controlArray of the boolean type is created to store
 * whether a card is revealed or not.
 */
public class PlayingField {

    /**
     * Height and Width of the {@link PlayingField}
     * TODO This values are only for the Prototype, later the
     * values are generated by selecting the size of the {@link PlayingField}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    private final int height = 4;
    private final int width = 4;

    /**
<<<<<<< HEAD
     * Array of the playiingfield
=======
     * Array of the {@link PlayingField}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    private Card[][] board;

    /**
<<<<<<< HEAD
     * Array of the control board
     */
    private boolean[][] controlArray;

    //Constructor
    /**
     * Default constructor that creates a new playingfield and the controlArray
=======
     * controlArray which saves whether a card is revealed or not.
     */
    private boolean[][] controlArray;

    /**
     * The Constructor creates a new {@link PlayingField} and the {@code controlArray}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    public PlayingField() {
        controlArray = new boolean[height][width];
        board = new Card[height][width];
<<<<<<< HEAD
        //All cards are set to starting Position
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                controlArray[i][j] = false;
            }
        }
        fillWithCards();
    }

    //Methods
    /**
     * @return the playingfield
=======
        fillWithCards();
    }

    /**
     * @return the {@code board}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
<<<<<<< HEAD
     * @return the control board
=======
     * @return the {@code controlArray}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    public boolean[][] getControlArray() {
        return controlArray;
    }

    /**
<<<<<<< HEAD
     * Checks whether a card identified by row and col has already been turned over.
     * If true, then the image of the card is open, if false, then the image is closed.
=======
     * Checks whether a card identified by row and column has already been turned over.
     * If true, then the image of the card is open. If false, then the image is closed.
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     *
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return whether a selected card is already revealed
     */
    public boolean isOpen(int row, int col) {
        return controlArray[row][col];
    }

    /**
<<<<<<< HEAD
     * Checks whether all cards has already been turned over.
     * If true, then the images of all cards are open,
     * if false, at least one image is still hidden.
=======
     * Checks whether all cards have already been turned over.
     * If true, then the images of all cards are open.
     * If false, at least one image is still hidden.
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
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
<<<<<<< HEAD
    /**
     * * Returns a specified card.
     * Checks if the row and the col are within the effective range.
     * 0 < row <= hight - 1.
     * 0 < col <= width - 1.
     * if not, throw out a IllegalArgumentException.
     * check if the card has been already oppened.
     * if yes, throw out a IllegalArgumentException.
=======

    /**
     * Returns a specified card.
     *
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return the selected card
     */
<<<<<<< HEAD

    public Card getCard(int row, int col){
        if(row > height - 1 || col > width - 1 || height < 0 || width < 0){
            throw new IllegalArgumentException(" the row or col can not be bigger than hight(width) or samller than 0!");
        }
        if(controlArray[row][col] == true){
            throw new IllegalArgumentException("this card has been already oppened!");
        }
=======
    public Card getCard(int row, int col) {
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
        return board[row][col];
    }

    /**
<<<<<<< HEAD
     * Removes a pair from the board-array.
     * removeCards happens after getCard.
     * so it needn't to check if row and col within a effective range.
     * change the element1 of controlArray to True.
     * change the element2 of controlArray to True.
=======
     * Removes a {@link Card} pair from the {@code board}.
     *
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected car
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected car
     */
<<<<<<< HEAD
    public void removeCards(int row1, int col1, int row2, int col2){
        controlArray[row1][col1] = true;
        controlArray[row2][col2] = true;
        //TODO
        //Karten müssen weiterhin existieren
        board[row1][col1] = null;
        board[row2][col2] = null;
    }

    /**
     * Board is filled with new pairs of cards
=======
    public void removeCards(int row1, int col1, int row2, int col2) {
        controlArray[row1][col1] = true;
        controlArray[row2][col2] = true;
    }

    /**
     * Fills the {@code board} with {@link Card} elements.
     * TODO This method must be changed in later iterations - Jan
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    private void fillWithCards() {
        //New pairs of cards are created
        Card card1 = new Card(CardValue.ONE);
        Card card2 = new Card(CardValue.TWO);
        Card card3 = new Card(CardValue.THREE);
        Card card4 = new Card(CardValue.FOUR);
        Card card5 = new Card(CardValue.FIVE);
        Card card6 = new Card(CardValue.SIX);
        Card card7 = new Card(CardValue.SEVEN);
        Card card8 = new Card(CardValue.EIGHT);
        //All cards are saved in board
        board[0][0] = card1;
        board[0][1] = card1;
        board[0][2] = card2;
        board[0][3] = card2;
        board[1][0] = card3;
        board[1][1] = card3;
        board[1][2] = card4;
        board[1][3] = card4;
        board[2][0] = card5;
        board[2][1] = card5;
        board[2][2] = card6;
        board[2][3] = card6;
        board[3][0] = card7;
        board[3][1] = card7;
        board[3][2] = card8;
        board[3][3] = card8;
        //Board is shuffled
        shuffleBoard();
    }

    /**
<<<<<<< HEAD
     * Cards are shuffled.
     */
    private void shuffleBoard(){
        Card tmp;
        int randRow, randCol;
        Random r = new Random();
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                randRow = r.nextInt(board.length-1);
                randCol = r.nextInt(board.length-1);
=======
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
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
                tmp = board[i][j];
                board[i][j] = board[randRow][randCol];
                board[randRow][randCol] = tmp;
            }
        }
    }
}