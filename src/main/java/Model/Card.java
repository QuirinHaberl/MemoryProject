package Model;

/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * The attribute {@link CardValue} is the image of the card.
     */
    private CardValue value;

    /**
     * Stores whether a {@link Card} is closed, open or found.
     */
    private CardStatus cardStatus;

    /**
     * Constructs a new {@link Card}.
     *
     * @param value uses the enumerations of {@link CardValue}
     */
    public Card(CardValue value) {
        this.value = value;
        this.cardStatus = CardStatus.CLOSED;
    }

    /**
     * @return the value of a {@link Card}.
     */
    public CardValue getValue() {
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
    public void setValue(CardValue value) {
        this.value = value;
    }

    /**
     * Visualizes {@link Card}, defined by {@link CardValue}
     *
     * @param cardValue Type of card
     * @return Visualization of the card value transferred
     * TODO für die nächste Iteration muss diese Methode auch umgeschrieben werden,
     * da sie vom Kartenset abhängt
     */
    public static int visualizeCard(CardValue cardValue) {
        int visualized;
        switch (cardValue) {
            case ONE:
                visualized = 1;
                break;
            case TWO:
                visualized = 2;
                break;
            case THREE:
                visualized = 3;
                break;
            case FOUR:
                visualized = 4;
                break;
            case FIVE:
                visualized = 5;
                break;
            case SIX:
                visualized = 6;
                break;
            case SEVEN:
                visualized = 7;
                break;
            case EIGHT:
                visualized = 8;
                break;
            default:
                visualized = 0;
        }
        return visualized;
    }

    /**
     * Gets the {@link CardStatus} of a {@link Card}.
     *
     * @return the {@link CardStatus} of a {@link Card}
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