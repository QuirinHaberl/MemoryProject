package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Achievements;
import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.PlayerList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {

    @Test
    void resetAllAchievementsTest() {

        Game game = Wrapper.getGame();
        PlayerList playerList = game.getPlayerList();
        game.addPlayer("test1");
        game.addPlayer("test2");
        GameController gameController = new GameController();
        Achievements test1Achievements =
                playerList.getPlayer(0).getAchievements();
        Achievements test2Achievements =
                playerList.getPlayer(1).getAchievements();

        assertEquals(test1Achievements.getPairCounterStreak(), 0);
        assertEquals(test1Achievements.getPairCounterStreak(), 0);
        assertEquals(test1Achievements.getPairCounterTotal(), 0);
        assertEquals(test1Achievements.getPairCounterTotal(), 0);

        test1Achievements.updatePairCounters();
        test2Achievements.updatePairCounters();

        assertEquals(test1Achievements.getPairCounterStreak(), 1);
        assertEquals(test1Achievements.getPairCounterStreak(), 1);
        assertEquals(test1Achievements.getPairCounterTotal(), 1);
        assertEquals(test1Achievements.getPairCounterTotal(), 1);

        gameController.resetAllAchievements();

        assertEquals(test1Achievements.getPairCounterStreak(), 0);
        assertEquals(test1Achievements.getPairCounterStreak(), 0);
        assertEquals(test1Achievements.getPairCounterTotal(), 0);
        assertEquals(test1Achievements.getPairCounterTotal(), 0);
    }
}
