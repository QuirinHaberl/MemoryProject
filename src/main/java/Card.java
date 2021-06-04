public class Card {
    /**
     * The class Card implements the card of the memory game.
     */

    //Attributes
    /**
     * This value is the image of the card
     */
    CardValue value;


    //Constructor
    /**
     * Default constructor that creates a new card.
     */
    public Card(CardValue value){
        this.value = value;
    }

    //Methods
    /**
     * @return the card value.
     */
    public CardValue getValue(){

        return value;
    }
}
