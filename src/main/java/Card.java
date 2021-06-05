/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * The attribute {@link CardValue} is the image of the card.
     */
    private CardValue value;

    /**
     * The attribute {@code revealed} is the value whether the card is revealed.
     */
    private boolean revealed;

    /**
     * Constructs a new {@link Card}.
     *
     * @param value uses the enumerations of {@link CardValue}
     */
    public Card(CardValue value) {
        this.value = value;
    }

    /**
     * @return the value of a {@link Card}.
     */
    public CardValue getValue() {
        return value;
    }

    /**
     * Checks weather the Card is revealable.
     * If true, then the card ist already open.
     * If false, then the card is still closed.
     *
     * @return the revealed boolean of a {@link Card}.
     */
    public boolean isRevealed() {
        return revealed;
    }

    /**
     * Sets the value of a {@link Card}.
     */
    public void setValue(CardValue value) {
        this.value = value;
    }

    /**
     * Sets the revealed boolean of a {@link Card}.
     */
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    /**
     * Visualizes {@link Card}, defined by {@link CardValue}
     *
     * @param cardValue Type of card
     * @return Visualization of the card value transferred
     * TODO Überprüfe, ob hier auch Buchstaben verwendet werden können
     * TODO für die nächste Iteration muss diese Methode auch umgeschrieben werden,
     *  da sie vom Kartenset abhängt
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
}