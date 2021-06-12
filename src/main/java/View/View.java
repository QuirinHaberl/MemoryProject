package View;

import Model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class represents the view of the MVC-architecture.
 */
public final class View {
    /**
     * Utility class needs private constructor
     */
    private View() {
    }

    /**
     * This is the main-method which starts a game by executing the execute-method.
     *
     * @param args unused.
     * @throws IOException on input error.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        execute(bufferedReader);
    }

    /**
     * This method reads the input from the console and delegates it to {@link Game}.
     *
     * @param bufferedReader provides a connection to the console.
     * @throws IOException on input error.
     */
    public static void execute(BufferedReader bufferedReader) throws IOException {
        //At the beginning a new control is created.
        new PlayingField();
        Game game = new Game();

        game.setGameStatus(GameStatus.RUNNING);

        int firstRow = 0;
        int firstCol = 0;
        String firstCardImage = null;

        //It is read in from the console until the program is ended.
        while (game.getGameStatus().equals(GameStatus.RUNNING)) {
            System.out.print("memory> ");
            String input = bufferedReader.readLine();
            if (input == null) {
                break;
            }
            if (input.equals("help")) {
                printDescription();
                continue;
            }
            //The input is split up using spaces.
            String[] tokens = input.trim().split("\\s+");

            //Implementation of game phases
            switch (game.getTurnStatus()) {
                case NOTSTARTED:
                    if (correctInput(tokens)) {
                        firstRow = Integer.parseInt(tokens[0]);
                        firstCol = Integer.parseInt(tokens[1]);
                        firstCardImage = game.revealFirstCard(firstRow, firstCol);
                        if (firstCardImage == null) {
                            System.out.println("The selected card was already found!");
                        } else {
                            showBoard();
                        }
                    }
                    break;
                case ACTIVTURN:
                    if (correctInput(tokens)) {
                        int secondRow = Integer.parseInt(tokens[0]);
                        int secondCol = Integer.parseInt(tokens[1]);
                        CardStatus secondCardStatus = game.revealSecondCard(secondRow, secondCol);
                        if (secondCardStatus.equals(CardStatus.FOUND)) {
                            System.out.println("The selected card was already found!");
                            break;
                        } else if (secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                            System.out.println("You've selected the same card twice!");
                            break;

                        }
                        showBoard();
                        if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {
                            System.out.println("You've found a pair!");
                            showBoard();
                            if (game.areAllCardsOpen()) {
                                game.setGameStatus(GameStatus.END);
                                System.out.println("You won!");
                            }
                        } else {
                            System.out.println("Cards are not equal!");
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Visualizes the current {@link Game}
     */
    private static void showBoard() {
        String line = ("  0 1 2 3");
        for (int row = 0; row < Game.getPlayingField().length; row++) {
            line = line + "\n" + row + " ";
            for (int col = 0; col < Game.getPlayingField()[row].length; col++) {
                if (Game.getCard(row, col).getCardStatus().equals(CardStatus.OPEN)) {
                    line = line + Card.visualizeCard(Game.getPlayingField()[row][col].getValue()) + " ";
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.CLOSED)) {
                    line = line + "X ";
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.FOUND)) {
                    line = line + "  ";
                }
            }
        }
        System.out.println(line);
    }

    //Helper Methods

    /**
     * Outputs a specified error message.
     *
     * @param specification Specification of the error message.
     */
    private static void error(String specification) {
        System.out.println("Error! " + specification);
    }

    /**
     * Tests whether the transferred input was correct.
     *
     * @param tokens Passed input
     * @return Returns true if the passed input(two coordinates) was correct.
     */
    private static boolean correctInput(String[] tokens) {
        //Checks whether the input contained two parameters
        if (tokens.length != 2) {
            error("Have not received correct number of parameters");
            return false;
        } else {
            //Checks whether the input contains two numbers between 0 and 3
            if (tokens[0].equals("0") || tokens[0].equals("1") || tokens[0].equals("2") || tokens[0].equals("3")) {
                if (tokens[1].equals("0") || tokens[1].equals("1") || tokens[1].equals("2") || tokens[1].equals("3")) {
                    return true;
                } else {
                    error("Second entry was out of range");
                    return false;
                }
            } else {
                error("First entry was out of range");
                return false;
            }
        }
    }

    /**
     * Prints a description of the memory-game when you enter the help command
     */
    public static void printDescription() {
        System.out.println("Wer an der Reihe ist, darf nacheinander zwei Karten aufdecken. \n" +
                "Dazu gib die Position der gewuenschten Karte als Tupel ein, z.B. 2 1 \n" +
                "Nun wird dir dann das Spielfeld mit dem Bild deiner ausgewaehlten Karte angezeigt. \n" +
                "Analog das Vorgehen bei der zweiten Karte. \n" +
                "Ziel des Spiels ist es ein Kartenpaar, d.h. zwei Karten mit dem gleichen Bild zu finden. \n" +
                "Das zusammenpassende Bilderpaar wird vom Spielfeld entfernt. \n" +
                "Passen die beiden Kartenbilder nicht zusammen, werden die Karten wieder umgedreht.");
    }
}