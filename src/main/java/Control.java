/**
 * <heading>Control</heading>
 * This class is the control interface between the Visualisation and
 * the Model (connection point = PlayingField).
 */
public class Control {

    //Attributes

    /**
     * Current game status
     */
    private GameStatus gameStatus;

    /**
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
        gameStatus = GameStatus.NOTSTART;
        playingField = new PlayingField();
    }


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
        if (playingField.isOpen(row, col) == true) {
            if(gameStatus == GameStatus.RUNNING) {
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
            //Check whether end of the game is reached
            if(playingField.areAllCardsOpen() == true) {
                setGameStatus(GameStatus.END);
            }§  §§
        }
    }

    /**
     * Getter of playing field status
     *
     * @return Status of playing field
     */
    public boolean[][] getControlArray(){
        return playingField.getControlArray();
    }

    /**
     * Getter of the game status
     *
     * @return Current game status
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Setter of the game status
     *
     * @param gameStatus The current game status
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
