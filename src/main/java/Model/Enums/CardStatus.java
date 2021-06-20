package Model.Enums;

import Model.Card;

/**
 * The enumeration of the card status.
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
     * A {@link Card} is already open.
     */
    AlREADYOPEN,

    /**
     * A {@link Card} is already found.
     */
    FOUND
}