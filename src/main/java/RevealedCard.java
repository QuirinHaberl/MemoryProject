/**
<<<<<<< HEAD
 * <heading>RevealedCard</heading>
 * This class is a helper class to transfer information about a revealed card
=======
 * This class is a helper class to transfer information about a {@link RevealedCard}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
 * form Control to Visualisation.
 */
public class RevealedCard {

<<<<<<< HEAD
    //Attributes

=======
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
    /**
     * Indicator whether the card can be revealed
     */
    private boolean canBeRevealed;

    /**
     * Array of the current playing field status
     */
    private boolean[][] output;

    /**
     * Reference to the revealed Card
     */
    private Card revealedCard;

<<<<<<< HEAD

    //Methods

=======
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
    /**
     * Getter of the indicator
     *
     * @return The indicator
     */
    public boolean isCanBeRevealed() {
        return canBeRevealed;
    }

    /**
     * Getter of the playing field status
     *
     * @return The current playing field status
     */
    public boolean[][] getOutput() {
        return output;
    }

    /**
     * Getter of the revealed card
     *
     * @return The card itself
     */
    public Card getRevealedCard() {
        return revealedCard;
    }

    /**
     * Setter of the indicator
     *
     * @param canBeRevealed The indicator
     */
    public void setCanBeRevealed(boolean canBeRevealed) {
        this.canBeRevealed = canBeRevealed;
    }

    /**
     * Setter of the playing field status
     *
     * @param output The current playing field status
     */
    public void setOutput(boolean[][] output) {
        this.output = output;
    }

    /**
     * Setter of the revealed card
     *
     * @param revealedCard The card itself
     */
    public void setRevealedCard(Card revealedCard) {
        this.revealedCard = revealedCard;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> cfe11c1 (Merge pull request #73 from se2p-se/pull_request_Isabella)
