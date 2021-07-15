package de.uni_passau.se.memory.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        achievements.clearCurrentAchievement();

        achievementText = "Found 2 pairs in total!\n";
        achievements.setPairCounterTotal(2);
        achievements.checkFoundPairsTotal();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();

        achievementText = "Found 10 pairs in total!\n";
        achievements.setPairCounterTotal(10);
        achievements.checkFoundPairsTotal();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();
    }

    @Test
    void checkFoundPairsStreakTest() {
        String achievementText;
        achievements.clearCurrentAchievement();

        achievementText = "Found 2 pairs in a row!\n";
        achievements.setPairCounterStreak(2);
        achievements.checkFoundPairsStreak();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();

        achievementText = "Found 10 pairs in a row!\n";
        achievements.setPairCounterStreak(10);
        achievements.checkFoundPairsStreak();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();
    }

    @Test
    void checkGamesWonTest() {
        String achievementText;
        achievements.clearCurrentAchievement();

        achievementText = "1. game won!\n";
        achievements.setGamesWon(1);
        achievements.checkGamesWon();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();

        achievementText = "5. game won!\n";
        achievements.setGamesWon(5);
        achievements.checkGamesWon();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();

    }

    @Test
    void checkHighScoreTest() {
        String achievementText;
        achievements.clearCurrentAchievement();
        achievements.setHighScore(2);

        achievementText = "New PB of 4!\n";
        achievements.setPairCounterTotal(4);
        achievements.checkHighScore();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();

        achievementText = "New PB of 16!\n";
        achievements.setPairCounterTotal(16);
        achievements.checkHighScore();
        assertEquals(achievements.getCurrentAchievements(), achievementText);
        achievements.clearCurrentAchievement();
    }
}
