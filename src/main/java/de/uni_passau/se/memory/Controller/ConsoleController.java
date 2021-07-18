package de.uni_passau.se.memory.Controller;

import de.uni_passau.se.memory.Model.*;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
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
     * Required user input length.
     * A user cant input more than 2 values at once.
     */
    public static int REQUIRED_INPUT_AMOUNT = 2;
    /**
     * Stores the current game.
     */
    private final Game game;
    /**
     * Stores the current playerList.
     */
    private PlayerList playerList;
    /**
     * Stores the database of a game.
     */
    private final Database database;
    /**
     * Stores the current playingFiled.
     */
    private final PlayingField playingField;
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
     * Stores the active player.
     */
    private Player activePlayer;
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
        this.game = Wrapper.getGame();
        this.playerList = game.getPlayerList();
        this.playingField = game.getPlayingField();
        this.menuStatus = MenuStatus.PLAYERMODE;
        this.database = game.getDatabase();
        this.singlePlayerMode = SinglePlayerMode.DEFAULT;
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
                default -> throw new IllegalStateException();
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

                default -> throw new IllegalStateException();
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
        int size = validatePlayingFieldBoardSize(bufferedReader.readLine().trim());
        if (size != 0) {
            setPlayingFieldBordSize(playingField, size);
            menuStatus = MenuStatus.CARDSET;
        }
    }

    /**
     * Sets the size of a given playingField.
     *
     * @param playingField whose size is set
     * @param size         to be set
     */
    public void setPlayingFieldBordSize(PlayingField playingField, int size) {
        playingField.setBoard(size);
    }

    /**
     * Validates a given size for playingField.
     *
     * @param size to validated
     * @return validated size
     */
    public int validatePlayingFieldBoardSize(String size) {
        return playingField.selectBoardSize(size);
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
        if (validatePlayingFieldCardSet(playingField,
                bufferedReader.readLine().trim())) {
            fillPlayingFieldWithCards(playingField);
            menuStatus = MenuStatus.PLAYERMODE;
            game.setGameStatus(GameStatus.RUNNING);
            View.printBoard(playingField);

            //This is only for the single player mode with the setting "play on time"
            if (playerList.size() == 1
                    && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                game.startTimer();
            }
        }
        updateCurrentDataBase(database);
    }

    /**
     * Fill a playingFiled with cards.
     *
     * @param playingField to be filled
     */
    public void fillPlayingFieldWithCards(PlayingField playingField) {
        playingField.fillWithCards();
    }

    /**
     * Validate a given cardSet.
     *
     * @param playingField whose cardSet is validated
     * @param cardSet      to be validated
     * @return validated cardSet
     */
    public boolean validatePlayingFieldCardSet(PlayingField playingField,
                                               String cardSet) {
        return playingField.selectCardSet(cardSet);
    }

    /**
     * Updates the dataBase.
     *
     * @param database to be used.
     */
    public void updateCurrentDataBase(Database database) {
        database.updateDataBase(game.getPlayerAmount(), playerList);
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
        if (playerList.size() == 1 && singlePlayerMode.equals(SinglePlayerMode.LIFEPOINTS)) {
            playerList.setPlayerLives(playerList.getPlayer(0), Game.FIVE_LIVES);
        }
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
            for (activePlayer = playerList.getFront(); activePlayer != null; activePlayer =
                    playerList.getNextPlayer(activePlayer)) {
                if (!game.getGameStatus().equals(GameStatus.RUNNING)) {
                    break;
                }
                View.printPlayer(playerList.getPlayerName(activePlayer));
                checkForSinglePlayer();
                View.printMemory();
                String input = bufferedReader.readLine().trim();

                if (handleInputsDuringGame(input)) {
                    executePlayerTurn(input, bufferedReader);
                }
            }
        }
    }

    /**
     * Executes the turn of a player
     *
     * @param input          command the player used
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void executePlayerTurn(String input, BufferedReader bufferedReader) throws IOException {

        //The input is split by using spaces.
        String[] tokens = input.trim().split("\\s+");

        //Implementation of the game phases
        switch (game.getTurnStatus()) {

            //Is used if the turn hasn't been stated yet.
            case IDLE -> executeIdle(tokens);


            //Is used if the turn has been stated.
            case ACTIVE -> executeActive(tokens, bufferedReader);
            default -> throw new IllegalStateException();
        }
    }

    /**
     * Executes the second choice of a player.
     *
     * @param tokens         used to validate the input
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void executeActive(String[] tokens, BufferedReader bufferedReader) throws IOException {

        if (correctInput(tokens)) {
            secondRow = Integer.parseInt(tokens[0]);
            secondCol = Integer.parseInt(tokens[1]);

            CardStatus secondCardStatus = game.revealSecondCard(secondRow, secondCol);
            View.printBoard(playingField);
            if (secondCardStatus.equals(CardStatus.FOUND)) {
                View.printAlreadyFound();
            } else if (secondCardStatus.equals(CardStatus.AlREADYOPEN)) {
                View.printSelectedTwice();
                game.setCardStatus(game.playingField.getCard(secondRow, secondCol), CardStatus.OPEN);
            } else {
                checkIfPairIsFound(bufferedReader);
            }
        }
    }

    /**
     * Checks if a Pair is found.
     *
     * @param bufferedReader provides a connection to the console
     * @throws IOException on input error
     */
    public void checkIfPairIsFound(BufferedReader bufferedReader) throws IOException {
        if (game.pairCheck(firstRow, firstCol, secondRow, secondCol)) {

            //View.printBoard(playingField);
            activePlayer.updateScore();
            activePlayer.getFoundCards().add(playingField.getBoard()[secondRow][secondCol]);

            //Check if a player has a new achievement
            game.checkForAchievementsInGame(activePlayer);
            checkGameWon(bufferedReader);
            firstCol = firstRow = secondRow = secondCol = Integer.MIN_VALUE;
            activePlayer = playerList.getPlayerRear(activePlayer);

        } else {
            View.printUnequalCards();

            //Reset streak for achievements
            activePlayer.setPlayerPairCounterStreak(activePlayer.getAchievements(), 0);

            //This is only for the single player mode with the setting "play with lives"
            if (playerList.size() == 1
                    && singlePlayerMode.equals(SinglePlayerMode.LIFEPOINTS)) {
                playerList.reducePlayerLives(playerList.getPlayer(0));

                if (playerList.getPlayerLives(playerList.getPlayer(0)) == 0) {
                    View.printLoserMessage();
                    boolean exit = true;
                    while (exit) {
                        View.printMemory();
                        String choice = bufferedReader.readLine().trim();
                        exit = handleInputsAfterGame(choice);
                        playerList.setPlayerLives(playerList.getPlayer(0), Game.FIVE_LIVES);
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
            View.printBoard(playingField);
            List<String> winningPlayers = playerList.winningPlayersToString();
            int highScore = playerList.getHighestScore();

            //Check if a player has a new achievement
            game.checkForAchievementsAfterGame(playerList);
            View.printGameSummary(winningPlayers, highScore);

            database.updateHighScoreHistory(winningPlayers, highScore);

            boolean exit = true;
            while (exit) {
                View.printMemory();
                String choice = bufferedReader.readLine().trim();

                exit = handleInputsAfterGame(choice);
            }

        } else {
            View.printFoundPair();
            View.printBoard(playingField);
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
                View.printBoard(playingField);
                View.printAlreadyFound();
            } else {
                View.printBoard(playingField);
            }
        }
        activePlayer = playerList.getPlayerRear(activePlayer);
    }

    /**
     * Checks if the singlePlayerMode is used.
     */
    public void checkForSinglePlayer() {
        //This is only for the single player mode with the setting "play with lives"
        if (playerList.size() == 1 && singlePlayerMode.equals(SinglePlayerMode.LIFEPOINTS)) {
            View.printLives(playerList.getPlayerLives(playerList.getPlayer(0)));
        }

        //This is only for the single player mode with the setting "play on time"
        if (playerList.size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
            if (game.getTime() == null) {
                View.printLoserMessage();
            } else {
                View.printTime(game.getTimeCount());
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

        //Checks whether the input contains two parameters
        if (tokens.length < REQUIRED_INPUT_AMOUNT) {
            View.error("Have not received enough parameters");
            return false;
        } else if (tokens.length > REQUIRED_INPUT_AMOUNT) {
            View.error("Received too many parameters");
            return false;
        }

        boolean[] cache = new boolean[]{false, false};

        for (int i = 0; i < REQUIRED_INPUT_AMOUNT; i++) {
            if (tokens[i].length() == 1 && tokens[i].matches("\\d")) {
                if (Integer.parseInt(tokens[i]) < playingField.getBoard().length) {
                    cache[i] = true;
                } else {
                    if (i == 0) {
                        View.error("First entry was out of range");
                    } else {
                        View.error("Second entry was out of range");
                    }
                    activePlayer = playerList.getPlayerRear(activePlayer);
                }
            } else {
                if (i == 0) {
                    View.error("First entry was not a valid number");
                } else {
                    View.error("Second entry was not a valid number");
                }
                activePlayer = playerList.getPlayerRear(activePlayer);
            }
        }
        return cache[0] && cache[1];
    }

    /**
     * Selects the playerMode.
     *
     * @param input number of players selected
     * @return true if no error appeared
     */
    public int getPlayerMode(String input) {
        if (input.length() == 1) {
            if (input.matches("\\d")) {
                int num = Integer.parseInt(input);
                if (num > PlayerList.MAX_PLAYER_AMOUNT) {
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
     * Handles the settings of the single player mode.
     *
     * @param mode read input
     * @return true if no problem occurred.
     */
    public boolean handleSinglePlayerModeSettings(String mode) {
        mode = mode.toLowerCase();
        if (!(mode.equals("life") || mode.equals("time"))) {
            View.error("You have to choose between 'time' or 'life'");
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
     * Handles the read inputs during a game and passes on the choices.
     *
     * @param input is the command which should be handled.
     * @return false if the game player used a command but the game continues
     */
    public boolean handleInputsDuringGame(String input) {
        switch (input.toLowerCase()) {
            case "help", "h" -> View.printHelp();
            case "rules", "ru" -> {
                if (playerList.size() > 1) {
                    View.printDescriptionMultiplayer();
                } else {
                    View.printDescriptionSinglePlayer();
                }
            }
            case "allRules", "ar" -> View.printDescriptionComplete();
            case "found", "f" -> {
                View.printDiscardPile(playerList.foundCardsToString());
                activePlayer = playerList.getPlayerRear(activePlayer);
                return false;
            }
            case "cheat" -> {
                View.cheat(playingField);
                activePlayer = playerList.getPlayerRear(activePlayer);
                return false;
            }
            case "score", "s" -> View.printScore(playerList);
            case "menu", "m" -> {
                database.storeProgress(playerList);
                game.returnToMenu(playerList);
            }
            case "reset", "r" -> {
                database.storeProgress(playerList);
                game.resetGame(playerList);
                if (playerList.size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
            }
            case "restart", "rs" -> {
                database.storeProgress(playerList);
                game.restartGame(playerList);
                View.printBoard(playingField);
                if (playerList.size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
            }
            case "quit", "q" -> {
                game.updateGamesPlayed();
                database.storeProgress(playerList);
                game.quitGame();
            }
            case "show", "sp" -> {
                showPlayer();
                activePlayer = playerList.getPlayerRear(activePlayer);
                return false;
            }
        }
        return true;
    }

    /**
     * Shows the stats of a player.
     */
    public void showPlayer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a the name of the player to be shown:");
        String name = sc.next();
        for (int i = 0; i < playerList.size(); i++) {
            if (name.equals(playerList.getPlayerName(playerList.getPlayer(i)))) {
                System.out.println("the achievement of " + name + "is: ");
                System.out.println(playerList.getPlayerProfileToString(playerList.getPlayer(i)));
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
                database.storeProgress(playerList);
                game.returnToMenu(playerList);
                return false;
            }
            case "reset", "r" -> {
                database.storeProgress(playerList);
                game.resetGame(playerList);
                if (playerList.size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
                return false;
            }
            case "restart", "rs" -> {
                database.storeProgress(playerList);
                game.restartGame(playerList);
                View.printBoard(playingField);
                if (playerList.size() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    game.startTimer();
                }
                return false;
            }
            case "quit", "q" -> {
                game.updateGamesPlayed();
                database.storeProgress(playerList);
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

    /**
     * Used for testing.
     */
    public void generateNewPlayerList(){
        this.playerList = new PlayerList();
        playerList.addPlayer("Player1");
        activePlayer = playerList.getPlayer(0);
    }
}
