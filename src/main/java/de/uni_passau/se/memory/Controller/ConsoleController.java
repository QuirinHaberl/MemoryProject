package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.Database;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
import de.uni_passau.se.memory.Model.Game;
import de.uni_passau.se.memory.Model.Player;
import de.uni_passau.se.memory.gui.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The controller for the console of the MVC-architecture.
 */
public class ConsoleController {

    /**
     * Stores the current {@link Game}.
     */
    private final Game game;

    /**
     * Stores the row of the first selected card.
     */
    private int firstRow = 0;

    /**
     * Stores the column of the first selected card.
     */
    private int firstCol = 0;

    /**
     * Stores the row of the second selected card.
     */
    private int secondRow = 0;

    /**
     * Stores the column of the second selected card.
     */
    private int secondCol = 0;

    /**
     * Stores whether a player found a pair.
     */
    private boolean isStillPlaying;

    /**
     * Stores the current {@link MenuStatus}.
     */

    private MenuStatus menuStatus;

    /**
     * Stores the current {@link SinglePlayerMode}.
     */
    private SinglePlayerMode singlePlayerMode;

    /**
     * Constructs a new {@link ConsoleController}.
     */
    public ConsoleController() {
        this.game = Game.getInstance();
        this.menuStatus = MenuStatus.PLAYERMODE;
    }

    /**
     * Reads the input from the console and delegates it to {@link Game}.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void execute(final BufferedReader bufferedReader)
            throws IOException {

        //Printing the description of a memory game
        View.printDefault();

        //Use an execute-method depending on the current game status.
        while (game.getGameStatus() != GameStatus.END) {
            switch (game.getGameStatus()) {
                case MENU -> executeMenu(bufferedReader);
                case RUNNING -> executeRunning(bufferedReader);
                default -> throw new UnknownError();
            }
        }
    }

    /**
     * This method reads the input from the console and delegates it to
     * {@link Game} when main {@code GameStatus.MENU} is set.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void executeMenu(BufferedReader bufferedReader) throws IOException {

        while (game.getGameStatus().equals(GameStatus.MENU)) {

            switch (menuStatus) {

                //Is used to determine the number of players
                // and to set (if needed) the single player mode.
                case PLAYERMODE -> selectPlayerMode(bufferedReader);

                //Used to set the names of all players.
                case PLAYERNAMES -> selectPlayerNames(bufferedReader);

                //Used to select the board size.
                case BOARDSIZE -> selectBoardSize(bufferedReader);
                //Used to select the card set.
                case CARDSET -> selectCardSet(bufferedReader);
            }
        }
    }

    /**
     * Selects the boardSize.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void selectBoardSize(BufferedReader bufferedReader) throws IOException {
        View.printSelectBoardSize();
        View.printMemory();
        int size = game.getPlayingField().selectBoardSize(bufferedReader.readLine().trim());
        if (size != 0) {
            game.getPlayingField().setBoard(size);
            menuStatus = MenuStatus.CARDSET;
        }
    }

    /**
     * Selects a cardSet depending on user input.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void selectCardSet(BufferedReader bufferedReader) throws IOException {
        View.printSelectCardSet();
        View.printMemory();
        if (game.getPlayingField().
                selectCardSet(bufferedReader.readLine().trim())) {
            game.getPlayingField().fillWithCards();
            menuStatus = MenuStatus.PLAYERMODE;
            game.setGameStatus(GameStatus.RUNNING);
            View.printBoard(game.getPlayingField());

            //This is only for the single player mode with the setting "play on time"
            if (game.getPlayerList().size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                game.startTimer();
            }
        }
        updateCurrentDataBase(game.getDatabase());
    }

    /**
     * Updates the dataBase.
     *
     * @param database to be used.
     */
    public void updateCurrentDataBase(Database database) {
        database.updateDataBase(game.getPlayerAmount(), game.getPlayerList());
    }

