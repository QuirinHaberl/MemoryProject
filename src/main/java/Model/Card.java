package Model;

/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * The attribute {@link CardDigits} is the image of the card.
     */
    private final Object value;

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
     * Constructs a new {@link Card}.
     *
     * @param value uses the enumerations of {@link CardDigits}
     */
    public Card(CardLetters value) {
        this.value = value;
        this.cardStatus = CardStatus.CLOSED;
    }

    /**
     * @return the value of a {@link Card}.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Visualizes {@link Card}, defined by {@link CardDigits}
     *
     * @param value Type of card
     * @return Visualization of the card value transferred
     * da sie vom Kartenset abhängt
     */
    public static String visualizeCard(Object value) {
        if (value instanceof CardDigits) {
            return ((CardDigits) value).getDigit();
        } else if (value instanceof CardLetters) {
            return ((CardLetters) value).getLetter();
        } else {
            return null; //TODO Das müss ich noch schöner schreiben -Jan
        }
    }

    /**
     * @return the {@link CardStatus} of a {@link Card}.
     */
    public CardStatus getCardStatus() {
        return cardStatus;
    }

    /**
     * Sets the {@link CardStatus} of a {@link Card}.
     *
     * @param cardStatus is the {@link CardStatus} to be set
     */
    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}