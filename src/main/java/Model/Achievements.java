package Model;

/**
 * The class {@link Achievements} keeps track of every {@link Player}'s achievements
 */
public class Achievements {

    /**
     * Stores the amount of total pairs found.
     */
    int pairCounterTotal;

    /**
     * Stores the amount of pairs found in a streak.
     */
    int pairCounterStreak;

    /**
     * Stores the highScore.
     */
    int highScore;

    /**
     * Stores the amount of total games won.
     */
    int gameCounter;

    /**
     * Stores the sum of the games that the player already joined in
     */
    int gameSum;

    /**
     * Stores the text for the current achievement.
     */
    String currentAchievements;

    /**
     * Constructs a new achievement
     */
    public Achievements() {
        this.pairCounterTotal = 0;
        this.gameCounter = 0;
    }

    /**
     * Updates all instance-achievements.
     * An instance-achievement can be earned in every game.
     *
     * @return weather a new achievement was earned
     */
    public boolean updateInstanceAchievements() {
        updatePairCounters();
        clearCurrentAchievement();
        checkFoundPairsTotal();
        checkFoundPairsStreak();
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
     * Updates all global-achievements.
     * A global-achievement can be earned once.
     *
     * @return whether a new achievement was earned
     */
    public boolean updateGlobalAchievements() {
        gameCounter++;
        clearCurrentAchievement();
        checkGamesWon();
        return !(currentAchievements.isEmpty());
    }

    /**
     * Checks whether a new milestone was hit regarding won games.
     */
    public void checkGamesWon() {
        if (gameCounter == 1) {
            currentAchievements = currentAchievements + "won a game!\n";
        }
        if (gameCounter == 2) {
            currentAchievements = currentAchievements + "won two games!\n";
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
    public int getGameCounter() {
        return gameCounter;
    }

    /**
     * Sets a new gameSum
     *
     * @param gameSum to be set
     */
    public void setGameSum(int gameSum){
        this.gameSum = gameSum;
    }

    /**
     * Gets the number of times that you joined games
     * @return
     */
    public int getGameSum(){
        return this.gameSum;
    }

    /**
     * Updates the sum of games that player joined in total, gameSum +1
     *
     */
    public void updateGameSum(){
        this.gameSum++;
    }
}