/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {
    CardValue value;

    public Card(CardValue value){
        this.value = value;
    }

    public CardValue getValue(){

        return value;
    }
}