    /**
     * Selects playerNames depending on user input.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void selectPlayerNames(BufferedReader bufferedReader) throws IOException {
        String[] playerNames = new String[game.getPlayerAmount()];
        boolean noProblemOccurred = true;
        for (int i = 0; i < game.getPlayerAmount(); i++) {
            if (noProblemOccurred) {
                View.printPlayerNameRequest(i + 1);
            }
            View.printMemory();
            String name = bufferedReader.readLine().trim();
            if (selectPlayerName(name, playerNames, i)) {
                noProblemOccurred = true;
            } else {
                i--;
                noProblemOccurred = false;
            }
        }
        game.addPlayers(game.getPlayerAmount(), playerNames);
        menuStatus = MenuStatus.BOARDSIZE;
    }

    /**
     * Selects playerMode depending on user input.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void selectPlayerMode(BufferedReader bufferedReader) throws IOException {
        View.printSelectPlayerAmount();

        View.printMemory();
        String input = bufferedReader.readLine().trim();

        if (getPlayerMode(input) > 1) {
            game.setPlayerAmount(Integer.parseInt(input));
            menuStatus = MenuStatus.PLAYERNAMES;

        } else if (getPlayerMode(input) == 1) {
            View.printSinglePlayerModeSettings();
            View.printMemory();
            boolean noProblemOccurred;
            do {
                String mode = bufferedReader.readLine().trim();
                noProblemOccurred =
                        handleSinglePlayerModeSettings(mode);
            } while (!noProblemOccurred);
            game.setPlayerAmount(Integer.parseInt(input));
            menuStatus = MenuStatus.PLAYERNAMES;
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
        while (game.getGameStatus().equals(GameStatus.RUNNING)) {
            for (Player player = game.getPlayerList().getFront(); player != null; player = player.getNext()) {
                if (!game.getGameStatus().equals(GameStatus.RUNNING)) {
                    break;
                }
                isStillPlaying = true;

                View.printPlayer(player.getName());
                checkForSinglePlayer();
                View.printMemory();
                String input = bufferedReader.readLine().trim();

                handleInputsDuringGame(input);

                executePlayerTurn(player, input, bufferedReader);

                if (isStillPlaying) {
                    player = player.getRear();
                }
            }
        }
    }

    /**
     * Executes the turn of a player
     *
     * @param player         who is currently playing
     * @param input          command the player used
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void executePlayerTurn(Player player, String input, BufferedReader bufferedReader) throws IOException {

        //The input is split by using spaces.
        String[] tokens = input.trim().split("\\s+");

        //Implementation of the game phases
        switch (game.getTurnStatus()) {

            //Is used if the turn hasn't been stated yet.
            case IDLE:
                executeIdle(tokens);
                break;

            //Is used if the turn has been stated.
            case ACTIVE:

                executeActive(tokens, player, bufferedReader);
                break;

            default:
                break;
        }
    }

    /**
     * Executes the second choice of a player.
     *
     * @param tokens         used to validate the input
     * @param player         who is currently playing
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void executeActive(String[] tokens, Player player,
                              BufferedReader bufferedReader) throws IOException {

        if (correctInput(tokens)) {
            secondRow = Integer.parseInt(tokens[0]);
            secondCol = Integer.parseInt(tokens[1]);

            CardStatus secondCardStatus = game.revealSecondCard(secondRow, secondCol);
            View.printBoard(game.getPlayingField());
            if (secondCardStatus.equals(CardStatus.FOUND)) {
                View.printAlreadyFound();
            } else if (secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                View.printSelectedTwice();
                game.setCardStatus(game.getCard(secondRow, secondCol), CardStatus.OPEN);
            } else {
                checkIfPairIsFound(player, bufferedReader);
            }
        }
    }

    /**
     * Checks if a Pair is found.
     *
     * @param player         who is currently playing
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void checkIfPairIsFound(Player player,
                                   BufferedReader bufferedReader) throws IOException {
        if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {
            //View.printBoard(game.getPlayingField());
            player.updateScore();
            player.getFoundCards().add(game.getPlayingField().getBoard()[secondRow][secondCol]);

            //Check if a player has a new achievement
            game.checkForAchievementsInGame(player);
            checkGameWon(bufferedReader);
            firstCol = firstRow = secondRow =
                    secondCol = Integer.MIN_VALUE;

        } else {
            isStillPlaying = false;
            View.printUnequalCards();

            //Reset streak for achievements
            player.getAchievements().setPairCounterStreak(0);

            //This is only for the single player mode with the setting "play with lives"
            if (game.getPlayerList().size() == 1
                    && singlePlayerMode.equals(SinglePlayerMode.LIFEPOINTS)) {
                game.getPlayerList().getPlayer(0).reduceLives();

                if (game.getPlayerList().getPlayer(0).getLives() == 0) {
                    View.printLoserMessage();
                    boolean exit = true;
                    while (exit) {
                        View.printMemory();
                        String choice = bufferedReader.readLine().trim();
                        exit = handleInputsAfterGame(choice);
                        game.getPlayerList().getPlayer(0).setLives(5);
                    }
                }
            }
        }
    }

    /**
     * Checks whether a game is won.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void checkGameWon(BufferedReader bufferedReader) throws IOException {
        if (game.areAllCardsFound()) {
            View.printAllPairsFound();
            View.printBoard(game.getPlayingField());
            List<String> winningPlayers = game.getPlayerList().winningPlayersToString();
            int highScore = game.getPlayerList().getHighestScore();

            //Check if a player has a new achievement
            game.checkForAchievementsAfterGame(game.getPlayerList());
            View.printGameSummary(winningPlayers, highScore);

            game.getDatabase().updateHighScoreHistory(winningPlayers, highScore);

            boolean exit = true;
            while (exit) {
                View.printMemory();
                String choice = bufferedReader.readLine().trim();

                exit = handleInputsAfterGame(choice);
            }

        } else {
            View.printFoundPair();
            View.printBoard(game.getPlayingField());
        }
    }

    /**
     * Execute the first phase of a turn.
     *
     * @param tokens to validate the input
     */
    public void executeIdle(String[] tokens) {
        if (correctInput(tokens)) {
            firstRow = Integer.parseInt(tokens[0]);
            firstCol = Integer.parseInt(tokens[1]);
            CardStatus firstCardStatus = game.revealFirstCard(firstRow, firstCol);
            if (firstCardStatus.equals(CardStatus.FOUND)) {
                View.printBoard(game.getPlayingField());
                View.printAlreadyFound();
            } else {
                View.printBoard(game.getPlayingField());
            }
        }
    }

