package de.uni_passau.se.memory.Model.Enums;

import de.uni_passau.se.memory.Model.Card;

/**
 * This is the {@link CardSet} with letters, digits and photos.
 */
public enum CardValues {

    /**
     * A {@link Card} can have values from ONE to THIRYTWO.
     * the value of a {@link Card} is saved as well.
     *
     * 1. value
     */
    ONE("1", "A", "Card_Apple"),
    /**
     * 2. value
     */
    TWO("2", "B", "Card_Armor"),
    /**
     * 3. value
     */
    THREE("3", "C", "Card_Arrow"),
    /**
     * 4. value
     */
    FOUR("4", "D", "Card_Axe"),
    /**
     * 5. value
     */
    FIVE("5", "E", "Card_BlueGem"),
    /**
     * 6. value
     */
    SIX("6", "F", "Card_BluePotion"),
    /**
     * 7. value
     */
    SEVEN("7", "G", "Card_Bone"),
    /**
     * 8. value
     */
    EIGHT("8", "H", "Card_Bone2"),
    /**
     * 9. value
     */
    NINE("9", "I", "Card_Book"),
    /**
     * 10. value
     */
    TEN("10", "J", "Card_Bow"),
    /**
     * 11. value
     */
    ELEVEN("11", "K", "Card_Caldron"),
    /**
     * 12. value
     */
    TWELVE("12", "L", "Card_Carrot"),
    /**
     * 13. value
     */
    THIRTEEN("13", "M", "Card_Clover"),
    /**
     * 14. value
     */
    FOURTEEN("14", "N", "Card_Crown"),
    /**
     * 15. value
     */
    FIFTEEN("15", "O", "Card_Diamond"),
    /**
     * 16. value
     */
    SIXTEEN("16", "P", "Card_Gold"),
    /**
     * 17. value
     */
    SEVENTEEN("17", "Q", "Card_GreenGem"),
    /**
     * 18. value
     */
    EIGHTEEN("18", "R", "Card_GreenPotion"),
    /**
     * 19. value
     */
    NINETEEN("19", "S", "Card_Helmet"),
    /**
     * 20. value
     */
    TWENTY("20", "T", "Card_Letter"),
    /**
     * 21. value
     */
    TWENTYONE("21", "U", "Card_Mushroom"),
    /**
     * 22. value
     */
    TWENTYTWO("22", "V", "Card_PinkGem"),
    /**
     * 23. value
     */
    TWENTYTHREE("23", "W", "Card_RedGem"),
    /**
     * 24. value
     */
    TWENTYFOUR("24", "#", "Card_RedPotion"),
    /**
     * 25. value
     */
    TWENTYFIVE("25", "Y", "Card_Ring"),
    /**
     * 26. value
     */
    TWENTYSIX("26", "Z", "Card_Shield1"),
    /**
     * 27. value
     */
    TWENTYSEVEN("27", "a", "Card_Shield2"),
    /**
     * 28. value
     */
    TWENTYEIGHT("28", "b", "Card_Skull"),
    /**
     * 29. value
     */
    TWENTYNINE("29", "c", "Card_Sword"),
    /**
     * 30. value
     */
    THIRTY("30", "d", "Card_Trident"),
    /**
     * 31. value
     */
    THIRTYONE("31", "e", "Card_Trophy"),
    /**
     * 32. value
     */
    THIRYTWO("32", "f", "Card_Wand");


    private final String digit;

    /**
     * The value of a card is stored in {@code digit}, which can't be changed.
     */
    private final String letter;

    /**
     * The value of a card is stored in {@code digit}, which can't be changed.
     */
    private final String picture;

    /**
     * Constructs a new digit.
     *
     * @param digit   for a Console game with digits
     * @param letter  for a Console game with letters
     * @param picture for a GUI game
     */
    CardValues(String digit, String letter, String picture) {
        this.digit = digit;
        this.letter = letter;
        this.picture = picture;
    }

    /**
     * Gets the {@code value} of a {@link CardValues}.
     * Gets a digit.
     *
     * @return the {@code value} of a {@link CardValues}
     */
    public String getDigit() {
        return digit;
    }

    /**
     * Gets the {@code value} of a {@link CardValues}.
     * Gets a letter.
     *
     * @return the {@code value} of a {@link CardValues}
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Gets the {@code value} of a {@link CardValues}.
     * Gets a picture.
     *
     * @return the {@code value} of a {@link CardValues}
     */
    public String getPicture() {
        return picture;
    }
}
