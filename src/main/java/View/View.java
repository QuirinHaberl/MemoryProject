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
        PlayingField field = new PlayingField();
        Game game = new Game();
        PlayerList players = new PlayerList();

        game.setGameStatus(GameStatus.MENU);

        int firstRow = 0;
        int firstCol = 0;
        String firstCardImage = null;

        //Printing the description of a memory game
        printDescription();

        //Setting the cardSet and fills the board with cards
        selectCardSet(bufferedReader, field);

        //Setting of single player oder multi player mode
        selectPlayerMode(bufferedReader, players);

        game.setGameStatus(GameStatus.RUNNING);

        //It is read in from the console until the program is ended.
        while (game.getGameStatus().equals(GameStatus.RUNNING)) {

            for (Player player = players.getFront(); player != null; player = player.getNext()) {
                int counter = 1; //Number of chooses
                System.out.println(player.getName() + ":");
                System.out.print("memory> ");
                String input = bufferedReader.readLine();

                if (input == null) {
                    break;
                }
                if (input.equals("help")) {
                    printDescription();
                    continue;
                }
                if (input.equals("found")) {
                    printGraveyard();
                    continue;
                }
                if (input.equals("players")) {
                    players.print();
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
                            CardStatus firstCardStatus = game.revealFirstCard(firstRow, firstCol);
                            if (firstCardStatus.equals(CardStatus.FOUND)) {
                                System.out.println("The selected card was already found!");
                                break;
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
                                counter = counter + 1;
                            }
                        }
                        break;
                }
                if(counter == 1) {
                    player = player.getRear();
                }
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
        System.out.println("\nWer an der Reihe ist, darf nacheinander zwei Karten aufdecken. \n" +
                "Dazu gib die Position der gewuenschten Karte als Tupel ein, z.B. 2 1 \n" +
                "Nun wird dir dann das Spielfeld mit dem Bild deiner ausgewaehlten Karte angezeigt. \n" +
                "Analog das Vorgehen bei der zweiten Karte. \n" +
                "Ziel des Spiels ist es ein Kartenpaar, d.h. zwei Karten mit dem gleichen Bild zu finden. \n" +
                "Das zusammenpassende Bilderpaar wird vom Spielfeld entfernt. \n" +
                "Passen die beiden Kartenbilder nicht zusammen, werden die Karten wieder umgedreht.\n");
    }

    /**
     * Sets the {@link CardSet} to be used
     */
    public static void selectCardSet(BufferedReader bufferedReader, PlayingField field) throws IOException {
        //TODO Der Input muss noch auf Fehler überprüft werden
        System.out.println("Type 'L' for letters or 'D' for digits.");
        System.out.print("memory> ");
        String input = bufferedReader.readLine();
        if (input.equalsIgnoreCase("L")) {
            field.setCardSet(CardSet.LETTERS);
        } else if (input.equalsIgnoreCase("D")) {
            field.setCardSet(CardSet.DIGITS);
        } else {
            throw new IllegalAccessError("This set does not exist!");
        }
        field.fillWithCards();
    }

    /**
     * This method implements a list of Players for the Game.
     *
     * @param bufferedReader
     * @param players, a list of the players
     * @return a list with all players who take part of the game
     * @throws IOException
     *
     * TODO Bis jetzt kann eine beliebige Anzahl an Spielern teilnehmen,
     *  lediglich die Printaufforderung schränkt die Wahl auf 1 - 4 ein.
     */
    public static PlayerList selectPlayerMode(BufferedReader bufferedReader, PlayerList players) throws IOException {
        System.out.println("How many players do you want? Choose between 1 and 4. ");
        System.out.print("memory> ");
        int input = Integer.parseInt(bufferedReader.readLine());
        for (int num = 1; num <= input; ++num){
            players.addPlayer("Spieler " + num);
        }
        showBoard();
        return players;
    }

    public static void printGraveyard() {
        String line = ("  0 1 2 3");
        for (int row = 0; row < Game.getPlayingField().length; row++) {
            line = line + "\n" + row + " ";
            for (int col = 0; col < Game.getPlayingField()[row].length; col++) {
                if (Game.getCard(row, col).getCardStatus().equals(CardStatus.OPEN)) {
                    line = line + "  ";
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.CLOSED)) {
                    line = line + "  ";
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.FOUND)) {
                    line = line + Card.visualizeCard(Game.getPlayingField()[row][col].getValue()) + " ";
                }
            }
        }
        System.out.println(line);
    }
}