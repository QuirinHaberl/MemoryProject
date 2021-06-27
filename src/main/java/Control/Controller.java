package Control;

import Model.*;
import Model.Enums.CardStatus;
import Model.Enums.GameStatus;
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
        View.printDefault();

        //Use an execute-method depending on the current game status.
        while (game.getGameStatus() != GameStatus.END) {
            switch (game.getGameStatus()) {
                case MENU -> executeMenu(bufferedReader);
                case RUNNING -> executeRunning(bufferedReader);
            }
        }
    }

    /**
     * This method reads the input from the console and delegates it to
     * {@link Game} when main {@code GameStatus.MENU} is set.
     *
     * @param bufferedReader provides a connection to the console.
     * @throws IOException on input error.
     */
    public void executeMenu(BufferedReader bufferedReader) throws IOException {

        boolean firstIssue = true;
        int playerAmount = 0;

        while (game.getGameStatus().equals(GameStatus.MENU)) {

            switch (menuStatus) {

                //Is used to determine the number of players.
                case PLAYERMODE -> {
                    if (firstIssue) {
                        View.printSelectPlayerAmount();
                    }
                    View.printMemory();
                    String input = bufferedReader.readLine().trim();
                    if (selectPlayerMode(input)) {
                        playerAmount = Integer.parseInt(input);
                        menuStatus = MenuStatus.PLAYERNAMES;
                        firstIssue = true;
                    } else {
                        firstIssue = false;
                    }
                }

                //Used to set the names of all players.
                case PLAYERNAMES -> {
                    String[] playerNames = new String[playerAmount];
                    for (int i = 0; i < playerAmount; i++){
                        if (firstIssue) {
                            View.printPlayernameRequest(i+1);
                        }
                        View.printMemory();
                        String name = bufferedReader.readLine().trim();
                        if (selectPlayerName(name, playerNames, i)) {
                            firstIssue = true;
                        } else {
                            i--;
                            firstIssue = false;
                        }
                    }
                    game.addPlayers(playerAmount, playerNames);
                    menuStatus = MenuStatus.BOARDSIZE;
                }

                //Used to select the board size.
                case BOARDSIZE -> {
                    if (firstIssue) {
                        View.printSelectBoardSize();
                    }
                    View.printMemory();
                    int size = game.getPlayingField().selectBoardSize(bufferedReader.readLine().trim());
                    if (size != 0) {
                        game.getPlayingField().setBoard(size);
                        menuStatus = MenuStatus.CARDSET;
                        firstIssue = true;
                    } else {
                        firstIssue = false;
                    }
                }

                //Used to select the card set.
                case CARDSET -> {
                    if (firstIssue) {
                        View.printSelectCardSet();
                    }
                    View.printMemory();
                    if (game.getPlayingField().
                            selectCardSet(bufferedReader.readLine().trim())) {
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
     * {@link Game} when the game is {@code GameStatus.RUNNING}.
     *
     * @param bufferedReader provides a connection to the console.
     * @throws IOException on input error.
     */
    public void executeRunning(BufferedReader bufferedReader) throws IOException {
        int firstRow = 0;
        int firstCol = 0;

        while (game.getGameStatus().equals(GameStatus.RUNNING)) {

            for (Player player = game.getPlayerList().getFront(); player != null; player = player.getNext()) {
                if (!game.getGameStatus().equals(GameStatus.RUNNING)) {
                    break;
                }

                int counter = 1; //Number of choices
                View.printPlayer(player.getName());

                View.printMemory();
                String input = bufferedReader.readLine().trim();

                boolean saveBreak = false;

                switch (input.toLowerCase()) {
                    case "help", "h" -> {
                        View.printHelp();
                        saveBreak = true;
                    }
                    case "rules", "ru" -> {
                        if(game.getPlayerList().getCount()>1) {
                            View.printDescriptionMultiplayer();
                        }
                        else{
                            View.printDescriptionSinglePlayer();
                        }
                        saveBreak = true;
                    }
                    case "allrules", "ar" -> {
                        View.printDescriptionComplete();
                        saveBreak = true;
                    }
                    case "found", "f" -> {
                        View.printDiscardPile(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "score", "s" -> {
                        View.printScore(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "menu", "m" -> {
                        game.returnToMenu(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "reset", "r" -> {
                        player = game.resetGame(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "restart", "rs" -> {
                        player = game.restartGame(game.getPlayerList());
                        saveBreak = true;
                    }
                    case "quit", "q" -> {
                        game.quitGame();
                        saveBreak = true;
                    }
                }

                if (!saveBreak) {
                    //The input is split by using spaces.
                    String[] tokens = input.trim().split("\\s+");

                    //Implementation of the game phases
                    switch (game.getTurnStatus()) {

                        //Is used if the turn hasn't been stated yet.
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

                        //Is used if the turn has been stated.
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
                                        View.printGameSummary(highestScore, game);
                                        boolean exit = true;
                                        while (exit) {
                                            View.printMemory();
                                            String choice = bufferedReader.readLine().trim();
                                            switch (choice.toLowerCase()) {
                                                case "menu", "m" -> {
                                                    game.returnToMenu(game.getPlayerList());
                                                    exit = false;
                                                }
                                                case "reset", "r" -> {
                                                    player = game.resetGame(game.getPlayerList());
                                                    exit = false;
                                                }
                                                case "restart", "rs" -> {
                                                    player = game.restartGame(game.getPlayerList());
                                                    exit = false;
                                                }
                                                case "quit", "q" -> {
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
     * Checks whether the transferred input was correct.
     *
     * @param tokens Passed input
     * @return Returns true if the passed input(two coordinates) was correct.
     */
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

    /**
     * Selects the player-mode.
     *
     * @param input number of players selected
     * @return true if no error appeared
     */
    public boolean selectPlayerMode(String input) {
        if (input.length() == 1) {
            if (input.matches("\\d")) {
                int num = Integer.parseInt(input);
                if (num > 4) {
                    View.error("Only a maximum of 4 players can take part");
                    return false;
                } else {
                    if (num < 1) {
                        View.error("At least 1 player must take part");
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        View.error("Entry was not a valid number");
        return false;
    }

    /**
     * Checks whether a name can be used for a {@link Player}.
     *
     * @param playerName    the chosen name
     * @param playerNames   lists all player names
     * @param pos           Position of the player
     * @return  true whether the chosen name can be used or not
     */
    public boolean selectPlayerName(String playerName, String[] playerNames,
                                    int pos) {
        String newStr = playerName.trim();
        if (newStr.equals("")) {
            View.error("No input recognized! Please try again");
            return false;
        }
        if(playerName.equalsIgnoreCase("noName")){
            playerNames[pos] = "Player" + (pos+1);
            return true;
        } else {
            boolean nameAlreadyTaken = false;
            for (int i = 0; i < pos; i++) {
                if (newStr.equals(playerNames[i])) {
                    nameAlreadyTaken = true;
                    break;
                }
            }
            if (!nameAlreadyTaken) {
                playerNames[pos] = newStr;
                return true;
            } else {
                View.error("Selected name already taken! Please select "
                        + "another one");
                return false;
            }
        }
    }
}