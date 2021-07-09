package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    static Game game;

    @BeforeEach
    void init() {
        game = new Game();
        game.getPlayingField().setBoard(4);
        game.getPlayingField().setCardSet(CardSet.DIGITS);
        game.getPlayingField().fillWithCards();
    }

    @AfterEach
    void clear() {
        game.getPlayerList().deleteAllPlayers();
    }

    @Test
    void saveProfile() {

    }

    @Test
    void useProfile() {


    }

    @Test
    void storeProgress() {

    }

    @Test
    void checkForAchievementsInGame() throws NoSuchFieldException, IllegalAccessException {


        game.addPlayer("Frodo");

        game.getPlayerList().getPlayer(0).getAchievements().setGamesWon(8);

        //set Achievements with Reflections
        Field currAch = Achievements.class.getDeclaredField("currentAchievements");
        currAch.setAccessible(true);
        currAch.set(game.getPlayerList().getPlayer(0).getAchievements(),"test123");


        String ach = game.checkForAchievementsInGame(game.getPlayerList().getPlayer(0));

        assertEquals("test123",ach);

    }

    @Test
    void testCheckForAchievements() {
    }


    @Test
    void revealFirstCard_LegalPos() {
        game.revealFirstCard(0,0);

        assertEquals(CardStatus.OPEN,game.getCard(0,0).getCardStatus());
    }

    @Test
    void revealFirstCard_IllegalPos() {

       assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
           game.revealFirstCard(-1,0);
        });
    }


    @Test
    void revealSecondCard_BeforeFirst_DoNothing() {
        game.revealSecondCard(1,2);
        assertEquals(CardStatus.CLOSED,game.getCard(1,2).getCardStatus());
    }

    @Test
    void revealSecondCard_OpenSameAsFirst() {
        game.revealFirstCard(0,0);
        game.revealSecondCard(0,0);
        assertEquals(CardStatus.AlREADYOPEN, game.getCard(0,0).getCardStatus());
    }

    @Test
    void revealSecondCard_RightOrder() {
        game.revealFirstCard(0,0);
        game.revealSecondCard(1,2);
        assertEquals(CardStatus.OPEN,game.getCard(0,0).getCardStatus());
        assertEquals(CardStatus.OPEN,game.getCard(1,2).getCardStatus());

    }

    @Test
    void pairCheck_AssumeTrue() throws NoSuchFieldException, IllegalAccessException {

        CardValues value = (CardValues) game.getCard(0,0).getValue();

        for (int row = 0; row < game.getBoard().length; row++) {
            for (int col = 0; col < game.getBoard().length; col++) {
                    if(row != 0 && col != 0 && (value == game.getCard(row,col).getValue())) {
                        assertTrue(game.pairCheck(0,0,row,col), "Cards should match");
                        break;
                    }
            }
        }
    }

    @Test
    void pairCheck_AssumeFalse() throws NoSuchFieldException, IllegalAccessException {

        CardValues value = (CardValues) game.getCard(0,0).getValue();

        for (int row = 0; row < game.getBoard().length; row++) {
            for (int col = 0; col < game.getBoard().length; col++) {
                if(row != 0 && col != 0 && (value != game.getCard(row,col).getValue())) {
                    assertFalse(game.pairCheck(0,0,row,col),"Cards should't match");
                    break;
                }
            }
        }
    }

    @Test
    void areAllCardsFound_AssumeFalse() {
        assertFalse(game.areAllCardsFound());
    }

    @Test
    void areAllCardsFound_AssumeTrue() {

        for (int row = 0; row < game.getBoard().length; row++) {
            for (int col = 0; col < game.getBoard().length; col++) {
                game.getCard(row, col).setCardStatus(CardStatus.FOUND);
            }
        }
        assertTrue(game.areAllCardsFound());
    }


    @Test
    void removeCards() {

        game.getCard(1,1).setCardStatus(CardStatus.OPEN);


        game.removeCards(1,1,1,2);

        assertEquals(CardStatus.FOUND,game.getCard(1,1).getCardStatus());
        assertEquals(CardStatus.FOUND,game.getCard(1,2).getCardStatus());

    }

    @Test
    void closeCards() {

        game.revealFirstCard(1,1);
        game.revealSecondCard(1,2);

        game.closeCards(1,1,1,2);

        assertEquals(CardStatus.CLOSED,game.getCard(1,1).getCardStatus());
        assertEquals(CardStatus.CLOSED,game.getCard(1,2).getCardStatus());

    }

    @Test
    void closeAllCards() {

        //Open a Card
        game.revealFirstCard(0,0);

        game.closeAllCards();

        for (int row = 0; row < game.getBoard().length; row++) {
            for (int col = 0; col < game.getBoard().length; col++) {
                assertEquals(CardStatus.CLOSED, game.getCard(row,col).getCardStatus());
            }
        }


    }

    @Test
    void returnToMenu() {

        PlayerList players = new PlayerList();
        players.addPlayer("Frodo");
        players.addPlayer("Sam");
        players.addPlayer("Gandalf");

        game.returnToMenu(players);

        assertEquals(0, game.getPlayerList().getCount());
        assertEquals(GameStatus.MENU, game.getGameStatus());
        assertEquals(TurnStatus.IDLE, game.getTurnStatus());


    }

    @Test
    void quitGame() {
        game.quitGame();
        assertEquals(GameStatus.END,game.getGameStatus());
    }

    @Test
    void resetGame() {
        String[] players = new String[] {"Frodo", "Sam", "Gandalf"};

        PlayerList playersList = new PlayerList();
        playersList.addPlayer(players[0]);
        playersList.addPlayer(players[1]);
        playersList.addPlayer(players[2]);


        game.addPlayers(3, players);

        game.resetGame(playersList);

        assertEquals(TurnStatus.IDLE,game.getTurnStatus());
        closeAllCards(); // other Test
        assertEquals(0,game.getPlayerList().getPlayer(0).getScore());
        assertEquals(0,game.getPlayerList().getPlayer(1).getScore());
        assertEquals(0,game.getPlayerList().getPlayer(2).getScore());


    }

    @Test
    void restartGame() {

    }

    @Test
    void addPlayers() {

        String[] players = new String[] {"Frodo", "Sam", "Gandalf"};

        game.addPlayers(3, players);

        assertEquals(3,game.getPlayerList().getCount(),"Player number is not right!");
        assertEquals("Frodo",game.getPlayerList().getPlayer(0).getName(), "Name of First Player is wrong");
        assertEquals("Sam",game.getPlayerList().getPlayer(1).getName(), "Name of Second Player is wrong");
        assertEquals("Gandalf",game.getPlayerList().getPlayer(2).getName(), "Name of Third Player is wrong");


    }

    @Test
    void addPlayer() {
        game.addPlayer("Frodo");


        assertEquals(1,game.getPlayerList().getCount());
        assertEquals("Frodo", game.getPlayerList().getPlayer(0).getName());
    }

}