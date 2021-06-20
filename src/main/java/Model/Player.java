package Model;

import java.util.*;

/**
 * This class implements {@link Player} of the {@link Game}.
 */
public class Player {

    /**
     * Stores the name of a {@link Player}.
     */
    String name;

    /**
     * Stores the score of a {@link Player}.
     */
    int score;

    /**
     * Stores the next {@link Player}.
     */
    Player next;

    /**
     * Stores the last {@link Player}.
     */
    Player rear;

    /**
     * Stores all {@link Card}'s a {@link Player} has found.
     */
    List<Card> foundCards;

    /**
     * Constructs a new {@link Player}.
     *
     * @param name  is the name of the {@link Player}
     * @param score the score of a {@link Player}
     * @param next  the next {@link Player}
     * @param rear  the last @link Player}
     */
    public Player(String name, int score, Player next, Player rear) {
        this.name = name;
        this.score = score;
        this.next = next;
        this.rear = rear;
        this.foundCards = new ArrayList<>();
    }

    /**
     * Gets the name of a {@link Player}.
     *
     * @return {@code name}
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the the score of a {@link Player}.
     *
     * @return the score of a {@link Player}
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score for a {@link Player}.
     *
     * @param score of a Model.Enums.Player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the next {@link Player}.
     *
     * @return the next {@link Player}
     */
    public Player getNext() {
        return next;
    }

    /**
     * Gets the last {@link Player}.
     *
     * @return the rear {@link Player}
     */
    public Player getRear() {
        return rear;
    }

    /**
     * Returns all {@link Card}'s a {@link Player} has found.
     *
     * @return all found {@link Card}'s
     */
    public List<Card> getFoundCards() {
        return foundCards;
    }

    /**
     * Increments the {@code score} by 1.
     */
    public void addScore() { // +1
        this.score++;
    }
}