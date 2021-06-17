package Model;

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
     * A {@link Card} has already been opened.
     */
    AlREADYOPEN,

    /**
     * A {@link Card} is already found.
     */
    FOUND
}