package Model;

/**
 * this class implements the Model.Enums.Player of the game.
 */
public class Player {

    /**
     * the attribute name is the name of a Model.Enums.Player
     */
    String name;

    /**
     * the attribute score is the score that a Model.Enums.Player got in the game
     */
    int score;

    /**
     * the attribute Model.Enums.Player next is used for connecting to the next Model.Enums.Player
     */
    Player next;

    /**
     * the attribute Model.Enums.Player rear is used for connecting to the rear Model.Enums.Player
     */
    Player rear;

    /**
     * constructs a new {@link Player}
     *
     * @param name  is the name of the {@link Player}
     * @param score the score of a {@link Player}
     * @param next  the next {@link Player}
     */
    public Player(String name, int score, Player next, Player rear) {
        this.name = name;
        this.score = score;
        this.next = next;
        this.rear = rear;
    }

    /**
     * set a name for Model.Enums.Player
     *
     * @param name of a Model.Enums.Player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name of a {@link Player}
     */
    public String getName() {
        return name;
    }

    /**
     * set the score for a Model.Enums.Player
     *
     * @param score of a Model.Enums.Player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * if a Model.Enums.Player found two same card, then the score of the Model.Enums.Player will added 1.
     */
    public void addScore() { // +1
        this.score++;
    }

    /**
     * @return the score of a {@link Player}
     */
    public int getScore() {
        return score;
    }

    /**
     * sets the next Model.Enums.Player
     *
     * @param next of the {@link Player}
     */
    public void setNext(Player next) {
        this.next = next;
    }

    /**
     * @return the next {@link Player}
     */
    public Player getNext() {
        return next;
    }

    /**
     * @return the rear {@link Player}
     */
    public Player getRear() {
        return rear;
    }

    /**
     * sets the rear Model.Enums.Player
     *
     * @param rear of the {@link Player}
     */
    public void setRear(Player rear) {
        this.rear = rear;
    }
}