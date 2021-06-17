package Control;

import Model.*;
import Model.Enums.GameStatus;
import Model.Enums.MenuStatus;
import View.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The controller of the MVC-architecture.
 */
public class Controller {

    /**
     * Stores the current {@link Game}.
     */
    private final Game game;

    /**
     * Stores the current {@link MenuStatus}.
     */
    private MenuStatus menuStatus;

    /**
     * Constructs a new {@link Controller}.
     */
    public Controller() {
        this.game = new Game();
        this.menuStatus = MenuStatus.PLAYERMODE;
    }

    /**
     * Reads the input from the console and delegates it to {@link Game}.
     *
     * @param bufferedReader provides a connection to the console.
     * @throws IOException on input error.
     */
    public void execute(BufferedReader bufferedReader) throws IOException {
        //Printing the description of a memory game
        View.printDescription();

        while (game.getGameStatus() != GameStatus.END) {
            switch (game.getGameStatus()) {
                case MENU -> executeMenu(bufferedReader);
                case RUNNING -> executeRunning(bufferedReader);
            }
        }
    }

    /**
     * This method reads the input from the console and delegates it to
     * {@link Game} when main {@code Model.Enums.GameStatus.MENU} is active.
     *
     * @param bufferedReader provides a connection to the console.
     * @throws IOException on input error.
     */
    public void executeMenu(BufferedReader bufferedReader) throws IOException {

        boolean firstIssue = true;

        while (game.getGameStatus().equals(GameStatus.MENU)) {

            switch (menuStatus) {
                case PLAYERMODE -> {
                    if (firstIssue) {
                        View.printSelectPlayerAmount();
                    }
                    View.printMemory();
                    if (game.selectPlayerMode(bufferedReader.readLine())) {
                        menuStatus = MenuStatus.BOARDSIZE;
                        firstIssue = true;
                    } else {
                        firstIssue = false;
                    }
                }
                case BOARDSIZE -> {
                    if (firstIssue) {
                        View.printSelectBoardSize();
                    }
                    View.printMemory();
                    int size = game.getPlayingField().selectBoardSize(bufferedReader.readLine());
                    if (size != 0) {
                        game.getPlayingField().setBoard(size);
                        menuStatus = MenuStatus.CARDSET;
                        firstIssue = true;
                    } else {
                        firstIssue = false;
                    }
                }
                case CARDSET -> {
                    if (firstIssue) {
                        View.printSelectCardSet();
                    }
                    View.printMemory();
                    if (game.getPlayingField().
                            selectCardSet(bufferedReader.readLine())) {
                        game.getPlayingField().fillWithCards();
                        menuStatus = MenuStatus.PLAYERMODE;
                        firstIssue = true;
                        game.setGameStatus(GameStatus.RUNNING);
                        View.printBoard(game.getPlayingField());
                    } else {
                        firstIssue = false;
                    }
                }
            }
        }
    }

