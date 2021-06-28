package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.CardDigits;
import de.uni_passau.se.memory.Model.Enums.CardLetters;
import de.uni_passau.se.memory.Model.Enums.CardStatus;

/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * Stores the {@code value} of the {@link Card}.
     */
    private final Object value;

    /**
     * Stores whether a {@link Card} is closed, open or found.
     */
    private CardStatus cardStatus;

    /**
     * Constructs a new {@link Card}.
     *
     * @param value uses the enumerations of {@link CardDigits} or {@link CardLetters}
     */
    public Card(CardDigits value) {
        this.value = value;
        this.cardStatus = CardStatus.CLOSED;
    }

    /**
     * Constructs a new {@link Card}.
     *
     * @param value uses the enumerations of {@link CardDigits} or {@link CardLetters}
     */
    public Card(CardLetters value) {
        this.value = value;
        this.cardStatus = CardStatus.CLOSED;
    }

    /**
     * Gets the value of a {@link Card}.
     *
     * @return the value of a {@link Card}.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Visualizes {@link Card}, defined by {@link CardDigits}.
     *
     * @return visualization of the given card
     */
    public String visualizeCard() {
        if (value instanceof CardDigits) {
            return ((CardDigits) value).getDigit();
        } else if (value instanceof CardLetters) {
            return ((CardLetters) value).getLetter();
        } else {
            return null;
        }
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
     *
     * @param cardStatus is the {@link CardStatus} to be set
     */
    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}