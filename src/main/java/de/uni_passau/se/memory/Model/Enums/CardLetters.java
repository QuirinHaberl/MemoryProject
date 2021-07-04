package de.uni_passau.se.memory.Model.Enums;

import de.uni_passau.se.memory.Model.Card;

/**
 * This is the {@link CardSet} with letters.
 */
public enum CardLetters {

    /**
     * A {@link Card} can have values from [A, Z] and [a, z].
     */
    A("A", "Card_Apple"), B("B", "Card_Armor"),
    C("C", "Card_Arrow"), D("D", "Card_Axe"),
    E("E", "Card_BlueGem"),
    F("F", "Card_BluePotion"), G("G", "Card_Bone"),
    H("H", "Card_Bone2"), I("I", "Card_Book"),
    J("J", "Card_Bow"),
    K("K", "Card_Caldron"), L("L", "Card_Carrot"),
    M("M", "Card_Clover"), N("N", "Card_Crown"),
    O("O", "Card_Diamond"),
    P("P", "Card_Gold"), Q("Q", "Card_GreenGem"),
    R("R", "Card_GreenPotion"), S("S", "Card_Helmet"),
    T("T", "Card_Letter"),
    U("U", "Card_Mushroom"), V("V", "Card_PinkGem"),
    W("W", "Card_RedGem"), X("X", "Card_RedPotion"),
    Y("Y", "Card_Ring"),
    Z("Z", "Card_Shield1"), a("a", "Card_Shield2"),
    b("b", "Card_Skull"), c("c", "Card_Sword"),
    d("d", "Card_Trident"),
    e("e", "Card_Trophy"), f("f", "Card_Wand"),
    g("g", "Card_Apple"), h("h", "Card_Apple"),
    i("i", "Card_Apple"),
    j("j", "Card_Apple"), k("k", "Card_Apple"),
    l("l", "Card_Apple"), m("m", "Card_Apple"),
    n("n", "Card_Apple"),
    o("o", "Card_Apple"), p("p", "Card_Apple"),
    q("q", "Card_Apple"), r("r", "Card_Apple"),
    s("s", "Card_Apple"),
    t("t", "Card_Apple"), u("u", "Card_Apple"),
    v("v", "Card_Apple"), w("w", "Card_Apple"),
    x("x", "Card_Apple"),
    y("y", "Card_Apple"), z("z", "Card_Apple");


    /**
     * The value of a card is stored in {@code letter}, which can't be changed.
     */
    private final String letter;

    /**
     * The value of a card is stored in {@code picture}, which can't be changed.
     */
    private final String picture;

    /**
     * Constructs a new letter.
     *
     * @param letter is saved as a attribute for a {@link CardLetters}
     * @param picture
     */
    CardLetters(String letter, String picture) {
        this.letter = letter;
        this.picture = picture;
    }



    /**
     * Gets the {@code letter} of a {@link CardLetters}
     *
     * @return the {@code letter} of a {@link CardLetters}
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Gets the {@code picture} of a {@link CardLetters}
     *
     * @return the {@code picture} of a {@link CardLetters}
     */
    public String getPicture(){
        return picture;
    }
}