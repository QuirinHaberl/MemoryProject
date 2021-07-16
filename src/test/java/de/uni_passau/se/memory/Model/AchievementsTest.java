package de.uni_passau.se.memory.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AchievementsTest {

    private final Achievements achievements = new Achievements();

    @Test
    void updateGamesPlayedTest() {
        int amountPlayedGames = 5;
        achievements.setGamesPlayed(amountPlayedGames);
        achievements.updateGamesPlayed();
        assertEquals(achievements.getGamesPlayed(), amountPlayedGames + 1);
    }

    @Test
    void updateGamesWonTest() {
        int amountPlayedWon = 5;
        achievements.setGamesWon(amountPlayedWon);
        achievements.updateGamesWon();
        assertEquals(achievements.getGamesWon(), amountPlayedWon + 1);
    }

    @Test
    void updatePairCountersTest() {
        int pairCounterTotal = 5;
        achievements.setPairCounterTotal(pairCounterTotal);
        int pairCounterStreak = 3;
        achievements.setPairCounterStreak(pairCounterStreak);

        achievements.updatePairCounters();

        assertEquals(achievements.getPairCounterTotal(), pairCounterTotal + 1);
        assertEquals(achievements.getPairCounterStreak(), pairCounterStreak + 1);
    }

    @Test
    void clearCurrentAchievementTest() {
        String achievementText = "Test";
        assertTrue(achievements.getCurrentAchievements().isEmpty());
        achievements.setCurrentAchievements(achievementText);
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();
        assertTrue(achievements.getCurrentAchievements().isEmpty());
    }

    @Test
    void checkFoundPairsTotalTest() {
        String achievementText;

        for (int i = 2; i <= 10; i = i + 2) {
            achievements.clearCurrentAchievement();
            achievementText = "Found " + i + " pairs in total!\n";
            achievements.setPairCounterTotal(i);
            achievements.checkFoundPairsTotal();
            assertEquals(achievements.getCurrentAchievements(), achievementText);
        }
    }

    @Test
    void checkFoundPairsStreakTest() {
        String achievementText;

        for (int i = 2; i <= 10; i = i + 2) {
            achievements.clearCurrentAchievement();
            achievementText = "Found " + i + " pairs in a row!\n";
            achievements.setPairCounterStreak(i);
            achievements.checkFoundPairsStreak();
            assertEquals(achievements.getCurrentAchievements(), achievementText);
        }
    }

    @Test
    void checkGamesWonTest() {
        String achievementText;

        for (int i = 1; i < 5; i++) {
            achievements.clearCurrentAchievement();
            achievementText = i + ". game won!\n";
            achievements.setGamesWon(i);
            achievements.checkGamesWon();
            assertEquals(achievements.getCurrentAchievements(), achievementText);
        }
    }

    @Test
    void checkHighScoreTest() {
        String achievementText;
        for (int i = 4; i <= 16; i = i + 4) {
            achievements.clearCurrentAchievement();
            achievementText = "New PB of " + i + "!\n";
            achievements.setPairCounterTotal(i);
            achievements.checkHighScore();
            assertEquals(achievements.getCurrentAchievements(), achievementText);
        }
    }
}