    /**
     * Checks if the singlePlayerMode is used.
     */
    public void checkForSinglePlayer() {
        //This is only for the single player mode with the setting "play with lives"
        if (game.getPlayerList().size() == 1 && singlePlayerMode.equals(SinglePlayerMode.LIFEPOINTS)) {
            View.printlifes(game.getPlayerList().getPlayer(0).getLives());
        }

        //This is only for the single player mode with the setting "play on time"
        if (game.getPlayerList().size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
            if (game.getTime() == null) {
                View.printLoserMessage();
            } else {
                View.printTime(game.getTime().getCount());
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
        int numberOfInputs = 2;
        //Checks whether the input contained two parameters
        if (tokens.length < numberOfInputs) {
            View.error("Have not received enough parameters");
            return false;
        } else if (tokens.length > numberOfInputs) {
            View.error("Received too many parameters");
            return false;
        }

        boolean[] cache = new boolean[numberOfInputs];
        for (int i = 0; i < 2; i++) {
            if (tokens[i].length() == 1 && tokens[i].matches("\\d")) {
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
        }
        return cache[0] && cache[1];
    }

    /**
     * Selects the player-mode.
     *
     * @param input number of players selected
     * @return true if no error appeared
     */
    public int getPlayerMode(String input) {
        if (input.length() == 1) {
            if (input.matches("\\d")) {
                int num = Integer.parseInt(input);
                if (num > 4) {
                    View.error("Only a maximum of 4 players can take part");
                    return 0;
                } else if (num < 1) {
                    View.error("At least 1 player must take part");
                    return 0;
                } else return num;
            }

        }
        View.error("Entry was not a valid number");
        return 0;
    }

    /**
     * Checks whether a name can be used for a {@link Player}.
     *
     * @param playerName  the chosen name
     * @param playerNames lists all player names
     * @param pos         Position of the player
     * @return true whether the chosen name can be used or not
     */
    public boolean selectPlayerName(String playerName, String[] playerNames,
                                    int pos) {
        String newStr = playerName.trim();
        if (newStr.equals("")) {
            View.error("No input recognized! Please try again");
            return false;
        }
        if (playerName.equalsIgnoreCase("noName") || playerName.equalsIgnoreCase("nn")) {
            playerNames[pos] = "Player" + (pos + 1);
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

    /**
     * Handles the Settings of the single player mode.
     *
     * @param mode read input
     * @return true if no problem occurred.
     */
    public boolean handleSinglePlayerModeSettings(String mode) {
        mode = mode.toLowerCase();
        if (!(mode.equals("life") || mode.equals("time"))) {
            View.error("You have to choose between 'time' ore 'life'");
            return false;
        }
        if (mode.equals("life")) {
            singlePlayerMode = SinglePlayerMode.LIFEPOINTS;
        } else {
            singlePlayerMode = SinglePlayerMode.TIME;
        }
        return true;
    }

    /**
     * Handles the read inputs during a game and passes on the choices
     *
     * @param input is the command which should be handled.
     */
    public void handleInputsDuringGame(String input) {
        switch (input.toLowerCase()) {
            case "help", "h" -> View.printHelp();
            case "rules", "ru" -> {
                if (game.getPlayerList().size() > 1) {
                    View.printDescriptionMultiplayer();
                } else {
                    View.printDescriptionSinglePlayer();
                }
            }
            case "allRules", "ar" -> View.printDescriptionComplete();
            case "found", "f" -> View.printDiscardPile(game.getPlayerList());
            case "cheat" -> View.cheat(game.getPlayingField());
            case "score", "s" -> View.printScore(game.getPlayerList());
            case "menu", "m" -> {
                game.database.storeProgress(game.getPlayerList());
                game.returnToMenu(game.getPlayerList());
            }
            case "reset", "r" -> {
                game.database.storeProgress(game.getPlayerList());
                game.resetGame(game.getPlayerList());
                if (game.getPlayerList().size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
            }
            case "restart", "rs" -> {
                game.database.storeProgress(game.getPlayerList());
                game.restartGame(game.getPlayerList());
                if (game.getPlayerList().size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
            }
            case "quit", "q" -> {
                game.updateGamesPlayed();
                game.database.storeProgress(game.getPlayerList());
                game.quitGame();
            }
            case "show", "sp" -> showPlayer();
        }
    }

    /**
     * Shows the stats of a player.
     */
    public void showPlayer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a the name of the player to be shown:");
        String name = sc.next();
        for (int i = 0; i < game.getPlayerList().size(); i++) {
            if (name.equals(game.getPlayerList().getPlayer(i).getName())) {
                System.out.println("the achievement of " + name + "is: ");
                System.out.println(game.getPlayerList().getPlayer(i).playerProfileToString());
            }
        }
    }

    /**
     * Handles the read inputs during a game and passes on the choices.
     *
     * @param input is the command which should be handled.
     * @return the exit value
     */
    public boolean handleInputsAfterGame(String input) {
        switch (input.toLowerCase()) {
            case "menu", "m" -> {
                game.database.storeProgress(game.getPlayerList());
                game.returnToMenu(game.getPlayerList());
                return false;
            }
            case "reset", "r" -> {
                game.database.storeProgress(game.getPlayerList());
                game.resetGame(game.getPlayerList());
                if (game.getPlayerList().size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
                return false;
            }
            case "restart", "rs" -> {
                game.database.storeProgress(game.getPlayerList());
                game.restartGame(game.getPlayerList());
                if (game.getPlayerList().size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
                return false;
            }
            case "quit", "q" -> {
                game.updateGamesPlayed();
                game.database.storeProgress(game.getPlayerList());
                game.quitGame();
                return false;
            }
            default -> {
                View.error("No valid command "
                        + "recognized");
                return true;
            }
        }
    }
}
