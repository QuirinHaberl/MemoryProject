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
    private String currentAchievements = "";

    /**
     * Constructs a new object of {@link Achievements}.
     */
    public Achievements() {
        this.pairCounterTotal = 0;
        this.pairCounterStreak = 0;
    }

    /**
     * Sets {@code pairCounterTotal}.
     *
     * @param pairCounterTotal to be set
     */
    public void setPairCounterTotal(int pairCounterTotal) {
        this.pairCounterTotal = pairCounterTotal;
    }

    /**
     * Sets {@code pairCounterStreak}.
     *
     * @param pairCounterStreak to be set
     */
    public void setPairCounterStreak(int pairCounterStreak) {
        this.pairCounterStreak = pairCounterStreak;
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
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Gets the personal highScore.
     *
     * @return the the personal highScore
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Sets {@code gamesWon} to a given value.
     *
     * @param gameCounter to be set
     */
    public void setGamesWon(int gameCounter) {
        this.gamesWon = gameCounter;
    }

    /**
     * Gets the current achievement-massage.
     *
     * @return the current achievement-massage
     */
    public String getCurrentAchievements() {
        return currentAchievements;
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
     * Gets the number of games played.
     *
     * @return the number of games played
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
     * Increments {@code gamesWon}.
     */
    public void updateGamesWon() {
        this.gamesWon++;
    }

    /**
     * Updates the amount of pairs found.
     */
    public void updatePairCounters() {
        this.pairCounterTotal++;
        this.pairCounterStreak++;
    }

    /**
     * Removes the text of the last earned achievement.
     */
    public void clearCurrentAchievement() {
        currentAchievements = "";
    }

    /**
     * Checks weather a new milestone was hit regarding found pairs in total.
     */
    public void checkFoundPairsTotal() {
        if (pairCounterTotal == 2) {
            currentAchievements = currentAchievements + "Found 2 pairs in total!\n";
        } else if (pairCounterTotal == 4) {
            currentAchievements = currentAchievements + "Found 4 pairs in total!\n";
        } else if (pairCounterTotal == 6) {
            currentAchievements = currentAchievements + "Found 6 pairs in total!\n";
        } else if (pairCounterTotal == 8) {
            currentAchievements = currentAchievements + "Found 8 pairs in total!\n";
        } else if (pairCounterTotal == 10) {
            currentAchievements = currentAchievements + "Found 10 pairs in total!\n";
        }
    }

    /**
     * Checks weather a new milestone was hit regarding found pairs in a row.
     */
    public void checkFoundPairsStreak() {
        if (pairCounterStreak == 2) {
            currentAchievements = currentAchievements + "Found 2 pairs in a row!\n";
        } else if (pairCounterStreak == 4) {
            currentAchievements = currentAchievements + "Found 4 pairs in a row!\n";
        } else if (pairCounterStreak == 6) {
            currentAchievements = currentAchievements + "Found 6 pairs in a row!\n";
        } else if (pairCounterStreak == 8) {
            currentAchievements = currentAchievements + "Found 8 pairs in a row!\n";
        } else if (pairCounterStreak == 10) {
            currentAchievements = currentAchievements + "Found 10 pairs in a row!\n";
        }
    }

    /**
     * Checks whether a new milestone was hit regarding won games.
     */
    public void checkGamesWon() {
        if (gamesWon == 1) {
            currentAchievements = currentAchievements + "1. game won!\n";
        } else if (gamesWon == 2) {
            currentAchievements = currentAchievements + "2. game won!\n";
        } else if (gamesWon == 3) {
            currentAchievements = currentAchievements + "3. game won!\n";
        } else if (gamesWon == 4) {
            currentAchievements = currentAchievements + "4. game won!\n";
        } else if (gamesWon == 5) {
            currentAchievements = currentAchievements + "5. game won!\n";
        }
    }

    /**
     * Checks whether a highScore was hit and generates a achievement.
     */
    public void checkHighScore() {
        if (pairCounterTotal > getHighScore()) {
            setHighScore(pairCounterTotal);
            if (pairCounterTotal == 4) {
                currentAchievements = currentAchievements + "New PB of 4!\n";
            } else if (pairCounterTotal == 8) {
                currentAchievements = currentAchievements + "New PB of 8!\n";
            } else if (pairCounterTotal == 12) {
                currentAchievements = currentAchievements + "New PB of 12!\n";
            } else if (pairCounterTotal == 16) {
                currentAchievements = currentAchievements + "New PB of 16!\n";
            }
        }
    }
}