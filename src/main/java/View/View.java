package View;

import Model.*;

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
     * Visualizes the current {@link Game}
     */
    public static void printBoard(PlayingField playingField) {
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
                    line.append("X ");
                } else if (playingField.getBoard()[row][col].getCardStatus().equals(CardStatus.FOUND)) {
                    line.append("  ");
                }
            }
        }
        System.out.println(line);
    }

    /**
     * Prints a description of the memory-game when you enter the game
     */
    public static void printDescription() {
        System.out.println("""

                1. On each turn, a player turns over any two cards (one at a time).\s
                2. To open a card, simply input its position, i.e. 2 1\s
                3. The board will show you the selected card.\s
                4. Select a second card, same as before.\s
                5. If they successfully match a pair they receive a point, and that player gets another turn.\s
                6. The found pair is removed from the board.\s
                7. When a player turns over two cards that do not match, those cards are turned face down again \s
                   (in the same position) and it becomes the next player’s turn.\s
                8. The trick is to remember which cards are where.\s
                9. The person with the most pairs at the end of the game wins.
                """);
    }

    /**
     * Prints out a list of possible commands when you enter the help command.
     */
    public static void printHelp() {
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

    public static void printMemory() {
        System.out.print("memory> ");
    }

    public static void printPlayer(String name) {
        System.out.println(name + ":");
    }

    /**
     * prints a specified errormessage.
     *
     * @param specification Specification of the error message.
     */
    public static void printGraveyard() {
    }

    /**
     * Prints every {@link Player} and its {@code Model.Enums.Player.score}
     *
     * @param players list of all players
     */
    public static void printScore(PlayerList players) {
        for (int i = 0; i < players.getCount(); i++) {
            System.out.println("[" + players.getPlayer(i).getName() + ": "
                    + players.getPlayer(i).getScore() + "]");
        }
    }

    /**
     * Outputs a specified error message.
     *
     * @param specification Specification of the error message.
     */
    public static void error(String specification) {
        System.out.println("Error! " + specification);
    }

    public static void printSelectPlayerAmount() {
        System.out.println("How many players do you want? Choose between 1 and 4. ");
    }

    public static void printSelectBoardSize() {
        System.out.println("Type '2', '4', '6', '8' to select the board-size:");
    }

    public static void printSelectCardSet() {
        System.out.println("Type 'L' for letters or 'D' for digits:");
    }

    public static void printAlreadyFound() {
        System.out.println("The selected card was already found!");
    }

    public static void printSelectedTwice() {
        System.out.println("You've selected the same card twice!");
    }

    public static void printAllPairsFound() {
        System.out.println("All Pairs are found.");
    }

    public static void printGameSummary(PlayerList highestScore, Game game) {
        highestScore.printList();
        System.out.print(" won the Game with a score of " );
        System.out.println(game.getPlayerList().getHighestScore());
        System.out.println("""
                 Please select by entering a command whether you want to\s
                 return to the main menu ('menu'),\s
                 reset the current game ('reset'),\s
                 restart the current game ('restart') or\s
                 quit the game ('quit')\s
                """);
    }

    public static void printFoundPair() {
        System.out.println("You've found a pair! It is your turn again.");
    }

    public static void printUnequalCards(){
        System.out.println("Cards are not equal!");
    }
}
