package de.uni_passau.se.memory.Model;

/**
 * The class {@link Achievements} keeps track of every {@link Player}'s achievements
 */
public class Achievements {

    /**
     * Stores the amount of total pairs found.
     */
    private int pairCounterTotal;

    /**
     * Stores the amount of pairs found in a streak.
     */
    private int pairCounterStreak;

    /**
     * Stores the highScore.
     */
    private int highScore;

    /**
     * Stores the amount of total games won.
     */
    private int gamesWon;

    /**
     * Stores the sum of the games that the player already joined in
     */
    private int gamesPlayed;

    /**
     * Stores the text for the current achievement.
     */
    private String currentAchievements="";

    /**
     * Constructs a new achievement
     */
    public Achievements() {
        this.pairCounterTotal = 0;
        this.gamesWon = 0;
    }

    /**
     * Updates all instance-achievements.
     * An instance-achievement can be earned in every game.
     *
     * @return weather a new achievement was earned
     */
    public boolean updateInstanceAchievements() {
        return !(currentAchievements.isEmpty());
    }

    /**
     * Checks weather a new milestone was hit regarding found pairs in total.
     */
    public void checkFoundPairsTotal() {
        if (pairCounterTotal == 2) {
            currentAchievements = currentAchievements + "Found two pairs in total!\n";
        }

        if (pairCounterTotal == 5) {
            currentAchievements = currentAchievements + "Found five pairs in total!\n";
        }
    }

    /**
     * Checks weather a new milestone was hit regarding found pairs in a row.
     */
    public void checkFoundPairsStreak() {
        if (pairCounterStreak == 2) {
            currentAchievements = currentAchievements + "Found two pairs in a row!\n";
        }

        if (pairCounterStreak == 5) {
            currentAchievements = currentAchievements + "Found five pairs in a row!\n";
        }
    }

    /**
     * Updates the amount of pairs found.
     */
    public void updatePairCounters() {
        pairCounterTotal++;
        pairCounterStreak++;
    }

    /**
     * Removes the text of the last earned achievement.
     */
    public void clearCurrentAchievement() {
        currentAchievements = "";
    }


    /**
     * Checks whether a new milestone was hit regarding won games.
     */
    public void checkGamesWon() {
        if (gamesWon == 1) {
            currentAchievements = currentAchievements + "won his/her first game!\n";
        }
        if (gamesWon == 2) {
            currentAchievements = currentAchievements + "won his/her second games!\n";
        }
    }

    /**
     * Gets the current achievement.
     *
     * @return the current achievement
     */
    public String getCurrentAchievement() {
        return currentAchievements;
    }

    /**
     * Resets the streak of found pairs.
     */
    public void resetPairCounterStreak() {
        this.pairCounterStreak = 0;
    }

    /**
     * Sets a new highScore
     *
     * @param highScore to be set
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Gets the number of won games.
     *
     * @return the number of won games.
     */
    public int getGameWon() {
        return gamesWon;
    }

    public int getPairCounterTotal() {
        return pairCounterTotal;
    }

    public void setPairCounterTotal(int pairCounterTotal) {
        this.pairCounterTotal = pairCounterTotal;
    }

    public int getPairCounterStreak() {
        return pairCounterStreak;
    }

    public void setPairCounterStreak(int pairCounterStreak) {
        this.pairCounterStreak = pairCounterStreak;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setGamesWon(int gameCounter) {
        this.gamesWon = gameCounter;
    }

    public String getCurrentAchievements() {
        return currentAchievements;
    }

    public void setCurrentAchievements(String currentAchievements) {
        this.currentAchievements = currentAchievements;
    }

    /**
     * Sets a new gameSum
     *
     * @param gamesPlayed to be set
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets the number of times that you joined games
     *
     * @return
     */
    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    /**
     * Updates the sum of games that player joined in total, gameSum +1
     */
    public void updateGamesPlayed() {
        this.gamesPlayed++;
    }

    /**
     * //TODO JavaDoc
     **/
    public void updateGamesWon() {
        gamesWon++;
    }

    public void checkHighScore(int score) {
        if (score < getHighScore()) {
            setHighScore(score);
        }
        //TODO Add new highScore achievements
    }
}