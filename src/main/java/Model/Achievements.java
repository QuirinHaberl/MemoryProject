package Model;

public class Achievements {

    int pairCounterTotal;
    int pairCounterStreak;
    int gameCounter;
    String currentAchievements;

    public Achievements() {
        this.pairCounterTotal = 0;
        this.gameCounter = 0;
    }


    public boolean updateInstanceAchievements() {
        updatePairCounters();
        clearCurrentAchievement();
        checkFoundPairsTotal();
        checkFoundPairsStreak();
        return !(currentAchievements.isEmpty());
    }

    public void checkFoundPairsTotal() {
        if (pairCounterTotal == 2) {
            currentAchievements = currentAchievements + "Found two pairs in total!\n";
        }

        if (pairCounterTotal == 5) {
            currentAchievements = currentAchievements + "Found five pairs in total!\n";
        }
    }

    public void checkFoundPairsStreak() {
        if (pairCounterStreak == 2) {
            currentAchievements = currentAchievements + "Found two pairs in a row!\n";
        }

        if (pairCounterStreak == 5) {
            currentAchievements = currentAchievements + "Found five pairs in a row!\n";
        }
    }

    public void updatePairCounters() {
        pairCounterTotal++;
        pairCounterStreak++;
    }

    public void clearCurrentAchievement() {
        currentAchievements = "";
    }

    public boolean updateGlobalAchievements() {
        gameCounter++;
        clearCurrentAchievement();
        checkGamesWon();
        return !(currentAchievements.isEmpty());
    }

    public void checkGamesWon() {
        if (gameCounter == 1) {
            currentAchievements = currentAchievements + "won a game!\n";
        }
        if (gameCounter == 2) {
            currentAchievements = currentAchievements + "won two games!\n";
        }
    }

    public String getCurrentAchievement() {
        return currentAchievements;
    }

    public void setCurrentAchievement(String currentAchievement) {
        this.currentAchievements = currentAchievement;
    }

    public int getPairCounterStreak() {
        return pairCounterStreak;
    }

    public void setPairCounterStreak(int pairCounterStreak) {
        this.pairCounterStreak = pairCounterStreak;
    }

    public void resetPairCounterStreak() {
        this.pairCounterStreak = 0;
    }
}
