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

        Game game = new Game();
        PlayerList players = new PlayerList();

        while (game.getGameStatus() != GameStatus.END) {
            switch (game.getGameStatus()) {
                case MENU -> executeMenu(bufferedReader, game, players);
                case RUNNING -> executeRunning(bufferedReader, game, players);
            }
        }
    }

    /**
     * This method reads the input from the console and delegates it to
     * {@link Game} when main {@code GameStatus.MENU} is active.
     *
     * @param bufferedReader provides a connection to the console.
     * @param game          current game
     * @param players       list of all players
     * @throws IOException  on input error.
     */
    public static void executeMenu(BufferedReader bufferedReader,
                                        Game game,
                                   PlayerList players) throws IOException {
        MenuStatus menuStatus = MenuStatus.PLAYERMODE;
        PlayingField field = null;

        boolean firstIssue = true;

        while (game.getGameStatus() == GameStatus.MENU) {

            switch (menuStatus) {
                case PLAYERMODE -> {
                    if (firstIssue) {
                        System.out.println("How many players do you want? Choose between 1 and 4. ");
                        System.out.print("memory> ");
                    }
                    if (selectPlayerMode(bufferedReader.readLine(), players)) {
                        menuStatus = MenuStatus.BOARDSIZE;
                        firstIssue = true;
                    } else {
                        firstIssue = false;
                    }
                }
                case BOARDSIZE -> {
                    if (firstIssue) {
                        System.out.println("Type '2', '4', '6', '8' to select the board-size:");
                        System.out.print("memory> ");
                    }
                    int size = selectBoardSize(bufferedReader.readLine());
                    if (size != 0) {
                        field = new PlayingField(size);
                        menuStatus = MenuStatus.CARDSET;
                        firstIssue = true;
                    } else {
                        firstIssue = false;
                    }
                }
                case CARDSET -> {
                    if (firstIssue) {
                        System.out.println("Type 'L' for letters or 'D' for digits:");
                        System.out.print("memory> ");
                    }
                    if (selectCardSet(bufferedReader.readLine(), field)) {
                        field.fillWithCards();
                        Game.setPlayingField();
                        menuStatus = MenuStatus.PLAYERMODE;
                        firstIssue = true;
                        game.setGameStatus(GameStatus.RUNNING);
                        showBoard();
                    } else {
                        firstIssue = false;
                    }
                }
            }
        }
    }

    /**
     * This method reads the input from the console and delegates it to
     * {@link Game} when the game is {@code GameStatus.RUNNING}.
     *
     * @param bufferedReader provides a connection to the console.
     * @param game          current game
     * @param players       list of all players
     * @throws IOException  on input error.
     */
    public static void executeRunning(BufferedReader bufferedReader,
                                      Game game,
                                      PlayerList players) throws IOException {
        int firstRow = 0;
        int firstCol = 0;

        //It is read in from the console until the program is ended.
        while (game.getGameStatus().equals(GameStatus.RUNNING)) {

            for (Player player = players.getFront(); player != null; player = player.getNext()) {
                if (!game.getGameStatus().equals(GameStatus.RUNNING)) {
                    break;
                }

                int counter = 1; //Number of choices
                System.out.println(player.getName() + ":");
                System.out.print("memory> ");
                String input = bufferedReader.readLine();

                //TODO Soll wir hier noch eine Fehlermeldung ausgeben?
                if (input == null) {
                    break;
                }

                boolean saveBreak = false;

                switch (input) {
                    case "help", "Help", "HELP", "h", "H":
                        printHelp();
                        saveBreak = true;
                        break;
                    case "found", "Found", "FOUND", "f", "F":
                        printGraveyard();
                        saveBreak = true;
                        break;
                    case "score", "Score", "SCORE", "s", "S":
                        printScore(players);
                        saveBreak = true;
                        break;
                    case "menu", "Menu", "MENU", "m", "M":
                        returnToMenu(players, game);
                        saveBreak = true;
                        break;
                    case "reset", "Reset", "RESET", "r", "R":
                        player = resetGame(players, game);
                        saveBreak = true;
                        break;
                    case "restart", "Restart", "RESTART", "rs", "RS":
                        player = restartGame(players, game);
                        saveBreak = true;
                        break;
                    case "quit", "Quit", "QUIT", "q", "Q":
                        quitGame(game);
                        saveBreak = true;
                        break;
                }

                if (!saveBreak) {
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
                                        //game.setGameStatus(GameStatus.END);
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
                                        System.out.println("""
                                                                                            
                                                Please select by entering a command whether you want to\s
                                                 return to the main menu ('menu'),\s
                                                 reset the current game ('reset'),\s
                                                 restart the current game ('restart') or\s
                                                 quit the game ('quit')\s
                                                """);
                                        boolean exit = true;
                                        while (exit) {
                                            System.out.print("memory> ");
                                            String choice = bufferedReader.readLine();
                                            switch (choice) {
                                                case "menu", "Menu", "MENU", "m", "M":
                                                    returnToMenu(players, game);
                                                    exit = false;
                                                    break;
                                                case "reset", "Reset", "RESET", "r", "R":
                                                    player = resetGame(players, game);
                                                    exit = false;
                                                    break;
                                                case "restart", "Restart", "RESTART", "rs", "RS":
                                                    player = restartGame(players, game);
                                                    exit = false;
                                                    break;
                                                case "quit", "Quit", "QUIT", "q", "Q":
                                                    quitGame(game);
                                                    exit = false;
                                                    break;
                                                default:
                                                    error("No valid command "
                                                            + "recognized");
                                            }
                                        }

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
     * @param input     passed {@link CardSet}
     * @param field     is used to set a {@link CardSet}
     * @return true if no error appeared
     */
    public static boolean selectCardSet(String input, PlayingField field) {
        if (input.length() == 1) {
            if (input.equalsIgnoreCase("L")) {
                field.setCardSet(CardSet.LETTERS);
                return true;
            } else if (input.equalsIgnoreCase("D")) {
                field.setCardSet(CardSet.DIGITS);
                return true;
            }
        }
        error("This set does not exist!");
        return false;
    }

    /**
     * Sets the field-size to be used
     *
     * @param input passed size for the {@code field}
     * @return the selected size of the {@code field}
     */
    public static int selectBoardSize(String input) {
        if (input.length() == 1) {
            if (input.matches("\\d")) {
                int size = Integer.parseInt(input);
                if (size <= 8 && size % 2 == 0 && size != 0) {
                    return size;
                }
            }
        }
        error("You can't select this size.");
        return 0;
    }

    /**
     * This method implements a list of Players for the Game.
     *
     * @param input     number of players selected
     * @param players   a list of the players
     * @return true if no error appeared
     */
    public static boolean selectPlayerMode(String input, PlayerList players) {
        if (input.length() == 1) {
            if (input.matches("\\d")) {
                int num = Integer.parseInt(input);
                if (num > 4) {
                    error("Only a maximum of 4 players can take part");
                    return false;
                } else {
                    if (num < 1) {
                        error("At least 1 player must take part");
                        return false;
                    } else {
                        for (int i = 1; i <= num; ++i) {
                            players.addPlayer("Spieler " + i);
                        }
                        return true;
                    }
                }
            }
        }
        error("Entry was not a valid number");
        return false;
    }

    /**
     * Prints out a list of possible commands when you enter the help command.
     */
    public static void printHelp(){
        System.out.println("""
                
                All possible commands are:\s
                    help:       Shows a list of possible commands\s
                    found:      Shows the discard pile of the running game\s
                    score:      Shows the score of all players of the running game\s
                    
                    menu:       Sends you back to main menu\s
                    reset:      Resets the running game to start state\s
                    restart:    Restarts the running game with repositioned cards\s
                    quit:       Quits the game\s
                    
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

    /**
     * Brings you back to main menu
     *
     * @param players   list of all players
     * @param game      current game
     */
    public static void returnToMenu(PlayerList players, Game game) {
        players.deleteAllPlayers();
        game.setGameStatus(GameStatus.MENU);
        game.setTurnStatus(TurnStatus.IDLE);
    }

    /**
     * Resets the Game and restarts it
     *
     * @param players   list of all players
     * @param game      current game
     * @return  the rear {@link Player} of the {@link PlayerList}, so that
     * the next turn is started with the first {@link Player}
     */
    public static Player resetGame(PlayerList players, Game game) {
        game.setTurnStatus(TurnStatus.IDLE);
        game.closeAllCards();
        players.resetAllScores();
        showBoard();
        return players.getRear();
    }

    /**
     * Restarts the Game with repositioned cards
     *
     * @param players   list of all players
     * @param game      current game
     * @return  the rear {@link Player} of the {@link PlayerList}, so that
     * the next turn is started with the first {@link Player}
     */
    public static Player restartGame(PlayerList players, Game game) {
        game.setTurnStatus(TurnStatus.IDLE);
        game.closeAllCards();
        PlayingField playingField =
                new PlayingField(Game.getPlayingField().length);
        PlayingField.setBoard(Game.getPlayingField());
        playingField.shuffleBoard();
        Game.setPlayingField();
        players.resetAllScores();
        showBoard();
        return players.getRear();
    }

    /**
     * Command to quit a running game
     *
     * @param game  current game
     */
    public static void quitGame(Game game) {
        game.setGameStatus(GameStatus.END);
    }
}