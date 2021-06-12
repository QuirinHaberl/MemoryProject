package Model;

/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * The attribute {@link CardDigits} is the image of the card.
     */
    private CardDigits value;

    /**
     * The attribute {@code cardStatus} saves whether a card is closed, open or found.
     */
    private CardStatus cardStatus;

    /**
     * Constructs a new {@link Card}.
     *
     * @param value uses the enumerations of {@link CardDigits}
     */
    public Card(CardDigits value) {
        this.value = value;
        this.cardStatus = CardStatus.CLOSED;
    }

    /**
     * @return the value of a {@link Card}.
     */
    public CardDigits getValue() {
        return value;
    }

    /**
     * Checks weather the Model.Card is revealable.
     * If true, then the card ist already open.
     * If false, then the card is still closed.
     *
     * @return the revealed boolean of a {@link Card}.
     */
    public boolean isRevealed() {
        return cardStatus == CardStatus.OPEN;
    }

    /**
     * Sets the value of a {@link Card}.
     */
    public void setValue(CardDigits value) {
        this.value = value;
    }

    /**
     * Visualizes {@link Card}, defined by {@link CardDigits}
     *
     * @param cardValue Type of card
     * @return Visualization of the card value transferred
     * TODO für die nächste Iteration muss diese Methode auch umgeschrieben werden,
     * da sie vom Kartenset abhängt
     */
    public static String visualizeCard(CardDigits cardValue) {
        return cardValue.getDigit();
    }

    /**
     * @return the {@link CardStatus} of a {@link Card}.
     */
    public CardStatus getCardStatus() {
        return cardStatus;
    }

    /**
     * Sets the {@link CardStatus} of a {@link Card}.
     */
    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}