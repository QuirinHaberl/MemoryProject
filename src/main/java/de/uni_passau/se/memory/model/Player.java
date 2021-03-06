package de.uni_passau.se.memory.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements {@link Player} of the {@link Game}.
 */
public class Player {

    /**
     * Stores the achievements of a {@link Player}
     */
    private final Achievements achievements;
    /**
     * Stores all {@link Card}'s a {@link Player} has found.
     */
    private final List<Card> foundCards;
    /**
     * Stores the next {@link Player}.
     */
    private Player next;
    /**
     * Stores the last {@link Player}.
     */
    private Player rear;
    /**
     * Stores the name of a {@link Player}.
     */
    private String name;

    /**
     * Stores the score of a {@link Player}.
     */
    private int score;

    /**
     * Stores the lives of a {@link Player}.
     * The default number of lives is 5.
     */
    private int lives = 5;

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
        this.achievements = new Achievements();
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
     * Sets the name for a {@link Player}.
     *
     * @param name of a player
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the next player.
     *
     * @param next to be set
     */
    public void setNext(Player next) {
        this.next = next;
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
     * Sets the previous player.
     *
     * @param rear to be set
     */
    public void setRear(Player rear) {
        this.rear = rear;
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
    public void updateScore() { // +1
        this.score++;
    }

    /**
     * Gets the lives of a {@link Player}.
     *
     * @return the lives of a {@link Player}.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the lives of a {@link Player}.
     *
     * @param lives of a {@link Player}.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Reduces the lives of a {@link Player}.
     */
    public void reduceLives() {
        this.lives = lives - 1;
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
    public String profileToString() {
        return (this.name + ", highest score: " +
                this.achievements.getHighScore() + ", " + " has played " +
                this.achievements.getGamesPlayed() + " times.");
    }

    /**
     * Gets the highScore of a player.
     *
     * @return highScore of a player
     */
    public int getHighScore() {
        return achievements.getHighScore();
    }

    /**
     * Sets the highScore.
     *
     * @param highScore to be set
     */
    public void setHighScore(int highScore) {
        achievements.setHighScore(highScore);
    }

    /**
     * Gets the amount of won games.
     *
     * @return amount of won games.
     */
    public int getGamesWon() {
        return achievements.getGamesWon();
    }

    /**
     * Sets the amount of won games.
     *
     * @param gamesWon to be set.
     */
    public void setGamesWon(int gamesWon) {
        achievements.setGamesWon(gamesWon);
    }

    /**
     * Gets the amount of played games.
     *
     * @return amount of played games.
     */
    public int getGamesPlayed() {
        return achievements.getGamesPlayed();
    }

    /**
     * Sets the amount of played games.
     *
     * @param gamesPlayed to bes set
     */
    public void setGamesPlayed(int gamesPlayed) {
        achievements.setGamesPlayed(gamesPlayed);
    }

    /**
     * Increments played games by one.
     */
    public void updateGamesPlayed() {
        achievements.updateGamesPlayed();
    }

    /**
     * Increments won games by one.
     */
    public void updateGamesWon() {
        achievements.updateGamesWon();
    }

    /**
     * Gets the last earned achievement.
     *
     * @return the last achievement earned
     */
    public String getCurrentAchievement() {
        return achievements.getCurrentAchievements();
    }

    /**
     * Clears the current achievement.
     */
    public void clearCurrentAchievement() {
        achievements.clearCurrentAchievement();
    }

    /**
     * Increments both pairCounters.
     */
    public void updatePairCounters() {
        achievements.updatePairCounters();
    }

    /**
     * Checks whether a new achievement was earned.
     * Checks pairCounterTotal.
     */
    public void checkFoundPairsTotal() {
        achievements.checkFoundPairsTotal();
    }

    /**
     * Checks whether a new achievement was earned.
     * Checks pairCounterStreak.
     */
    public void checkFoundPairsStreak() {
        achievements.checkFoundPairsStreak();
    }

    /**
     * Checks whether a new achievement was earned.
     * Checks highScore.
     */
    public void checkHighScore() {
        achievements.checkHighScore();
    }

    /**
     * Checks whether a new achievement was earned.
     * Checks gamesWon.
     */
    public void checkGamesWon() {
        achievements.checkGamesWon();
    }

    /**
     * Sets pairCounterStreak for a given player.
     *
     * @param achievements of the given player
     * @param streak       to be set
     */
    public void setPlayerPairCounterStreak(Achievements achievements,
                                           int streak) {
        achievements.setPairCounterStreak(streak);
    }

    /**
     * Resets both pairCounters to 0.
     */
    public void resetAchievements() {
        achievements.setPairCounterStreak(0);
        achievements.setPairCounterTotal(0);
    }
}
