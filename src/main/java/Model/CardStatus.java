package Model;

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
     * a {@link Card} is open.
     */
    AlREADYOPEN,

    /**
     * a {@link Card} is already found.
     */
    FOUND
}