package Model;

/**
 * Enumeration of values that can be used.
 */
public enum CardValue {
    /**
     * a {@link Card} can have values from ONE to EIGHT
     * the value of a {@link Card} is saved as well.
     */
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"),
    FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8");

    /**
     * The value of a card is saved {@code label}, which can't be changed
     */
    private final String digit;

    /**
     * Constructs a new label
     *
     * @param digit is saved as a attribute for a {@link CardValue}
     */
    CardValue(String digit) {
        this.digit = digit;
    }

    /**
     * @return the {@code label} of a {@link CardValue}
     */
    public String getDigit() {
        return digit;
    }
}