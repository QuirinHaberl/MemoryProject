package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.CardSet;
import de.uni_passau.se.memory.Model.Enums.CardValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlayingFieldTest {

    private final CardSet globalCardSet = CardSet.DIGITS;
    private final int size = 4;

    private PlayingField playingField;

    @BeforeEach
    public void setUp() throws Exception {
        playingField = new PlayingField();
        playingField.setBoard(size);
        playingField.setCardSet(globalCardSet);
    }

    @Test
    void fillWithCards() {
        playingField.fillWithCards();
        boolean ifNull = false;
        loop:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (playingField.getBoard()[i][j] == null) {
                    ifNull = true;
                    break loop;
                }
            }
        }
        assertEquals(false, ifNull, "At least one element is null!");
    }

    @Test
    void fillWithValues() {
        playingField.fillWithValues(CardValues.values());
        boolean ifNull = false;
        loop:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (playingField.getBoard()[i][j].getValue() == null || playingField.getBoard()[i][j].getCardSet() == null) {
                    ifNull = true;
                    break loop;
                }
            }
        }
        assertEquals(false, ifNull, "At least one element has no value or CardSet!");
    }

    @Test
    void shuffleBoard() {
        playingField.fillWithValues(CardValues.values());
        playingField.shuffleBoard();
        boolean ifRandom = false;
        loop:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j = j + 2) {
                if (playingField.getBoard()[i][j] != playingField.getBoard()[i][j + 1]) {
                    ifRandom = true;
                    break loop;
                }
            }
        }
        assertEquals(true, ifRandom, "The Card[][] are not random!");
    }

    @Test
    void selectCardSet() {
        playingField.selectCardSet("L");
        assertEquals(CardSet.LETTERS, playingField.getCardSet(), "the CardSet isn't LETTERS!");

        playingField.selectCardSet("D");
        assertEquals(CardSet.DIGITS, playingField.getCardSet(), "the CardSet isn't DIGITS!");

        playingField.selectCardSet("L");
        assertNotEquals(CardSet.DIGITS, playingField.getCardSet(), "the two CardSets shouldn't be the same!");

    }

    @Test
    void selectBoardSize() {
        assertEquals(6, playingField.selectBoardSize("6"), "the size is not 6!");

        assertNotEquals(10, playingField.selectBoardSize("10"), "the size can not be bigger than 8!");
    }

}
