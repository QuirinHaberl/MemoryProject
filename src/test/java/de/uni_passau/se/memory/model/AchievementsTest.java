package de.uni_passau.se.memory.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AchievementsTest {

    private static final int TEST_AMOUNT = 5;
    private final Achievements achievements = new Achievements();

    @Test
    void updateGamesPlayedTest() {
        int amountPlayedGames = TEST_AMOUNT;
        achievements.setGamesPlayed(amountPlayedGames);
        achievements.updateGamesPlayed();
        assertEquals(achievements.getGamesPlayed(), amountPlayedGames + 1,
                "Amount amount should be 6!");
    }

    @Test
    void updateGamesWonTest() {
        int amountPlayedWon = TEST_AMOUNT;
        achievements.setGamesWon(amountPlayedWon);
        achievements.updateGamesWon();
        assertEquals(achievements.getGamesWon(), amountPlayedWon + 1,
                "Amount should be 6!");
    }

    @Test
    void updatePairCountersTest() {
        int pairCounterTotal = TEST_AMOUNT;
        achievements.setPairCounterTotal(pairCounterTotal);
        int pairCounterStreak = TEST_AMOUNT;
        achievements.setPairCounterStreak(pairCounterStreak);

        achievements.updatePairCounters();

        assertEquals(achievements.getPairCounterTotal(), pairCounterTotal + 1
                , "Total should be 6!");
        assertEquals(achievements.getPairCounterStreak(),
                pairCounterStreak + 1, "Streak should be 6!");
    }

    @Test
    void clearCurrentAchievementTest() {
        String achievementText = "Test";
        assertTrue(achievements.getCurrentAchievements().isEmpty(),
                "Achievement should not be empty!");
        achievements.setCurrentAchievements(achievementText);
        assertEquals(achievements.getCurrentAchievements(), achievementText,
                "Achievements should be equal!");
        achievements.clearCurrentAchievement();
        assertTrue(achievements.getCurrentAchievements().isEmpty(),
                "Achievement should be empty!");
    }

    @Test
    void checkFoundPairsTotalTest() {
        String achievementText;

        for (int i = 2; i <= 10; i = i + 2) {
            achievements.clearCurrentAchievement();
            achievementText = "Found " + i + " pairs in total!\n";
            achievements.setPairCounterTotal(i);
            achievements.checkFoundPairsTotal();
            assertEquals(achievements.getCurrentAchievements(),
                    achievementText, "Achievements should be equal!");
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
            assertEquals(achievements.getCurrentAchievements(),
                    achievementText, "Achievements should be equal!");
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
            assertEquals(achievements.getCurrentAchievements(),
                    achievementText, "Achievements should be equal!");
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
            assertEquals(achievements.getCurrentAchievements(),
                    achievementText, "Achievements should be equal!");
        }
    }
}
