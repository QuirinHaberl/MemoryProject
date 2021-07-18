package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.PlayingField;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleControllerTest {

    @Test
    void validatePlayingFieldBoardSizeTest() {
        ConsoleController consoleController = new ConsoleController();
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0 && i > 0) {
                assertEquals(consoleController.validatePlayingFieldBoardSize(i + ""), i);
            } else {
                assertEquals(consoleController.validatePlayingFieldBoardSize(i + ""), 0);
            }
        }
    }

    @Test
    void correctInputTest() {
        ConsoleController consoleController = new ConsoleController();
        PlayingField playingField = new PlayingField();
        consoleController.setPlayingFieldBordSize(playingField, 4);
        consoleController.generateNewPlayerList();
        assertFalse(consoleController.correctInput(new String[]{"0"}));
        assertFalse(consoleController.correctInput(new String[]{"0", "0", "0"}));
        assertTrue(consoleController.correctInput(new String[]{"1", "1"}));
        assertFalse(consoleController.correctInput(new String[]{"5", "0"}));
        assertFalse(consoleController.correctInput(new String[]{"0", "5"}));
    }

    @Test
    void handleSinglePlayerModeSettingsTest() {
        ConsoleController consoleController = new ConsoleController();
        assertTrue(consoleController.handleSinglePlayerModeSettings("life"));
        assertTrue(consoleController.handleSinglePlayerModeSettings("time"));
        assertFalse(consoleController.handleSinglePlayerModeSettings(""));
        assertFalse(consoleController.handleSinglePlayerModeSettings("test"));
    }

    @Test
    void handleInputsDuringGameTest() {
        ConsoleController consoleController = new ConsoleController();
        List<String[]> playerProfiles = new ArrayList<>();
        playerProfiles = Wrapper.getGame().getDatabase().loadFromFile(
                "src/main/resources/de/uni_passau/se/memory/Database" +
                        "/profiles.csv", playerProfiles);

        PlayingField playingField = new PlayingField();
        consoleController.setPlayingFieldBordSize(playingField, 4);
        consoleController.generateNewPlayerList();
        assertTrue(consoleController.handleInputsDuringGame("help"));
        assertTrue(consoleController.handleInputsDuringGame("rules"));
        assertTrue(consoleController.handleInputsDuringGame("allRules"));
        assertTrue(consoleController.handleInputsDuringGame("score"));
        assertTrue(consoleController.handleInputsDuringGame("menu"));
        assertTrue(consoleController.handleInputsDuringGame("quit"));

        Wrapper.getGame().getDatabase().setPlayerProfiles(playerProfiles);
        Wrapper.getGame().getDatabase().useProfile(Wrapper.getGame().getPlayerList());
        Wrapper.getGame().getDatabase().storePlayerProfiles();
    }

    @Test
    void handleInputsAfterGameTest() {
        ConsoleController consoleController = new ConsoleController();

        List<String[]> playerProfiles = new ArrayList<>();
        playerProfiles = Wrapper.getGame().getDatabase().loadFromFile(
                "src/main/resources/de/uni_passau/se/memory/Database" +
                        "/profiles.csv", playerProfiles);

        PlayingField playingField = new PlayingField();
        consoleController.setPlayingFieldBordSize(playingField, 4);
        consoleController.generateNewPlayerList();
        consoleController.validatePlayingFieldBoardSize("4");
        assertFalse(consoleController.handleInputsAfterGame("menu"));
        assertFalse(consoleController.handleInputsAfterGame("quit"));
        assertTrue(consoleController.handleInputsAfterGame(""));

        Wrapper.getGame().getDatabase().setPlayerProfiles(playerProfiles);
        Wrapper.getGame().getDatabase().useProfile(Wrapper.getGame().getPlayerList());
        Wrapper.getGame().getDatabase().storePlayerProfiles();
    }
}
