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
     * Different numbers of pairs
     */
    public static final int TWO_PAIRS = 2;
    public static final int FOUR_PAIRS = 4;
    public static final int SIX_PAIRS = 6;
    public static final int EIGHT_PAIRS = 8;
    public static final int TEN_PAIRS = 10;
    public static final int TWELVE_PAIRS = 12;
    public static final int SIXTEEN_PAIRS = 16;

    /**
     * Different numbers of games won
     */
    public static final int ONE_GAME_WON = 1;
    public static final int TWO_GAMES_WON = 2;
    public static final int THREE_GAMES_WON = 3;
    public static final int FOUR_GAMES_WON = 4;
    public static final int FIVE_GAMES_WON = 5;

    /**
     * Constructs a new object of {@link Achievements}.
     */
    public Achievements() {
        this.pairCounterTotal = 0;
        this.pairCounterStreak = 0;
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
     * Gets the number of won games.
     *
     * @return the number of won games.
     */
    public int getGamesWon() {
        return gamesWon;
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
     * Gets the personal highScore.
     *
     * @return the the personal highScore
     */
    public int getHighScore() {
        return highScore;
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
     * Gets the current achievement-massage.
     *
     * @return the current achievement-massage
     */
    public String getCurrentAchievements() {
        return currentAchievements;
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
     * Sets a new gameSum
     *
     * @param gamesPlayed to be set
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
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
        if (pairCounterTotal == TWO_PAIRS) {
            currentAchievements = currentAchievements + "Found 2 pairs in total!\n";
        } else if (pairCounterTotal == FOUR_PAIRS) {
            currentAchievements = currentAchievements + "Found 4 pairs in total!\n";
        } else if (pairCounterTotal == SIX_PAIRS) {
            currentAchievements = currentAchievements + "Found 6 pairs in total!\n";
        } else if (pairCounterTotal == EIGHT_PAIRS) {
            currentAchievements = currentAchievements + "Found 8 pairs in total!\n";
        } else if (pairCounterTotal == TEN_PAIRS) {
            currentAchievements = currentAchievements + "Found 10 pairs in total!\n";
        }
    }

    /**
     * Checks weather a new milestone was hit regarding found pairs in a row.
     */
    public void checkFoundPairsStreak() {
        if (pairCounterStreak == TWO_PAIRS) {
            currentAchievements = currentAchievements + "Found 2 pairs in a row!\n";
        } else if (pairCounterStreak == FOUR_PAIRS) {
            currentAchievements = currentAchievements + "Found 4 pairs in a row!\n";
        } else if (pairCounterStreak == SIX_PAIRS) {
            currentAchievements = currentAchievements + "Found 6 pairs in a row!\n";
        } else if (pairCounterStreak == EIGHT_PAIRS) {
            currentAchievements = currentAchievements + "Found 8 pairs in a row!\n";
        } else if (pairCounterStreak == TEN_PAIRS) {
            currentAchievements = currentAchievements + "Found 10 pairs in a row!\n";
        }
    }

    /**
     * Checks whether a new milestone was hit regarding won games.
     */
    public void checkGamesWon() {
        if (gamesWon == ONE_GAME_WON) {
            currentAchievements = currentAchievements + "1. game won!\n";
        } else if (gamesWon == TWO_GAMES_WON) {
            currentAchievements = currentAchievements + "2. game won!\n";
        } else if (gamesWon == THREE_GAMES_WON) {
            currentAchievements = currentAchievements + "3. game won!\n";
        } else if (gamesWon == FOUR_GAMES_WON) {
            currentAchievements = currentAchievements + "4. game won!\n";
        } else if (gamesWon == FIVE_GAMES_WON) {
            currentAchievements = currentAchievements + "5. game won!\n";
        }
    }

    /**
     * Checks whether a highScore was earned and generates a achievement.
     */
    public void checkHighScore() {
        if (pairCounterTotal > getHighScore()) {
            setHighScore(pairCounterTotal);
            if (pairCounterTotal == FOUR_PAIRS) {
                currentAchievements = currentAchievements + "New PB of 4!\n";
            } else if (pairCounterTotal == EIGHT_PAIRS) {
                currentAchievements = currentAchievements + "New PB of 8!\n";
            } else if (pairCounterTotal == TWELVE_PAIRS) {
                currentAchievements = currentAchievements + "New PB of 12!\n";
            } else if (pairCounterTotal == SIXTEEN_PAIRS) {
                currentAchievements = currentAchievements + "New PB of 16!\n";
            }
        }
    }
}
