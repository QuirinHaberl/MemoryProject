package de.uni_passau.se.memory;

import de.uni_passau.se.memory.Control.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The de.uni_passau.se.memory.Main-class of the memory-game.
 */
public class Main {

    /**
     * This is the main-method which starts a game by executing the execute-method.
     *
     * @param args unused.
     * @throws IOException on input error
     */
    public static void main(String[] args) throws IOException {
        Controller controller = Controller.getInstance();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        controller.execute(bufferedReader);
    }
}
