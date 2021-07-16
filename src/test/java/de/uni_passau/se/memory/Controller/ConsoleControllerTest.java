package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.PlayingField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleControllerTest {

    @Test
    void validatePlayingFieldBoardSizeTest() {
        ConsoleController consoleController = new ConsoleController();
        assertEquals(consoleController.validatePlayingFieldBoardSize("0"), 0);
        assertEquals(consoleController.validatePlayingFieldBoardSize("1"), 0);
        assertEquals(consoleController.validatePlayingFieldBoardSize("2"), 2);
        assertEquals(consoleController.validatePlayingFieldBoardSize("3"), 0);
        assertEquals(consoleController.validatePlayingFieldBoardSize("4"), 4);
        assertEquals(consoleController.validatePlayingFieldBoardSize("5"), 0);
        assertEquals(consoleController.validatePlayingFieldBoardSize("6"), 6);
        assertEquals(consoleController.validatePlayingFieldBoardSize("7"), 0);
        assertEquals(consoleController.validatePlayingFieldBoardSize("8"), 8);
        assertEquals(consoleController.validatePlayingFieldBoardSize("9"), 0);
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
        PlayingField playingField = new PlayingField();
        consoleController.setPlayingFieldBordSize(playingField, 4);
        consoleController.generateNewPlayerList();
        assertTrue(consoleController.handleInputsDuringGame("help"));
        assertTrue(consoleController.handleInputsDuringGame("rules"));
        assertTrue(consoleController.handleInputsDuringGame("allRules"));
        assertTrue(consoleController.handleInputsDuringGame("score"));
        assertTrue(consoleController.handleInputsDuringGame("menu"));
        assertTrue(consoleController.handleInputsDuringGame("quit"));
    }

    @Test
    void handleInputsAfterGameTest() {
        ConsoleController consoleController = new ConsoleController();
        PlayingField playingField = new PlayingField();
        consoleController.setPlayingFieldBordSize(playingField, 4);
        consoleController.generateNewPlayerList();
        consoleController.validatePlayingFieldBoardSize("4");
        assertFalse(consoleController.handleInputsAfterGame("menu"));
        assertFalse(consoleController.handleInputsAfterGame("quit"));
        assertTrue(consoleController.handleInputsAfterGame(""));
    }
}
