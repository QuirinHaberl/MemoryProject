/**
 * The class Card implements the card of the memory game.
 */
public class Card {

    //Attributes
    /**
     * This {@link CardValue} is the image of the card
     */
    CardValue value;


    //Constructor
    /**
     * Constructs a new {@link Card}. Initially, the value of a given cart is null.
     *
     * @param value enum value of {@link CardValue}
     */
    public Card(CardValue value) {
        this.value = value;
    }

    //Methods
    /**
     * @return the card value of {@link CardValue}.
     */
    public CardValue getValue() {

        return value;
    }
}
