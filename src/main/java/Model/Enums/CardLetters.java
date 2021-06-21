package Model.Enums;

import Model.Card;

/**
 * This is the {@link CardSet} with letters.
 */
public enum CardLetters {

    /**
     * A {@link Card} can have values from [A, Z] and [a, z].
     */
    A("A"), B("B"), C("C"), D("D"), E("E"),
    F("F"), G("G"), H("H"), I("I"), J("J"),
    K("K"), L("L"), M("M"), N("N"), O("O"),
    P("P"), Q("Q"), R("R"), S("S"), T("T"),
    U("U"), V("V"), W("W"), X("X"), Y("Y"),
    Z("Z"), a("a"), b("b"), c("c"), d("d"),
    e("e"), f("f"), g("g"), h("h"), i("i"),
    j("j"), k("k"), l("l"), m("m"), n("n"),
    o("o"), p("p"), q("q"), r("r"), s("s"),
    t("t"), u("u"), v("v"), w("w"), x("x"),
    y("y"), z("z");


    /**
     * The value of a card is stored in {@code letter}, which can't be changed.
     */
    private final String letter;

    /**
     * Constructs a new letter.
     *
     * @param letter is saved as a attribute for a {@link CardLetters}
     */
    CardLetters(String letter) {
        this.letter = letter;
    }

    /**
     * Gets the {@code letter} of a {@link CardLetters}
     *
     * @return the {@code letter} of a {@link CardLetters}
     */
    public String getLetter() {
        return letter;
    }
}