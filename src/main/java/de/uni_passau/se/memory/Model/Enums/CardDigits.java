package de.uni_passau.se.memory.Model.Enums;

import de.uni_passau.se.memory.Model.Card;

/**
 * This is the {@link CardSet} with digits.
 */
public enum CardDigits {

    /**
     * A {@link Card} can have values from ONE to THIRTYTWO.
     * the value of a {@link Card} is saved as well.
     */
    ONE("1"), TWO("2"), THREE("3"),
    FOUR("4"), FIVE("5"), SIX("6"),
    SEVEN("7"), EIGHT("8"), NINE("9"),
    TEN("10"), ELEVEN("11"), TWELVE("12"),
    THIRTEEN("13"), FOURTEEN("14"), FIFTEEN("15"),
    SIXTEEN("16"), SEVENTEEN("17"), EIGHTEEN("18"),
    NINETEEN("19"), TWENTY("20"), TWENTYONE("21"),
    TWENTYTWO("22"), TWENTYTHREE("23"), TWENTYFOUR("24"),
    TWENTYFIVE("25"), TWENTYSIX("26"), TWENTYSEVEN("27"),
    TWENTYEIGHT("28"), TWENTYNINE("29"), THIRTY("30"),
    THIRTYONE("31"), THIRTYTWO("32");

    /**
     * The value of a card is stored in {@code digit}, which can't be changed.
     */
    private final String digit;

    /**
     * Constructs a new digit.
     *
     * @param digit is saved as a attribute for a {@link CardDigits}
     */
    CardDigits(String digit) {
        this.digit = digit;
    }

    /**
     * Gets the {@code digit} of a {@link CardDigits}
     *
     * @return the {@code digit} of a {@link CardDigits}
     */
    public String getDigit() {
        return digit;
    }
}