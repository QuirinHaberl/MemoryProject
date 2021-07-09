package de.uni_passau.se.memory.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerListTest {


    private PlayerList playerList;

    @BeforeEach
    public void setUp() throws Exception{
       playerList = new PlayerList();
    }

    @Test
    void addPlayer() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        assertNotEquals(null,playerList.getFront(),"this PlayerList is null!");
        assertNotEquals(null,playerList.getRear(),"this PlayerList is null!");

        assertEquals("A",playerList.getPlayer(0).getName(),"the Player name is not 'A'!");
        assertEquals("B",playerList.getPlayer(1).getName(),"the Player name is not 'B'!");
        assertEquals("C",playerList.getPlayer(2).getName(),"the Player name is not 'C'!");
        assertEquals(3,playerList.getCount(),"this PlayerList is null!");
    }

    @Test
    void deletePlayer() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.deletePlayer(0);
        assertEquals("B",playerList.getPlayer(0).getName(),"the Player name is not 'B'!");
        assertEquals("B",playerList.getFront().getName(),"the Player name is not 'B'!");

        playerList.deletePlayer(1);
        assertEquals("B",playerList.getRear().getName(),"the Player name is not 'C'!");
    }

    @Test
    void deleteAllPlayers() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.deleteAllPlayers();

        assertEquals(null,playerList.getFront(),"this PlayerList is not null!");
        assertEquals(null,playerList.getRear(),"this PlayerList is not null!");
        assertEquals(0,playerList.getCount(),"this PlayerList is not null!");
    }

    @Test
    void getPlayer() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        assertEquals("A",playerList.getPlayer(0).getName(),"the Player name is not 'A'!");
        assertEquals("B",playerList.getPlayer(1).getName(),"the Player name is not 'B'!");
        assertEquals("C",playerList.getPlayer(2).getName(),"the Player name is not 'C'!");
    }

    @Test
    void resetAllScores() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(1);
        playerList.getPlayer(2).setScore(2);

        assertEquals(1,playerList.getPlayer(1).getScore());

        playerList.resetAllScores();
        assertEquals(0,playerList.getPlayer(0).getScore(),"the score is not reset!");
        assertEquals(0,playerList.getPlayer(1).getScore(),"the score is not reset!");
        assertNotEquals(2,playerList.getPlayer(2).getScore(),"the score is not reset!");
    }

    @Test
    void getPlayerPosition() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        Player A = playerList.getPlayer(0);
        Player B = playerList.getPlayer(1);
        Player C = playerList.getPlayer(2);

        assertEquals(0,playerList.getPlayerPosition(A),"the position of this Player is not 0!");
        assertEquals(1,playerList.getPlayerPosition(B),"the position of this Player is not 1!");
        assertEquals(2,playerList.getPlayerPosition(C),"the position of this Player is not 2!");
    }

    @Test
    void getHighestScore() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(1);
        playerList.getPlayer(2).setScore(2);

        assertEquals(2,playerList.getHighestScore(),"the Highest score should be 2!");
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

        assertEquals(winner,playerList.winningPlayersToString(),"the name of Winner should be 'C'!");
    }

    @Test
    void updatePlayerScore() {
        playerList.addPlayer("A");
        playerList.addPlayer("B");
        playerList.addPlayer("C");

        playerList.getPlayer(0).setScore(0);
        playerList.getPlayer(1).setScore(1);
        playerList.getPlayer(2).setScore(2);

        playerList.updatePlayerScore(0);
        playerList.updatePlayerScore(1);
        playerList.updatePlayerScore(2);

        assertEquals(1,playerList.getPlayer(0).getScore(),"this score is not updated!");
        assertEquals(2,playerList.getPlayer(1).getScore(),"this score is not updated!");
        assertEquals(3,playerList.getPlayer(2).getScore(),"this score is not updated!");
    }

    @Test
    void getPlayerName() {

    }

    @Test
    void getPlayerScore() {
    }

    @Test
    void getRanking() {
    }

    @Test
    void getSorted(){
    }
}