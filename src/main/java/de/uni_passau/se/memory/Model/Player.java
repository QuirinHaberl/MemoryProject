package de.uni_passau.se.memory.Model;

import java.util.*;

/**
 * This class implements {@link Player} of the {@link Game}.
 */
public class Player {

    /**
     * Stores the playerId of a {@link Player}.
     */
    private String playerId;

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
     * Stores the achievements of a {@link Player}
     */
    private final Achievements achievements = new Achievements();

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
        //this.playerId = UUID.randomUUID().toString();
        this.name = name;
        this.score = score;
        this.next = next;
        this.rear = rear;
        this.foundCards = new ArrayList<>();
    }

    /**
     * Gets the playerId.
     *
     * @return the playerId
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Sets the playerId
     *
     * @param playerId to be set
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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
     * @param score of a player
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
     * @param name of a player
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
    public int getlifes() {
        return lifes;
    }

    /**
     * Reduces the lifes of a {@link Player}.
     */
    public void reducelifes() {
        this.lifes = lifes - 1;
    }

    /**
     * Sets the lifes of a {@link Player}.
     *
     * @param lifes of a {@link Player}.
     */
    public void setlifes(int lifes) {
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

    /**
     * Generates a String of a playerProfile.
     *
     * @return the playerProfile as a String
     */
    public String playerProfileToString() {
        return (this.name + ", highest score: " +
                this.achievements.getHighScore() + ", " + " has played " +
                this.achievements.getGamesPlayed() + " times.");
    }
}