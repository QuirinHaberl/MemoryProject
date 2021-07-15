package de.uni_passau.se.memory.Model.Enums;

import de.uni_passau.se.memory.Model.Card;

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
