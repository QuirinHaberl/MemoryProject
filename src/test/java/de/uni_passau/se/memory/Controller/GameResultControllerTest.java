package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Achievements;
import de.uni_passau.se.memory.Model.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameResultControllerTest {

    @Test
    void printGameSummaryTest() {

        GameResultController gameResultController = new GameResultController();

        List<String> winningPlayers = new ArrayList<>();
        winningPlayers.add("test1");
        int highScore = 5;

        assertEquals("test1 won the game with a score of 5\n" + "null",
                gameResultController.printGameSummary(winningPlayers,
                        highScore));

        winningPlayers.add("test2");
        assertEquals("The game ended in a tie", gameResultController.
                printGameSummary(winningPlayers, highScore));

    }

    @Test
    void getAchievementsAfterGameTest() {
        GameResultController gameResultController = new GameResultController();

        assertNull(gameResultController.getAchievementsAfterGame());

        Game game = Wrapper.getGame();

        game.addPlayer("test1");
        Achievements test1Achievements =
                game.getPlayerList().getPlayer(0).getAchievements();
        test1Achievements.updatePairCounters();
        test1Achievements.updatePairCounters();

        String achievement = "test1 has earned: 1. game won!\n\n";

        assertEquals(achievement,
                gameResultController.getAchievementsAfterGame());
    }
}
