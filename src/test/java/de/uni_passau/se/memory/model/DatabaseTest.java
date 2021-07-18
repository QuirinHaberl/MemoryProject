package de.uni_passau.se.memory.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseTest {

    private static final int PLAYER_AMOUNT = 4;
    private final Database database = new Database();

    @Test
    void useProfileTest() {
        database.setUsesProfileOnEveryPlayer(PLAYER_AMOUNT);
        PlayerList playerList = new PlayerList();

        for (int i = 1; i <= 4; i++) {
            playerList.addPlayer("test" + i);
        }

        database.useProfile(playerList);

        assertFalse(database.getPlayerProfiles().isEmpty());
        for (int i = 0; i < PLAYER_AMOUNT; i++) {
            assertFalse(playerList.getPlayerProfileToString(playerList.getPlayer(i)).isEmpty());
        }
    }

    @Test
    public void resetPlayerProfiles() {
        database.setUsesProfileOnEveryPlayer(PLAYER_AMOUNT);
        PlayerList playerList = new PlayerList();

        for (int i = 1; i <= 4; i++) {
            playerList.addPlayer("test" + i);
        }

        database.useProfile(playerList);

        assertFalse(database.getPlayerProfiles().isEmpty());
        for (int i = 0; i < PLAYER_AMOUNT; i++) {
            assertFalse(playerList.getPlayerProfileToString(playerList.getPlayer(i)).isEmpty());
        }
        database.resetPlayerProfiles();
        assertTrue(database.getPlayerProfiles().isEmpty());
    }

    @Test
    void loadFromFileTest() {
        List<String[]> list1 = new ArrayList<>();

        assertTrue(database.getPlayerProfiles().isEmpty());
        assertFalse(database.loadFromFile(Database.
                getPathToProfiles(), list1).isEmpty());

        List<String[]> list2 = new ArrayList<>();
        assertTrue(database.getHighScoreList().isEmpty());
        assertFalse(database.loadFromFile(Database.
                getPathToHighScores(), list2).isEmpty());
    }

    @Test
    void saveProfileTest() {
        database.setUsesProfileOnEveryPlayer(PLAYER_AMOUNT);
        PlayerList playerList = new PlayerList();

        for (int i = 1; i <= 4; i++) {
            playerList.addPlayer("test" + i);
        }
        database.useProfile(playerList);
        database.saveProfile(playerList);
        boolean savedCorrect = false;
        String[] profile;
        for (int i = 0; i < PLAYER_AMOUNT; i++) {
            profile = database.getPlayerProfiles().get(i);
            if (profile[0].equals(playerList.getPlayer(i).getName() + "") &&
                    profile[1].equals(playerList.getPlayer(i).getHighScore() + "") &&
                    profile[2].equals(playerList.getPlayer(i).getGamesPlayed() + "") &&
                    profile[3].equals(playerList.getPlayer(i).getGamesWon() + "")) {
                savedCorrect = true;
            }
        }
        assertTrue(savedCorrect);
    }
}
