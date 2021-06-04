/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * The attribute {@link CardValue} is the image of the card.
     */
    CardValue value;

    /**
     * The attribute {@code revealed} is the image of the card.
     */
    boolean revealed;

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
}
