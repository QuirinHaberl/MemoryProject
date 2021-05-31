import java.security.PrivateKey;

/**
 * <heading>Control</heading>
 * This class is the control interface between the Visualisation and
 * the Model (connection point = PlayingField).
 */
public class Control {
    //Constructor
    //TODO
    public Control(){

    }

    //Attributes

    /**
     * Current game status
     */
    private GameStatus gameStatus;

    /**
     * Active playing field
     */
    private PlayingField playingField;

    private Card firstCard;
    private Card secondCard;

    private int rowFirstCard;
    private int colFirstCard;

    private int rowSecondCard;
    private int colSecondCard;


    //Methods

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public RevealedCard revealCard(int row, int col) {
        RevealedCard revealedCard = new RevealedCard();
        if (playingField.isOpen(row, col) == true) {
            if(gameStatus == GameStatus.RUNNING) {
                setGameStatus(GameStatus.NOTSTART);
            }
            revealedCard.setCanBeRevealed(false);
            return revealedCard;
        } else {
            revealedCard.setCanBeRevealed(true);
            revealedCard.setOutput(getControlArray());
            revealedCard.setRevealedCard(playingField.getCard(row, col));
            if (gameStatus == GameStatus.NOTSTART) {
                firstCard = revealedCard.getRevealedCard();
                rowFirstCard = row;
                colFirstCard = col;
                setGameStatus(GameStatus.RUNNING);
            } else {
                secondCard = revealedCard.getRevealedCard();
                rowSecondCard = row;
                colSecondCard = col;
                setGameStatus(GameStatus.NOTSTART);
            }
            return revealedCard;
        }
    }

    /**
     *
     */
    public void checkForPairOfCards() {
        if (firstCard.getValue().equals(secondCard.getValue())) {
            playingField.removeCards(rowFirstCard, colFirstCard,
                    rowSecondCard, colSecondCard);
            if(playingField.areAllCardsOpen() == true) {
                setGameStatus(GameStatus.END);
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean[][] getControlArray(){
        return playingField.getControlArray();
    }

    /**
     *
     * @return
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     *
     * @param gameStatus
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