    /**
     * This method reads the input from the console and delegates it to
     * {@link Game} when the game is {@code Model.Enums.GameStatus.RUNNING}.
     *
     * @param bufferedReader provides a connection to the console.
     * @throws IOException on input error.
     */
    public void executeRunning(BufferedReader bufferedReader) throws IOException {
        int firstRow = 0;
        int firstCol = 0;

        //It is read in from the console until the program is ended.
        while (game.getGameStatus().equals(GameStatus.RUNNING)) {

            for (Player player = game.getPlayerList().getFront(); player != null; player = player.getNext()) {
                if (!game.getGameStatus().equals(GameStatus.RUNNING)) {
                    break;
                }

                int counter = 1; //Number of choices
                View.printPlayer(player.getName());

                View.printMemory();
                String input = bufferedReader.readLine();

                //TODO Soll wir hier noch eine Fehlermeldung ausgeben?
                if (input == null) {
                    break;
                }

                boolean saveBreak = false;

                switch (input) {
                    case "help", "Help", "HELP", "h", "H" -> {
                        View.printHelp();
                        saveBreak = true;
                    }
                    case "found", "Found", "FOUND", "f", "F" -> {
                        View.printDiscardPile(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "score", "Score", "SCORE", "s", "S" -> {
                        View.printScore(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "menu", "Menu", "MENU", "m", "M" -> {
                        game.returnToMenu(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "cheat" -> {
                        View.printAllCards(game.getPlayingField());
                        saveBreak = true;
                    }
                    case "reset", "Reset", "RESET", "r", "R" -> {
                        player = game.resetGame(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "restart", "Restart", "RESTART", "rs", "RS" -> {
                        player = game.restartGame(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "quit", "Quit", "QUIT", "q", "Q" -> {
                        game.quitGame();
                        saveBreak = true;
                    }
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
                                    View.printAlreadyFound();
                                    break;
                                } else {
                                    View.printBoard(game.getPlayingField());
                                }
                            }
                            break;
                        case ACTIVE:
                            if (correctInput(tokens)) {
                                int secondRow = Integer.parseInt(tokens[0]);
                                int secondCol = Integer.parseInt(tokens[1]);
                                CardStatus secondCardStatus = game.revealSecondCard(secondRow, secondCol);
                                if (secondCardStatus.equals(CardStatus.FOUND)) {
                                    View.printAlreadyFound();
                                    break;
                                } else if (secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                                    View.printSelectedTwice();
                                    break;

                                }
                                View.printBoard(game.getPlayingField());
                                if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {
                                    player.addScore();
                                    player.getFoundCards().add(game.getPlayingField().getBoard()[secondRow][secondCol]);
                                    if (game.areAllCardsOpen()) {
                                        //game.setGameStatus(Model.Enums.GameStatus.END);
                                        View.printAllPairsFound();
                                        View.printBoard(game.getPlayingField());
                                        PlayerList highestScore = game.getPlayerList().
                                                getWinningPlayers(game.getPlayerList());
                                        //Todo Fehler nach Merge
                                        //View.printGameSummary(highestScore, game);
                                        boolean exit = true;
                                        while (exit) {
                                            View.printMemory();
                                            String choice = bufferedReader.readLine();
                                            switch (choice) {
                                                case "menu", "Menu", "MENU", "m", "M" -> {
                                                    game.returnToMenu(game.getPlayerList());
                                                    exit = false;
                                                }
                                                case "reset", "Reset", "RESET", "r", "R" -> {
                                                    player = game.resetGame(game.getPlayerList());
                                                    exit = false;
                                                }
                                                case "restart", "Restart", "RESTART", "rs", "RS" -> {
                                                    player = game.restartGame(game.getPlayerList());
                                                    exit = false;
                                                }
                                                case "quit", "Quit", "QUIT", "q", "Q" -> {
                                                    game.quitGame();
                                                    exit = false;
                                                }
                                                default -> View.error("No valid command "
                                                        + "recognized");
                                            }
                                        }

                                    } else {
                                        View.printFoundPair();
                                        View.printBoard(game.getPlayingField());
                                    }
                                } else {
                                    View.printUnequalCards();
                                    counter = counter + 1;
                                }
                            }
                            break;
                    }
                    if (counter == 1) {
                        player = player.getRear();
                    }
                } else {
                    player = player.getRear();
                }
            }
        }
    }

    /**
     * Tests whether the transferred input was correct.
     *
     * @param tokens Passed input
     * @return Returns true if the passed input(two coordinates) was correct.
     */
    //TODO vielleicht fällt jemanden etwas einfacheres ein
    public boolean correctInput(String[] tokens) {
        //Checks whether the input contained two parameters
        if (tokens.length < 2) {
            View.error("Have not received enough parameters");
            return false;
        } else {
            if (tokens.length > 2) {
                View.error("Received too many parameters");
                return false;
            } else {
                boolean[] cache = new boolean[2];
                for (int i = 0; i < 2; i++) {
                    if (tokens[i].length() == 1) {
                        if (tokens[i].matches("\\d")) {
                            if (Integer.parseInt(tokens[i]) < game.getPlayingField().getBoard().length) {
                                cache[i] = true;
                            } else {
                                if (i == 0) {
                                    View.error("First entry was out of range");
                                } else {
                                    View.error("Second entry was out of range");
                                }
                            }
                        } else {
                            if (i == 0) {
                                View.error("First entry was not a valid number");
                            } else {
                                View.error("Second entry was not a valid number");
                            }
                        }
                    } else {
                        if (i == 0) {
                            View.error("First entry was not a valid number");
                        } else {
                            View.error("Second entry was not a valid number");
                        }
                    }
                }
                return cache[0] && cache[1];
            }
        }
    }
}