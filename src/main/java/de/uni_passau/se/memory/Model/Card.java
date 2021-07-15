package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.CardSet;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.CardValue;

/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * Stores the {@code value} of the {@link Card}.
     */
    private final CardValue value;

    /**
     * Stores the used {@link CardSet}.
     */
    private CardSet cardSet;

    /**
     * Stores whether a {@link Card} is closed, open, found or was already
     * found.
     */
    private CardStatus cardStatus;

    /**
     * Constructs a new {@link Card}.
     *
     * @param value   uses the enumerations of {@link CardValue}
     * @param cardSet to be used
     */
    public Card(CardValue value, CardSet cardSet) {
        this.value = value;
        this.cardSet = cardSet;
        this.cardStatus = CardStatus.CLOSED;
    }

    /**
     * Gets the value of a {@link Card}.
     *
     * @return the value of a {@link Card}.
     */
    public CardValue getValue() {
        return value;
    }

    /**
     * Visualizes {@link Card}.
     *
     * @return visualization of the given card
     */
    public String visualizeCard() {
        if (cardSet.equals(CardSet.DIGITS)) {
            return value.getDigit();
        } else if (cardSet.equals(CardSet.LETTERS)) {
            return value.getLetter();
        } else if (cardSet.equals(CardSet.PICTURES)) {
            return value.getPicture();
        } else {
            throw new IllegalStateException();
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

    /**
     * Gets the current {@code cardSet}.
     *
     * @return the current {@code cardSet}
     */
    public CardSet getCardSet() {
        return cardSet;
    }

    /**
     * Sets a {@code cardSet}.
     *
     * @param cardSet to be set.
     */
    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }

    /**
     * Gets the picture of a {@code cardValue}.
     *
     * @param cardValue whose picture is requested
     * @return the picture of a card
     */
    public String getCardPicture(CardValue cardValue) {
        return cardValue.getPicture();
    }
}
