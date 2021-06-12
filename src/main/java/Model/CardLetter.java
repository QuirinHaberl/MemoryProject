package Model;

/**
 * Enumeration of values that can be used.
 * This is the Cardset with Letters
 */
public enum CardLetter {
    /**
     * a {@link Card} can have values from A to P
     */
    A("A"), B("B"), C("C"), D("D"), F("F"),
    G("G"), H("H"), I("I"), J("J"), K("K"),
    L("L"), M("M"), N("N"), O("O"), P("P");

    /**
     * The value of a card is saved {@code label}, which can't be changed
     */
    private final String letter;

    /**
     * Constructs a new label
     *
     * @param letter is saved as a attribute for a {@link CardLetter}
     */
    CardLetter(String letter) {
        this.letter = letter;
    }

    /**
     * @return the {@code label} of a {@link CardLetter}
     */
    public String getLetter() {
        return letter;
    }
}