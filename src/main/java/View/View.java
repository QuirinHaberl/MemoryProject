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
        //Printing the description of a memory game
        printDescription();

        //Setting of single player oder multi player mode
        PlayerList players = new PlayerList();
        selectPlayerMode(bufferedReader, players);

        //At the beginning a new control is created.
        PlayingField field = new PlayingField(selectBoardSize(bufferedReader));
        Game game = new Game();

        game.setGameStatus(GameStatus.MENU);

        int firstRow = 0;
        int firstCol = 0;

        //Selecting the cardSet and fills the board with cards
        selectCardSet(bufferedReader, field);

        //Fill the board with cards
        field.fillWithCards();

        game.setGameStatus(GameStatus.RUNNING);

        //Print the board before the game starts
        showBoard();

        //It is read in from the console until the program is ended.
        while (game.getGameStatus().equals(GameStatus.RUNNING)) {

            for (Player player = players.getFront(); player != null; player = player.getNext()) {
                int counter = 1; //Number of choices
                System.out.println(player.getName() + ":");
                System.out.print("memory> ");
                String input = bufferedReader.readLine();

                //TODO Soll wir hier noch eine Fehlermeldung ausgeben?
                if (input == null) {
                    break;
                }

                switch (input) {
                    case "help", "Help", "HELP", "h", "H":
                        printHelp();
                        break;
                    case "found", "Found", "FOUND", "f", "F":
                        printGraveyard();
                        break;
                    case "score", "Score", "SCORE", "s", "S":
                        printScore(players);
                }

                //The input is split up using spaces.
                String[] tokens = input.trim().split("\\s+");

                //Implementation of game phases
                switch (game.getTurnStatus()) {
                    case IDLE:
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
                    case ACTIVE:
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
                                player.addScore();
                                if (game.areAllCardsOpen()) {
                                    game.setGameStatus(GameStatus.END);
                                    System.out.println("All Pairs are found.");
                                    showBoard();
                                    Player[] highestScore =
                                            players.getWinningPlayers();
                                    String result = "";
                                    for (int i = 0; i < highestScore.length; i++) {
                                        if (i == 0) {
                                            result = result + highestScore[i].getName();
                                        } else {
                                            result =
                                                    result + " and " + highestScore[i].getName();
                                        }
                                    }
                                    result = result + " won the Game with a "
                                            + "score of " + players.getHighestScore() + "!";
                                    System.out.println(result);
                                } else {
                                    System.out.println("You've found a pair! It is your turn again.");
                                    showBoard();
                                }
                            } else {
                                System.out.println("Cards are not equal!");
                                counter = counter + 1;
                            }
                        }
                        break;
                }
                if (counter == 1) {
                    player = player.getRear();
                }
                if (game.getGameStatus().equals(GameStatus.END)) {
                    break;
                }
            }
        }
    }

    /**
     * Visualizes the current {@link Game}
     */
    private static void showBoard() {
        StringBuilder line = new StringBuilder("  ");
        for (int i = 0; i < Game.getPlayingField().length; i++) {
            line.append(i).append(" ");
        }
        for (int row = 0; row < Game.getPlayingField().length; row++) {
            line = new StringBuilder(line + "\n" + row + " ");
            for (int col = 0; col < Game.getPlayingField()[row].length; col++) {
                if (Game.getCard(row, col).getCardStatus().equals(CardStatus.OPEN)) {
                    line.append(Card.visualizeCard(Game.getPlayingField()[row][col].getValue())).append(" ");
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.CLOSED)) {
                    line.append("X ");
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.FOUND)) {
                    line.append("  ");
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
    //TODO vielleicht fällt jemanden etwas einfacheres ein
    private static boolean correctInput(String[] tokens) {
        //Checks whether the input contained two parameters
        if (tokens.length < 2) {
            error("Have not received enough parameters");
            return false;
        } else {
            if (tokens.length > 2) {
                error("Received too many parameters");
                return false;
            } else {
                boolean[] cache = new boolean[2];
                for (int i = 0; i < 2; i++) {
                    if (tokens[i].length() == 1) {
                        if (tokens[i].matches("\\d")) {
                            if (Integer.parseInt(tokens[i]) < Game.getPlayingField().length) {
                                cache[i] = true;
                            } else {
                                if (i == 0) {
                                    error("First entry was out of range");
                                } else {
                                    error("Second entry was out of range");
                                }
                            }
                        } else {
                            if (i == 0) {
                                error("First entry was not a valid number");
                            } else {
                                error("Second entry was not a valid number");
                            }
                        }
                    } else {
                        if (i == 0) {
                            error("First entry was not a valid number");
                        } else {
                            error("Second entry was not a valid number");
                        }
                    }
                }
                return cache[0] && cache[1];
            }
        }
    }

    /**
     * Prints a description of the memory-game when you enter the game
     */
    public static void printDescription() {
        System.out.println("""

                Wer an der Reihe ist, darf nacheinander zwei Karten aufdecken.\s
                Dazu gib die Position der gewuenschten Karte als Tupel ein, z.B. 2 1\s
                Nun wird dir dann das Spielfeld mit dem Bild deiner ausgewaehlten Karte angezeigt.\s
                Analog das Vorgehen bei der zweiten Karte.\s
                Ziel des Spiels ist es ein Kartenpaar, d.h. zwei Karten mit dem gleichen Bild zu finden.\s
                Das zusammenpassende Bilderpaar wird vom Spielfeld entfernt.\s
                Passen die beiden Kartenbilder nicht zusammen, werden die Karten wieder umgedreht.
                """);
    }

    /**
     * Sets the {@link CardSet} to be used
     *
     * @param bufferedReader provides a connection to the console.
     * @param field          is used to set a {@link CardSet}
     * @throws IOException on input error.
     */
    public static void selectCardSet(BufferedReader bufferedReader, PlayingField field) throws IOException {
        //TODO Der Input muss noch auf Fehler überprüft werden
        System.out.println("Type 'L' for letters or 'D' for digits:");
        System.out.print("memory> ");
        String input = bufferedReader.readLine();
        if (input.equalsIgnoreCase("L")) {
            field.setCardSet(CardSet.LETTERS);
        } else if (input.equalsIgnoreCase("D")) {
            field.setCardSet(CardSet.DIGITS);
        } else {
            throw new IllegalArgumentException("This set does not exist!");
        }
    }

    /**
     * Sets the field-size to be used
     *
     * @param bufferedReader provides a connection to the console.
     * @return the selected size of the {@code field}
     * @throws IOException on input error.
     */
    public static int selectBoardSize(BufferedReader bufferedReader) throws IOException {
        //TODO Der Input muss noch auf Fehler überprüft werden
        System.out.println("Type '2', '4', '6', '8' to select the board-size:");
        System.out.print("memory> ");
        int size = Integer.parseInt(bufferedReader.readLine());
        if (size <= 8 || size % 2 == 0) {
            return size;
        } else {
            throw new IllegalArgumentException("You can't select this size.");
        }
    }

    /**
     * This method implements a list of Players for the Game.
     *
     * @param bufferedReader provides a connection to the console.
     * @param players        a list of the players
     * @throws IOException on input error.
     */
    public static void selectPlayerMode(BufferedReader bufferedReader, PlayerList players) throws IOException {
        System.out.println("How many players do you want? Choose between 1 and 4. ");
        System.out.print("memory> ");
        int input = Integer.parseInt(bufferedReader.readLine());
        if (input > 4 || input < 1) {
            throw new IOException("Es müssen mindestens 1 und maximal 4 Spieler teilnehmen!");
        }
        for (int num = 1; num <= input; ++num) {
            players.addPlayer("Spieler " + num);
        }
    }

    /**
     * Prints out a list of possible commands when you enter the help command.
     */
    public static void printHelp(){
        System.out.println("""
                
                All possible commands are:\s
                    help:   Shows a list of possible commands\s
                    found:  Shows the discard pile of the running game\s
                    score:  Shows the score of all players of the running game\s
                    
                    menu:   Sends you back to main menu\s
                    reset:  Resets the running game to start state\s
                    quit:   Quits the game\s
                    
                All sorts of commands can be written in lowercase and uppercase letters.\s
                Or can also be called by using abbreviations consisting of their first letter.\s
                """);
    }

    /**
     * Prints every {@link Card} that is {@code GameStatus.Found}
     */
    public static void printGraveyard() {
        StringBuilder line = new StringBuilder("  ");
        for (int i = 0; i < Game.getPlayingField().length; i++) {
            line.append(i).append(" ");
        }
        for (int row = 0; row < Game.getPlayingField().length; row++) {
            line.append("\n").append(row).append(" ");
            for (int col = 0; col < Game.getPlayingField()[row].length; col++) {
                if (Game.getCard(row, col).getCardStatus().equals(CardStatus.OPEN)) {
                    line.append("  ");
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.CLOSED)) {
                    line.append("  ");
                } else if (Game.getCard(row, col).getCardStatus().equals(CardStatus.FOUND)) {
                    line.append(Card.visualizeCard(Game.getPlayingField()[row][col].getValue())).append(" ");
                }
            }
        }
        System.out.println(line);
    }

    /**
     * Prints every {@link Player} and its {@code Player.score}
     *
     * @param players list of all players
     */
    public static void printScore(PlayerList players){
        for (int i = 0; i < players.getCount(); i++) {
            System.out.println("[" + players.getPlayer(i).getName() + ": "
                    + players.getPlayer(i).getScore() + "]");
        }
    }
}