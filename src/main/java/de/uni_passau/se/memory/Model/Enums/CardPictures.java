package de.uni_passau.se.memory.Model.Enums;

import de.uni_passau.se.memory.Model.Card;

/**
 * This is the {@link CardSet} with letters.
 */
public enum CardPictures {

    /**
     * A {@link Card} can have values from [A, Z] and [a, z].
     */
    Card_Apple("Card_Apple"),
    Card_Armor("Card_Armor"),
    Card_Arrow("Card_Arrow"),
    Card_Axe("Card_Axe"),
    Card_BlueGem("Card_BlueGem"),
    Card_BluePotion("Card_BluePotion"),
    Card_Bone("Card_Bone"),
    Card_Bone2("Card_Bone2"),
    Card_Book("Card_Book"),
    Card_Bow("Card_Bow"),
    Card_Caldron("Card_Caldron"),
    Card_Carrot("Card_Carrot"),
    Card_Clover("Card_Clover"),
    Card_Crown("Card_Crown"),
    Card_Diamond("Card_Diamond"),
    Card_Gold("Card_Gold"),
    Card_GreenGem("Card_GreenGem"),
    Card_GreenPotion("Card_GreenPotion"),
    Card_Helmet("Card_Helmet"),
    Card_Letter("Card_Letter"),
    Card_Mushroom("Card_Mushroom"),
    Card_PinkGem("Card_PinkGem"),
    Card_RedGem("Card_RedGem"),
    Card_RedPotion("Card_RedPotion"),
    Card_Ring("Card_Ring"),
    Card_Shield1("Card_Shield1"),
    Card_Shield2("Card_Shield2"),
    Card_Skull("Card_Skull"),
    Card_Sword("Card_Sword"),
    Card_Trident("Card_Trident"),
    Card_Trophy("Card_Trophy"),
    Card_Wand("Card_Wand");


    /**
     * The value of a card is stored in {@code picture}, which can't be changed.
     */
    private final String picture;

    /**
     * Constructs a new letter.
     *
     * @param picture
     */
    CardPictures(String picture) {
        this.picture = picture;
    }

    /**
     * Gets the {@code picture} of a {@link CardPictures}
     *
     * @return the {@code picture} of a {@link CardPictures}
     */
    public String getPicture() {
        return picture;
    }
}