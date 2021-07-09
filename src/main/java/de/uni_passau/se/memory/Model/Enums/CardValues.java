package de.uni_passau.se.memory.Model.Enums;

import de.uni_passau.se.memory.Model.Card;

/**
 * This is the {@link CardSet} with letters, digits and photos.
 */
public enum CardValues {

    /**
     * A {@link Card} can have values from ONE to THIRTYTWO.
     * the value of a {@link Card} is saved as well.
     */
    ONE(         "1" , "A",  "Card_Apple"),
    TWO(         "2" , "B",  "Card_Armor"),
    THREE(       "3" , "C",  "Card_Arrow"),
    FOUR(        "4" , "D",  "Card_Axe"),
    FIVE(        "5" , "E",  "Card_BlueGem"),
    SIX(         "6" , "F",  "Card_BluePotion"),
    SEVEN(       "7" , "G",  "Card_Bone"),
    EIGHT(       "8" , "H",  "Card_Bone2"),
    NINE(        "9" , "I",  "Card_Book"),
    TEN(         "10", "J",  "Card_Bow"),
    ELEVEN(      "11", "K",  "Card_Caldron"),
    TWELVE(      "12", "L",  "Card_Carrot"),
    THIRTEEN(    "13", "M",  "Card_Clover"),
    FOURTEEN(    "14", "N",  "Card_Crown"),
    FIFTEEN(     "15", "O",  "Card_Diamond"),
    SIXTEEN(     "16", "P",  "Card_Gold"),
    SEVENTEEN(   "17", "Q",  "Card_GreenGem"),
    EIGHTEEN(    "18", "R",  "Card_GreenPotion"),
    NINETEEN(    "19", "S",  "Card_Helmet"),
    TWENTY(      "20", "T",  "Card_Letter"),
    TWENTYONE(   "21", "U",  "Card_Mushroom"),
    TWENTYTWO(   "22", "V",  "Card_PinkGem"),
    TWENTYTHREE( "23", "W",  "Card_RedGem"),
    TWENTYFOUR(  "24", "X",  "Card_RedPotion"),
    TWENTYFIVE(  "25", "Y",  "Card_Ring"),
    TWENTYSIX(   "26", "Z",  "Card_Shield1"),
    TWENTYSEVE(  "27", "a",  "Card_Shield2"),
    TWENTYEIGHT( "28", "b",  "Card_Skull"),
    TWENTYNINE(  "29", "c",  "Card_Sword"),
    THIRTY(      "30", "d",  "Card_Trident"),
    THIRTYONE(   "31", "e",  "Card_Trophy"),
    THIRYTWO(    "32", "f",  "Card_Wand");


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
     * @param digit for a Console game with digits
     * @param letter for a Console game with letters
     * @param picture for a GUI game
     */
    CardValues(String digit, String letter, String picture) {
        this.digit = digit;
        this.letter = letter;
        this.picture = picture;
    }

    /**
     * Gets the {@code value} of a {@link CardValues}
     *
     * @return the {@code value} of a {@link CardValues}
     */

    public String getDigit() {
        return digit;
    }

    public String getLetter() {
        return letter;
    }

    public String getPicture() {
        return picture;
    }
}