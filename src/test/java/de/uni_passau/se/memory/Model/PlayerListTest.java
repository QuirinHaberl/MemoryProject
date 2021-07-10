package de.uni_passau.se.memory.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlayerListTest {


    private Database database;
    private PlayerList playerList;

    @BeforeEach
    public void setUp() throws Exception {
        playerList = new PlayerList();
        database = new Database();
    }

    @Test
    void addPlayer() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        assertNotEquals(null, playerList.getFront(), "this PlayerList is null!");
        assertNotEquals(null, playerList.getRear(), "this PlayerList is null!");

        assertEquals("A", playerList.getPlayer(0).getName(), "the Player name is not 'A'!");
        assertEquals("B", playerList.getPlayer(1).getName(), "the Player name is not 'B'!");
        assertEquals("C", playerList.getPlayer(2).getName(), "the Player name is not 'C'!");
        assertEquals(3, playerList.size(), "this PlayerList is null!");
    }


    @Test
    void deleteAllPlayers() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.deleteAllPlayers();

        assertEquals(null, playerList.getFront(), "this PlayerList is not null!");
        assertEquals(null, playerList.getRear(), "this PlayerList is not null!");
        assertEquals(0, playerList.size(), "this PlayerList is not null!");
    }

    @Test
    void getPlayer() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        assertEquals("A", playerList.getPlayer(0).getName(), "the Player name is not 'A'!");
        assertEquals("B", playerList.getPlayer(1).getName(), "the Player name is not 'B'!");
        assertEquals("C", playerList.getPlayer(2).getName(), "the Player name is not 'C'!");
    }

    @Test
    void resetAllScores() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(1);
        playerList.getPlayer(2).setScore(2);

        assertEquals(1, playerList.getPlayer(1).getScore());

        playerList.resetAllScores();
        assertEquals(0, playerList.getPlayer(0).getScore(), "the score is not reset!");
        assertEquals(0, playerList.getPlayer(1).getScore(), "the score is not reset!");
        assertNotEquals(2, playerList.getPlayer(2).getScore(), "the score is not reset!");
    }

    @Test
    void getCountOfWinningPlayers() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");
        playerList.addPlayer("D");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(10);
        playerList.getPlayer(2).setScore(2);
        playerList.getPlayer(3).setScore(10);

        assertEquals(2, playerList.getCountOfWinningPlayers(), "the winningPlayers should be 2!");
    }

    @Test
    void getHighestScore() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(1);
        playerList.getPlayer(2).setScore(2);

        assertEquals(2, playerList.getHighestScore(), "the Highest score should be 2!");
    }

    @Test
    void winningPlayersToString() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(1);
        playerList.getPlayer(2).setScore(2);

        List<String> winner = new ArrayList<>();
        winner.add(playerList.getPlayer(2).getName());

        assertEquals(winner, playerList.winningPlayersToString(), "the name of Winner should be 'C'!");
    }

    @Test
    void getPlayerName() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        assertEquals("A", playerList.getPlayerName(0), "the name is not 'A'!");
        assertEquals("B", playerList.getPlayerName(1), "the name is not 'B'!");
        assertEquals("C", playerList.getPlayerName(2), "the name is not 'C'!");
    }

    @Test
    void getPlayerScore() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(1);
        playerList.getPlayer(2).setScore(2);

        assertEquals(0, playerList.getPlayer(0).getScore(), "this score should be 0!");
        assertEquals(1, playerList.getPlayer(1).getScore(), "this score should be 1!");
        assertEquals(2, playerList.getPlayer(2).getScore(), "this score should be 2!");
    }

}
