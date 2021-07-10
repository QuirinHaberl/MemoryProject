package de.uni_passau.se.memory.gui;

import de.uni_passau.se.memory.Model.Card;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Player;
import de.uni_passau.se.memory.Model.PlayerList;
import de.uni_passau.se.memory.Model.PlayingField;

import java.util.List;

/**
 * This class represents the view of the MVC-architecture.
 */
public final class View {
    /**
     * Utility class needs private constructor.
     */
    private View() {
    }

    /**
     * Prints the current {@code playingField}.
     *
     * @param playingField current {@link PlayingField}
     */
    public static void printBoard(PlayingField playingField) {
        StringBuilder line = new StringBuilder("  ");
        for (int i = 0; i < playingField.getBoard().length; i++) {
            line.append(i).append(" ");
        }
        for (int row = 0; row < playingField.getBoard().length; row++) {
            line = new StringBuilder(line + "\n" + row + " ");
            for (int col = 0; col < playingField.getBoard()[row].length; col++) {
                if (playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.OPEN) ||
                        playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.AlREADYOPEN)) {
                    line.append(playingField.getBoard()[row][col].visualizeCard()).append(" ");
                } else if (playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.CLOSED)) {
                    line.append("X ");
                } else if (playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.FOUND)) {
                    line.append("  ");
                }
            }
        }
        System.out.println(line);
    }

    /**
     * Prints the current {@code playingField}.
     *
     * @param playingField current {@link PlayingField}
     */
    public static void cheat(PlayingField playingField) {
        StringBuilder line = new StringBuilder("  ");
        for (int i = 0; i < playingField.getBoard().length; i++) {
            line.append(i).append(" ");
        }
        for (int row = 0; row < playingField.getBoard().length; row++) {
            line = new StringBuilder(line + "\n" + row + " ");
            for (int col = 0; col < playingField.getBoard()[row].length; col++) {
                if (playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.OPEN)) {
                    line.append(playingField.getBoard()[row][col].visualizeCard()).append(" ");
                } else if (playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.CLOSED)) {
                    line.append(playingField.getBoard()[row][col].visualizeCard()).append(" ");
                } else if (playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.FOUND)) {
                    line.append("  ");
                }
            }
        }
        System.out.println(line);
    }

    /**
     * Print all {@link Card}'s a {@link Player} has found.
     *
     * @param players current {@link PlayerList}
     */
    public static void printDiscardPile(PlayerList players) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < players.size(); i++) {
            String playerName = getPlayerName(players.getPlayer(i));
            str.append(playerName).append(
                    ":" +
                            " ");
            for (int j = 0; j < getFoundCardSize(getFoundCards(players.getPlayer(i))); j++) {
                str.append(getFoundCards(players.getPlayer(i)).get(j).visualizeCard()).append(" ");
            }
            str.append("\n");
        }
        System.out.println(str.append("]"));
    }

    /**
     * Gets the size of the foundCardList.
     *
     * @param cardsList whose size is requested
     * @return the size of cardsList
     */
    public static int getFoundCardSize(List<Card> cardsList) {
        return cardsList.size();
    }

    /**
     * Gets the foundCards of a given player.
     *
     * @param player whose foundCards are requested
     * @return the foundCards of a player
     */
    public static List<Card> getFoundCards(Player player) {
        return player.getFoundCards();
    }

    /**
     * Get the name of a player.
     *
     * @param player whose name is requested
     * @return the name of a player
     */
    public static String getPlayerName(Player player) {
        return player.getName();
    }

    /**
     * Prints the initial introduction to a game of memory.
     */
    public static void printDefault() {
        System.out.println("Welcome to the memory game of group 19!\n" +
                "This is an ordinary game of memory. " +
                "Just follow the instructions and you can use 'help' " +
                "to obtain further information during the game.");
    }

    /**
     * Prints a Multiplayer description of the memory-game.
     */
    public static void printDescriptionMultiplayer() {
        System.out.println("""

                1. On each turn, a player turns over any two cards (one at a time).\s
                2. To open a card, simply input its position, i.e. 2 1\s
                3. The board will show your selected card.\s
                4. Select a second card, same as before.\s
                5. If a player successfully matches a pair they receive a point and that player gets another turn.\s
                6. The found pair is removed from the board.\s
                7. When a player turns over two cards that do not match, those cards are turned face down again \s
                   (at the same position) and it becomes the next player's turn.\s
                8. The trick is to remember which cards are where.\s
                9. The person with the most pairs at the end of the game wins.
                """);
    }

    /**
     * Prints a Single Player description of the memory-game.
     */

    public static void printDescriptionSinglePlayer() {
        System.out.println("""

                1. On each turn, you can turn over any two cards (one at a time).\s
                2. To open a card, simply input its position, i.e. 2 1\s
                3. The board will show you the selected card.\s
                4. Select a second card, same as before.\s
                5. If you successfully match a pair you receive a point, and you get another turn.\s
                6. The found pair is removed from the board.\s
                7. When you select two cards that do not match, those cards are turned face down again \s
                   (in the same position) and you get to try again.\s
                8. The trick is to remember which cards are where.\s
                """);
    }

    /**
     * Prints a Complete description of the memory-game.
     */
    public static void printDescriptionComplete() {
        System.out.println("""

                Welcome to the fantastic game of memory.\s
                This fun game helps you to improve your own memory and is also fun to play against friends. \s
                \s
                \s
                Game modes: \s
                    1.Single Player \s
                    1. On each turn, you can turn over any two cards (one at a time).\s
                    2. To open a card, simply input its position, i.e. 2 1\s
                    3. The board will show you the selected card.\s
                    4. Select a second card, same as before.\s
                    5. If you successfully match a pair you receive a point, and you get another turn.\s
                    6. The found pair is removed from the board.\s
                    7. When you select two cards that do not match, those cards are turned face down again \s
                       (in the same position) and you get to try again.\s
                    8. The trick is to remember which cards are where.\s
                    \s
                    2.Multiplayer \s
                    1. On each turn, a player turns over any two cards (one at a time).\s
                    2. To open a card, simply input its position, i.e. 2 1\s
                    3. The board will show your selected card.\s
                    4. Select a second card, same as before.\s
                    5. If a player successfully matches a pair they receive a point and that player gets another turn.\s
                    6. The found pair is removed from the board.\s
                    7. When a player turns over two cards that do not match, those cards are turned face down again \s
                       (at the same position) and it becomes the next player's turn.\s
                    8. The trick is to remember which cards are where.\s
                    9. The person with the most pairs at the end of the game wins.
                    \s
                    \s
                Board Sizes:\s
                At the beginning of the game (after choosing the Game mode) you can choose the board size you would like to play with.\s
                You can choose between:\s
                2 -->4 cards in sum on the board\s
                4 -->16 cards in sum on the board\s
                6 -->36 cards in sum on the board\s
                8 -->64 cards in sum on the board\s
                \s
                                
                Card Sets:\s
                You can choose which type of cards you want to play with.\s
                You can choose between:\s
                L-->cards "image" will show letters when turned over\s
                D-->cards "image" will show digits when turned over\s
                \s
                \s
                We hope you have fun with this game!\s
                Your developers,\s
                Quirin, Jan, Isabella, Daqian and Florian
                """);
    }

    /**
     * Prints a list of accepted commands.
     */
    public static void printHelp() {
        System.out.println("""                                
                All possible commands are:\s
                    help:       Shows a list of possible commands\s
                    rules:      Shows the rules of the game\s
                    allRules:   Shows the complete rule set of the game\s
                    found:      Shows the cards every player has found\s
                    score:      Shows the scores of all players\s
                    
                    menu:       Returns you back to main menu\s
                    reset:      Resets the running game to start state\s
                    restart:    Restarts the running game with repositioned cards\s
                    quit:       Quits the game\s
                    
                All sorts of commands can be written in lowercase and uppercase letters.\s
                They can also be called by using abbreviations consisting of their first letter.\s
                """);
    }

    /**
     * Prints every {@link Player} and its {@code de.uni_passau.se.memory.Model.Enums.Player.score}.
     *
     * @param players list of all players
     */
    public static void printScore(PlayerList players) {
        String[] playerNames = new String[players.size()];
        int[] scores = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            playerNames[i] = getPlayerName(players.getPlayer(i));
            scores[i] = getPlayerScore(players.getPlayer(i));
        }
        //Insertion sort
        for (int i = 1; i < scores.length; i++) {
            int currentElement = scores[i];
            String currentName = playerNames[i];
            PlayerList.sortingHelper(playerNames, scores, i, currentName, currentElement);
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println("[" + playerNames[i] + ": "
                    + scores[i] + "]");
        }
    }

    /**
     * Get the score of a given player.
     *
     * @param player whose score ist requested
     * @return the score of a player
     */
    public static int getPlayerScore(Player player) {
        return player.getScore();
    }


    /**
     * Prints a specified error-massage.
     *
     * @param specification Specification of the error message.
     */
    public static void error(String specification) {
        System.out.println("Error! " + specification);
    }


    /**
     * Prints "memory>" to indicate that user-input is expected.
     */
    public static void printMemory() {
        System.out.print("memory> ");
    }

    /**
     * Prints a {@link Player}'s name.
     *
     * @param name of the printed {@link Player}
     */
    public static void printPlayer(String name) {
        System.out.println(name + ":");
    }

    /**
     * Informs the user to input the amount of player.
     */
    public static void printSelectPlayerAmount() {
        System.out.println("How many players do you want? Choose between 1 and 4. ");
    }

    /**
     * Informs the user to input the board-size.
     */
    public static void printSelectBoardSize() {
        System.out.println("Type '2', '4', '6', '8' to select the board-size:");
    }

    /**
     * Informs the user to input the {@link de.uni_passau.se.memory.Model.Enums.CardSet}.
     */
    public static void printSelectCardSet() {
        System.out.println("Type 'L' for letters or 'D' for digits:");
    }

    /**
     * Prints that a {@link Card} was already found.
     */
    public static void printAlreadyFound() {
        System.out.println("The selected card was already found!");
    }

    /**
     * Prints that a {@link Card} was selected twice.
     */
    public static void printSelectedTwice() {
        System.out.println("You've selected the same card twice!");
    }

    /**
     * Prints that all {@link Card} are found.
     */
    public static void printAllPairsFound() {
        System.out.println("All Pairs are found.");
    }


    /**
     * Prints that pair of {@link Card}'s was found.
     */
    public static void printFoundPair() {
        System.out.println("You've found a pair! It is your turn again.");
    }

    /**
     * Prints that the selected pair was not equal.
     */
    public static void printUnequalCards() {
        System.out.println("Cards are not equal!");
    }

    /**
     * Prints a PlayerNameRequest.
     *
     * @param index of the player whose information is requested
     */
    public static void printPlayerNameRequest(int index) {
        System.out.println("Player" + index + " enter your preferred name or"
                + " the command 'noName' (if you don't want to choose a name):");
    }

    /**
     * Prints the winner notification and the description of the next input options.
     *
     * @param winningPlayers of the current game
     * @param highestScore   the highest score a player reached
     */
    public static void printGameSummary(List<String> winningPlayers, int highestScore) {
        printWinningPlayers(winningPlayers);
        System.out.print(" won the game with a score of ");
        System.out.println(highestScore);
        System.out.println("""
                 Please select by entering a command whether you want to\s
                 return to the main menu ('menu'),\s
                 reset the current game ('reset'),\s
                 restart the current game ('restart') or\s
                 quit the game ('quit')\s
                """);
    }

    /**
     * Prints the message of a lost game.
     */
    public static void printLoserMessage() {
        System.out.println("What a pity... You've lost the game!");
        System.out.println("""
                 Please select by entering a command whether you want to\s
                 return to the main menu ('menu'),\s
                 reset the current game ('reset'),\s
                 restart the current game ('restart') or\s
                 quit the game ('quit')\s
                """);
    }

    /**
     * Prints all players and their scores.
     *
     * @param winningPlayers the highest score a player reached
     */
    public static void printWinningPlayers(List<String> winningPlayers) {
        for (int i = 0; i < winningPlayers.size(); i++) {
            if (i != winningPlayers.size() - 1) {
                System.out.print(winningPlayers.get(i) + " and ");
            } else {
                System.out.print(winningPlayers.get(i));
            }
        }
    }

    /**
     * Prints the request to adjust the single player mode settings
     */
    public static void printSinglePlayerModeSettings() {
        System.out.println("You choose the Single-Player-Mode." +
                "To play with lives please enter 'life' else enter 'time' to " +
                "play against time.");
    }

    /**
     * Prints the number of lives a player has
     *
     * @param lives of the player
     */
    public static void printLives(int lives) {
        System.out.println("<3 lives: " + lives);
    }

    /**
     * Prints the remaining time a player has
     *
     * @param remainingTime of the player
     */
    public static void printTime(int remainingTime) {
        System.out.println("This is your remaining time: " + remainingTime);
    }

    /**
     * Prints an achievement a player has earned.
     *
     * @param specification achievement to be printed
     * @param player        who has earned an achievement
     */
    public static void printAchievement(String specification, Player player) {
        System.out.println("\n" + player.getName() + " has new achievements: ");
        System.out.println(specification);
    }
}
