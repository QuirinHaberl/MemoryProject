package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.CardSet;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.CardValues;

/**
 * The class {@link Card} implements a card of the game.
 */
public class Card {

    /**
     * Stores the {@code value} of the {@link Card}.
     */
    private final CardValues value;

    /**
     * Stores the used {@link CardSet}.
     */
    private CardSet cardSet;

    /**
     * Stores whether a {@link Card} is closed, open or found.
     */
    private CardStatus cardStatus;

    /**
     * Constructs a new {@link Card}.
     *
     * @param value value uses the enumerations of {@link CardValues}
     * @param cardSet to be used
     */
    public Card(CardValues value, CardSet cardSet) {
        this.value = value;
        this.cardSet = cardSet;
        this.cardStatus = CardStatus.CLOSED;
    }

    /**
     * Gets the value of a {@link Card}.
     *
     * @return the value of a {@link Card}.
     */
    public CardValues getValue() {
        return value;
    }

    /**
     * Visualizes {@link Card}
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
            //TODO Oder lieber Exception?
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

    /**
     * Gets the current cardSet.
     *
     * @return the current cardSet
     */
    public CardSet getCardSet() {
        return cardSet;
    }

    /**
     * Sets a cardSet.
     *
     * @param cardSet to be set.
     */
    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }
}