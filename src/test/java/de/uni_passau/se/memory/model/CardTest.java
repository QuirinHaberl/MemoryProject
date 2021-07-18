package de.uni_passau.se.memory.model;

import de.uni_passau.se.memory.model.Enums.CardSet;
import de.uni_passau.se.memory.model.Enums.CardValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardTest {

    private final CardValue cardValue = CardValue.ONE;
    private final CardSet cardSet = CardSet.LETTERS;
    private Card card;

    @BeforeEach
    public void setUp() {
        card = new Card(cardValue, cardSet);
    }

    @Test
    void visualizeCard() {
        assertEquals(cardValue.getLetter(), card.visualizeCard(), "the value should be 'A'!");

        card.setCardSet(CardSet.DIGITS);
        assertEquals(cardValue.getDigit(), card.visualizeCard(), "the value should be '1'");

        card.setCardSet(CardSet.PICTURES);
        assertEquals(cardValue.getPicture(), card.visualizeCard(), "the value should be 'Card_Apple'");

        card.setCardSet(CardSet.LETTERS);
        assertNotEquals(cardValue.getDigit(), card.visualizeCard(), "the value shouldn't be '1'");

        card.setCardSet(CardSet.LETTERS);
        assertNotEquals(cardValue.getPicture(), card.visualizeCard(), "the value shouldn't be 'Card_Apple'");
    }

}
