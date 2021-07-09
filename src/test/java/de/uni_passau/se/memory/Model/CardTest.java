package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.CardSet;
import de.uni_passau.se.memory.Model.Enums.CardValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card;

    private CardValues cardValues = CardValues.ONE;
    private CardSet cardSet = CardSet.LETTERS;

    @BeforeEach
    public void setUp() throws Exception{
        card = new Card(cardValues,cardSet);
    }

    @Test
    void visualizeCard() {
        assertEquals(cardValues.getLetter(), card.visualizeCard(),"the value should be 'A'!");

        card.setCardSet(CardSet.DIGITS);
        assertEquals(cardValues.getDigit(), card.visualizeCard(),"the value should be '1'");

        card.setCardSet(CardSet.PICTURES);
        assertEquals(cardValues.getPicture(), card.visualizeCard(),"the value should be 'Card_Apple'");

        card.setCardSet(CardSet.LETTERS);
        assertNotEquals(cardValues.getDigit(), card.visualizeCard(),"the value shouldn't be '1'");

        card.setCardSet(CardSet.LETTERS);
        assertNotEquals(cardValues.getPicture(), card.visualizeCard(),"the value shouldn't be 'Card_Apple'");
    }

}