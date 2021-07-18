package de.uni_passau.se.memory.model.Enums;

import de.uni_passau.se.memory.model.Card;

/**
 * The enumeration of the {@link CardStatus}.
 */
public enum CardStatus {

    /**
     * A {@link Card} is closed.
     */
    CLOSED,

    /**
     * A {@link Card} is open.
     */
    OPEN,

    /**
     * A {@link Card} was already open.
     */
    AlREADYOPEN,

    /**
     * A {@link Card} is already found.
     */
    FOUND
}
