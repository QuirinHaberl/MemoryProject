package Control;

import Model.*;
import Model.Enums.CardStatus;
import Model.Enums.GameStatus;
import View.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The controller of the MVC-architecture.
 */
public class Controller {

    /**
     * Stores the current {@link Game}.
     */
    private final Game game;

    /**
     * Stores the current {@link HighScoreHistory}.
     */
    private HighScoreHistory highScoreHistory;

    /**
     * Stores the current {@link MenuStatus}.
     */
    private MenuStatus menuStatus;

    /**
     * Stores the current {@link SinglePlayerMode}.
     */
    private SinglePlayerMode singlePlayerMode;

    /**
     * Stores player-profiles
     */
    Database database;

    /**
     * Constructs a new {@link Controller}.
     */
    public Controller() {
        this.game = new Game();
        this.highScoreHistory = new HighScoreHistory();
        this.menuStatus = MenuStatus.PLAYERMODE;
        database = new Database();
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
        //Controls whether the player mode has already been selected
        boolean firstIssue = true;

        int playerAmount = 0;

        while (game.getGameStatus().equals(GameStatus.MENU)) {

            switch (menuStatus) {

                //Is used to determine the number of players
                // and to set (if needed) the single player mode.
                case PLAYERMODE -> {
                    if (firstIssue) {
                        View.printSelectPlayerAmount();
                    }
                    View.printMemory();
                    String input = bufferedReader.readLine().trim();
                    if (selectPlayerMode(input) > 1) {
                        playerAmount = Integer.parseInt(input);
                        menuStatus = MenuStatus.PLAYERNAMES;
                        firstIssue = true;
                    } else if (selectPlayerMode(input) == 1) {
                        View.printSinglePlayerModeSettings();
                        View.printMemory();
                        String mode = bufferedReader.readLine().trim();
                        if (handleSinglePlayerModeSettings(mode)) {
                            playerAmount = Integer.parseInt(input);
                            menuStatus = MenuStatus.PLAYERNAMES;
                        }
                        firstIssue = true;
                    } else {
                        firstIssue = false;
                    }
                }

                //Used to set the names of all players.
                case PLAYERNAMES -> {
                    String[] playerNames = new String[playerAmount];
                    for (int i = 0; i < playerAmount; i++) {
                        if (firstIssue) {
                            View.printPlayernameRequest(i + 1);
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
                    game.useProfile(database.getPlayerProfiles());
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

                        //This is only for the single player mode with the setting "play on time"
                        if (game.getPlayerList().getCount() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                            game.startTimer();
                        }

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

                //This is only for the single player mode with the setting "play with lifes"
                if (game.getPlayerList().getCount() == 1 && singlePlayerMode.equals(SinglePlayerMode.LIFEPOINTS)) {
                    View.printLifes(game.getPlayerList().getPlayer(0).getLifes());
                }

                //This is only for the single player mode with the setting "play on time"
                if (game.getPlayerList().getCount() == 1 && singlePlayerMode.equals(SinglePlayerMode.TIME)) {
                    View.printTime(game.getTime().getCount());
                }

                View.printMemory();
                String input = bufferedReader.readLine().trim();

                boolean saveBreak = handleInputsDuringGame(input, player);

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
                                        View.printAllPairsFound();
                                        View.printBoard(game.getPlayingField());
                                        checkForAchievements(game.getPlayerList());
                                        String[] winningPlayers = game.getPlayerList().winningPlayersToString();
                                        int[] highScore =
                                                game.getPlayerList().getHighestScore();
                                        storeProgress();
                                        View.printGameSummary(winningPlayers,
                                                highScore[0]);
                                        highScoreHistory.updateHighScoreHistory(winningPlayers, highScore[0]);

                                        boolean exit = true;
                                        while (exit) {
                                            View.printMemory();
                                            String choice = bufferedReader.readLine().trim();
                                            exit = handleInputsAfterGame(choice, player);
                                        }

                                    } else {
                                        View.printFoundPair();
                                        checkForAchievements(player);
                                        View.printBoard(game.getPlayingField());
                                    }
                                } else {
                                    View.printUnequalCards();
                                    counter = counter + 1;

                                    //Reset streak for achievements
                                    player.getAchievements().resetPairCounterStreak();

                                    //This is only for the single player mode with the setting "play with lifes"
                                    if (game.getPlayerList().getCount() == 1
                                            && singlePlayerMode.equals(SinglePlayerMode.LIFEPOINTS)) {
                                        game.getPlayerList().getPlayer(0).reduceLifes();

                                        if (game.getPlayerList().getPlayer(0).getLifes() == 0) {
                                            View.printLoserMessage();
                                            boolean exit = true;
                                            while (exit) {
                                                View.printMemory();
                                                String choice = bufferedReader.readLine().trim();
                                                exit = handleInputsAfterGame(choice, player);
                                                game.getPlayerList().getPlayer(0).setLifes(5);
                                            }
                                        }
                                    }
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
     * Checks weather a {@link Player} has earned a new achievement
     *
     * @param player who is being checked
     */
    public void checkForAchievements(Player player) {
        if (player.getAchievements().updateInstanceAchievements()) {
            View.printAchievement(player.getAchievements().getCurrentAchievement(), player);
        }
    }

    /**
     * Checks weather multiple players have earned an achievement.
     *
     * @param players who are being checked
     */
    public void checkForAchievements(PlayerList players) {
        int highestScore = players.getHighestScore()[0];

        for (int i = 0; i < players.getCount(); i++) {
            if (players.getPlayerScore(i) == highestScore) {
                if (players.getPlayer(i).getAchievements().updateGlobalAchievements()) {
                    View.printAchievement(players.getPlayer(i).getAchievements().getCurrentAchievement(), players.getPlayer(i));
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
    public int selectPlayerMode(String input) {
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
     * @return if the setting was successful
     */
    public boolean handleSinglePlayerModeSettings(String mode) {
        mode = mode.toLowerCase();
        if (!(mode.equals("life") || mode.equals("time"))) {
            View.error("You have to choose between 'time' ore 'life'");
            return false;
        }
        if (mode.equals("life")) {
            singlePlayerMode = SinglePlayerMode.LIFEPOINTS;
            return true;
        } else if (mode.equals("time")) {
            singlePlayerMode = SinglePlayerMode.TIME;
            return true;
        }
        return false;
    }

    /**
     * Handles the read inputs during a game and passes on the choices
     * TODO Versuch die Befehle in eine Methode zu packen.
     * Gerne nochmal überarbeiten!
     *
     * @param input  is the command which should be handled.
     * @param player the affected player
     * @return savebreak
     */
    public boolean handleInputsDuringGame(String input, Player player) {
        boolean saveBreak = false;
        switch (input.toLowerCase()) {
            case "help", "h" -> {
                View.printHelp();
                saveBreak = true;
            }
            case "rules", "ru" -> {
                if (game.getPlayerList().getCount() > 1) {
                    View.printDescriptionMultiplayer();
                } else {
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
            case "cheat" -> {
                View.cheat(game.getPlayingField());
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
                database.storePlayerProfiles(game.getPlayerList());
                game.quitGame();
                saveBreak = true;
                try {
                    highScoreHistory.saveHighScoreHistory();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case "show current" ->{       //durch dem kann man nur den Spieler schauen, der momentan im Spiel ist.
                Scanner sc = new Scanner(System.in);  // wenn der spieler momentan nicht im Spiel aber in profiles.csv ist, kann man nicht schauen.
                System.out.println("input the name of player in this current game whom you want to know:");
                String name = sc.next();
                for(int i = 0; i < game.getPlayerList().getCount(); i ++){
                    if(name.equals(game.getPlayerList().getPlayer(i).getName())){
                        System.out.println("the achievement of " + name + "is: ");
                        System.out.println(game.getPlayerList().getPlayer(i).getPlayerProfile());
                    }
                }
                saveBreak = true;
            }
        }
        return saveBreak;
    }

    /**
     * Handles the read inputs during a game and passes on the choises
     * TODO Versuch die Befehle in eine Methode zu packen.
     * Gerne nochmal überarbeiten!
     *
     * @param input  is the command which should be handled.
     * @param player the affected player
     * @return the exit value
     */
    public boolean handleInputsAfterGame(String input, Player player) {
        switch (input.toLowerCase()) {
            case "menu", "m" -> {
                game.returnToMenu(game.getPlayerList());
                return false;
            }
            case "reset", "r" -> {
                player = game.resetGame(game.getPlayerList());
                return false;
            }
            case "restart", "rs" -> {
                player = game.restartGame(game.getPlayerList());
                return false;
            }
            case "quit", "q" -> {
                game.quitGame();
                try {
                    highScoreHistory.saveHighScoreHistory();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
     * Stores the progress of the current players in their playerProfiles.
     */
    public void storeProgress() {
        database.storePlayerProfiles(game.getPlayerList());
    }
}