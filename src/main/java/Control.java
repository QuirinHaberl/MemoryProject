/**
<<<<<<< HEAD
 * <heading>Control</heading>
 * This class is the control interface between the Visualisation and
 * the Model (connection point = PlayingField).
 */
public class Control {

    //Attributes

    /**
     * Current game status
=======
 * This class is the control part of the MVC-architecture.
 */
public class Control {

    /**
     * Current {@link GameStatus}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    private GameStatus gameStatus;

    /**
<<<<<<< HEAD
     * Active playing field
     */
    private final PlayingField playingField;

    //All following attributes need for methode checkForPairOfCards
    /**
     * Row of the current first revealed card
     */
    private int rowFirstCard;

    /**
     * Column of the current first revealed card
     */
    private int colFirstCard;

    /**
     * Row of the current second revealed card
     */
    private int rowSecondCard;

    /**
     * Column of the current second revealed card
     */
    private int colSecondCard;


    //Constructor

    /**
     * Default constructor that creates a new playing field
     */
    public Control(){
=======
     * Active {@link PlayingField}
     */
    private final PlayingField playingField;


    /**
     * Row and Column of the first revealed {@link Card}
     */
    private int rowFirstCard;
    private int colFirstCard;

    /**
     * Row and Column of the second revealed {@link Card}
     */
    private int rowSecondCard;
    private int colSecondCard;

    /**
     * The Constructor initiates the game and creates a new {@link PlayingField}
     */
    public Control() {
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
        gameStatus = GameStatus.NOTSTART;
        playingField = new PlayingField();
    }

<<<<<<< HEAD

    //Methods

    /**
     * Reveals a card identified by row and col by distinguishing between the
     * first and the second card to be revealed.
     *
     * @param row Row position of the card to be revealed
     * @param col Column position of the card to be revealed
     * @return The revealed card itself
     */
    public RevealedCard revealCard(int row, int col) {
        RevealedCard revealedCard = new RevealedCard();
        //Check whether the card has already been revealed
        if (playingField.isOpen(row, col)) {
            if(gameStatus == GameStatus.RUNNING) {
=======
    /**
     * Reveals a card identified by row and column by comparing the
     * first and the second card.
     *
     * @param row position of the card to be revealed
     * @param col position of the card to be revealed
     * @return the revealed card
     */
    public RevealedCard revealCard(int row, int col) {
        RevealedCard revealedCard = new RevealedCard();
        //Checks whether the card has already been revealed
        if (playingField.isOpen(row, col)) {
            if (gameStatus == GameStatus.RUNNING) {
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
                setGameStatus(GameStatus.NOTSTART);
            }
            revealedCard.setCanBeRevealed(false);
            return revealedCard;
        } else {
            //Save all data required by Visualization in RevealedCard
            revealedCard.setCanBeRevealed(true);
            revealedCard.setOutput(getControlArray());
            revealedCard.setRevealedCard(playingField.getCard(row, col));
            //Distinction between first and second card to be revealed
<<<<<<< HEAD
=======
            //TODO An dieser Stelle kommt es zu einem Fehler - muss noch überarbetet werden
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
            if (gameStatus == GameStatus.NOTSTART) {
                rowFirstCard = row;
                colFirstCard = col;
                setGameStatus(GameStatus.RUNNING);
            } else {
                rowSecondCard = row;
                colSecondCard = col;
                setGameStatus(GameStatus.NOTSTART);
            }
            return revealedCard;
        }
    }

    /**
     * Compares two cards for a match.
     */
    public void checkForPairOfCards() {
        Card firstCard = playingField.getCard(rowFirstCard, colFirstCard);
        Card secondCard = playingField.getCard(rowSecondCard, colSecondCard);
        if (firstCard.getValue().equals(secondCard.getValue())) {
            playingField.removeCards(rowFirstCard, colFirstCard,
                    rowSecondCard, colSecondCard);
<<<<<<< HEAD
            //Check whether end of the game is reached
            if(playingField.areAllCardsOpen()) {
=======
            //Checks whether end of the game is reached
            if (playingField.areAllCardsOpen()) {
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
                setGameStatus(GameStatus.END);
            }
        }
    }

    /**
<<<<<<< HEAD
     * Getter of playing field status
     *
     * @return Status of playing field
     */
    public boolean[][] getControlArray(){
=======
     * Getter for {@code controlArray}
     *
     * @return the {@code controlArray}
     */
    public boolean[][] getControlArray() {
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
        return playingField.getControlArray();
    }

    /**
<<<<<<< HEAD
     * Getter of the game status
     *
     * @return Current game status
=======
     * Getter of {@link GameStatus}
     *
     * @return the {@link GameStatus}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
<<<<<<< HEAD
     * Setter of the game status
     *
     * @param gameStatus The current game status
=======
     * Setter of the {@link GameStatus}
     *
     * @param gameStatus sets the {@link GameStatus}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
