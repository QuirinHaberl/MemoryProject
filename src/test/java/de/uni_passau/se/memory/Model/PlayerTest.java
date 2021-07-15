package de.uni_passau.se.memory.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private Player player;
    private final String player1 = "player1";
    private final int score = 10;

    @BeforeEach
    void setUp() throws Exception {
        player = new Player(player1, score, null, null);
    }

    @Test
    void updateScore() {
        player.updateScore();

        assertEquals(11, player.getScore(), "the score is not updated!");
    }

    @Test
    void reduceLives() {
        player.reduceLives();

        assertEquals(4, player.getLives(), "the life was not reduced!");
    }

    @Test
    void playerProfileToString() {
        player.getAchievements().setHighScore(10);
        player.getAchievements().setGamesPlayed(5);
        String profile1 = player.getName() + ", highest score: " +
                player.getAchievements().getHighScore() + "," + " has played " +
                player.getAchievements().getGamesPlayed() + " times.";
        String profile = "player1, highest score: 10,  has played 5 times.";
        assertEquals(profile, player.profileToString(), "hh");
    }

    @Test
    void updateGamesPlayed() {
        player.getAchievements().setGamesPlayed(5);
        player.updateGamesPlayed();

        assertEquals(6, player.getAchievements().getGamesPlayed(), "player1 has player 6 times!");
    }

    @Test
    void updateGamesWon() {
        player.getAchievements().setGamesWon(5);
        player.updateGamesWon();

        assertEquals(6, player.getAchievements().getGamesWon(), " player1 has won 6 times!");
    }

    @Test
    void clearCurrentAchievement() {
        player.getAchievements().setHighScore(2);
        player.getAchievements().setGamesPlayed(5);
        player.getAchievements().setGamesWon(5);

        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();

        player.getAchievements().checkFoundPairsTotal();
        player.getAchievements().checkFoundPairsStreak();
        player.getAchievements().checkGamesWon();
        player.getAchievements().checkHighScore();

        String achievement = "Found 4 pairs in total!\n" + "Found 4 pairs in a row!\n" + "5. game won!\n" + "New PB of 4!\n";

        assertEquals(achievement, player.getCurrentAchievement(), "these are not the same!");
        player.clearCurrentAchievement();

        assertEquals("", player.getCurrentAchievement());
    }

    @Test
    void updatePairCountersTest() {
        Achievements achievements = player.getAchievements();
        achievements.updatePairCounters();
        achievements.updatePairCounters();

        assertEquals(2, player.getAchievements().getPairCounterTotal());
        assertEquals(2, player.getAchievements().getPairCounterStreak());
    }

    @Test
    void checkFoundPairsTotal() {
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();

        player.getAchievements().checkFoundPairsTotal();

        String str = "Found 4 pairs in total!\n";

        assertEquals(str, player.getAchievements().getCurrentAchievements());
    }

    @Test
    void checkFoundPairsStreak() {
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();

        player.getAchievements().checkFoundPairsStreak();

        String str = "Found 4 pairs in a row!\n";

        assertEquals(str, player.getAchievements().getCurrentAchievements());

    }

    @Test
    void checkHighScore() {
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();
        player.getAchievements().updatePairCounters();

        player.getAchievements().setHighScore(2);
        player.getAchievements().checkHighScore();

        String str = "New PB of 4!\n";

        assertEquals(str, player.getAchievements().getCurrentAchievements());
    }

    @Test
    void checkGamesWon() {
        player.getAchievements().setGamesWon(2);
        player.getAchievements().checkGamesWon();

        String str = "2. game won!\n";
    }
}
