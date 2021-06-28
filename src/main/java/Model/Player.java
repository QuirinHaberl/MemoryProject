package Model;

import java.util.*;

/**
 * This class implements {@link Player} of the {@link Game}.
 */
public class Player {

    /**
     * Stores the id of a {@link Player}.
     */
    private String id;

    /**
     * Stores the name of a {@link Player}.
     */
    private String name;

    /**
     * Stores the score of a {@link Player}.
     */
    private int score;

    /**
     * Stores the lifes of a {@link Player}.
     * The default number of lifes is 5.
     */
    private int lifes = 5;

    /**
     * Stores the achievments of a {@link Player}
     */
    private Achievements achievements = new Achievements();

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
        //TODO ID
        this.name = name;
        this.score = score;
        this.next = next;
        this.rear = rear;
        this.foundCards = new ArrayList<>();
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * Sets the name for a {@link Player}.
     *
     * @param name of a Model.Enums.Player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the next {@link Player}.
     *
     * @param next {@link Player}
     */
    public void setNext(Player next) {
        this.next = next;
    }

    /**
     * Sets the last {@link Player}.
     *
     * @param rear of the {@link Player}
     */
    public void setRear(Player rear) {
        this.rear = rear;
    }

    /**
     * Gets the lifes of a {@link Player}.
     *
     * @return the lifes of a {@link Player}.
     */
    public int getLifes() {
        return lifes;
    }

    /**
     * Reduces the lifes of a {@link Player}.
     */
    public void reduceLifes() {
        this.lifes = lifes - 1;
    }

    /**
     * Sets the lifes of a {@link Player}.
     *
     * @param lifes of a {@link Player}.
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    /**
     * Gets the earned achievements of a {@link Player}.
     *
     * @return the earned achievements of a {@link Player}
     */
    public Achievements getAchievements() {
        return achievements;
    }

    public String getPlayerProfile() {
        String profile = this.name + ", highest score: " + this.achievements.getHighScore() + ", " + " has played " + this.achievements.getGamesPlayed() + " times.";
        return profile;
    }


}