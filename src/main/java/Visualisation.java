import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <heading>Visualisation</heading>
 * This class is the visualisation interface between the user (console) and
 * the control interface (Control).
 */
public final class Visualisation {

    /**
     * Utility class implies private constructor
     */
    private Visualisation() {
    }

    /**
     * This is the main method which makes use of the execute method.
     *
     * @param args Unused.
     * @throws IOException On input error.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        execute(bufferedReader);
    }


    //Methods
    /**
     * This method reads in input from the console and reacts to it by
     * delegating to Control.
     *
     * @param bufferedReader Provides a connection to the console.
     * @throws IOException On input error.
     */
    public static void execute(BufferedReader bufferedReader) throws IOException{
        //At the beginning a new control is created.
        Control control = new Control();

        boolean quit = false;

        int firstRow = 0;
        int firstCol = 0;
        int firstCard = 0;

        //It is read in from the console until the program is ended.
        while (!quit) {
            System.out.print("memory> ");
            String input = bufferedReader.readLine();
            if (input == null) {
                break;
            }
            //The input is split up using spaces.
            String[] tokens = input.trim().split("\\s+");

            switch (control.getGameStatus()) {
                case NOTSTART:
                    revealFirstCard(tokens, control);
                    firstRow = Integer.parseInt(tokens[0]);
                    firstCol = Integer.parseInt(tokens[1]);
                    RevealedCard revealedCard = control.revealCard(firstRow,
                            firstCol);
                    firstCard =
                            visualizeCard(revealedCard.getRevealedCard().getValue());
                    break;
                case RUNNING:
                    //Reveals second card
                    revealSecondCard(tokens, control, firstRow, firstCol, firstCard);
                    control.checkForPairOfCards();
                    showPlayingField(tokens, control);
                    break;
                case END:
                    System.out.println("You won!");
                    quit = true;
                    break;
            }
        }
    }

    /**
     *
     * @param tokens
     * @param control
     */
    public static void revealFirstCard(String[] tokens, Control control) {
        correctInput(tokens);
        int numberIntRow = Integer.parseInt(tokens[0]);
        int numberIntCol = Integer.parseInt(tokens[1]);
        RevealedCard revealedCard = control.revealCard(numberIntRow, numberIntCol);
        if (!revealedCard.isCanBeRevealed()) {
            error("Card is already revealed");
        } else {
            boolean[][] playingField = revealedCard.getOutput();
            String line = ("  0 1 2 3");
            int card =
                    visualizeCard(revealedCard.getRevealedCard().getValue());
            for (int i = 0; i < playingField.length; i++) {
                line = line + "\r" + i + " ";
                for (int j = 0; j < playingField[i].length; j++) {
                    if (numberIntRow == i && numberIntCol == j) {
                        line = line + card + " ";
                    } else {
                        if (!playingField[i][j]) {
                            line = line + "X ";
                        } else {
                            line = line + "  ";
                        }
                    }
                }
            }
            System.out.println(line);
        }
    }

    /**
     *
     * @param tokens
     * @param control
     * @param firstRow
     * @param firstCol
     * @param firstCard
     */
    public static void revealSecondCard(String[] tokens, Control control,
                                        int firstRow, int firstCol,
                                        int firstCard) {
        correctInput(tokens);
        int numberIntRow = Integer.parseInt(tokens[0]);
        int numberIntCol = Integer.parseInt(tokens[1]);
        RevealedCard revealedCard = control.revealCard(numberIntRow, numberIntCol);
        if (!revealedCard.isCanBeRevealed()) {
            error("Card is already revealed");
        } else {
            boolean[][] playingField = revealedCard.getOutput();
            String line = ("  0 1 2 3");
            int card =
                    visualizeCard(revealedCard.getRevealedCard().getValue());
            for (int i = 0; i < playingField.length; i++) {
                line = line + "\r" + i + " ";
                for (int j = 0; j < playingField[i].length; j++) {
                    if (firstRow == i && firstCol == j) {
                        line = line + firstCard + " ";
                    }
                    if (numberIntRow == i && numberIntCol == j) {
                        line = line + card + " ";
                    } else {
                        if (!playingField[i][j]) {
                            line = line + "X ";
                        } else {
                            line = line + "  ";
                        }
                    }
                }
            }
            System.out.println(line);
        }
    }

    /**
     *
     * @param tokens
     * @param control
     */
    public static void showPlayingField(String[] tokens, Control control) {
        boolean[][] playingField = control.getControlArray();
        String line = ("  0 1 2 3");
        for (int i = 0; i < playingField.length; i++) {
            line = line + "\r" + i + " ";
            for (int j = 0; j < playingField[i].length; j++) {
                if (!playingField[i][j]) {
                    line = line + "X ";
                } else {
                    line = line + "  ";
                }
            }
        }
        System.out.println(line);
    }

    /**
     *
     * @param cardValue
     * @return
     */
    public static int visualizeCard(CardValue cardValue) {
        int visualized;
        switch (cardValue) {
            case ONE:
                visualized = 1;
                break;
            case TWO:
                visualized = 2;
                break;
            case THREE:
                visualized = 3;
                break;
            case FOUR:
                visualized = 4;
                break;
            case FIVE:
                visualized = 5;
                break;
            case SIX:
                visualized = 6;
                break;
            case SEVEN:
                visualized = 7;
                break;
            case EIGHT:
                visualized = 8;
                break;
            case NINE:
                visualized = 9;
                break;
            case TEN:
                visualized = 10;
                break;
            default:
                visualized = 0;
        }
        return visualized;
    }


    //Helper Methods
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
    private static boolean correctInput(String[] tokens){
        //Checks whether the input contained two parameters
        if (tokens.length != 2) {
            error("Have not received correct number of parameters");
            return false;
        } else {
            //Checks whether the input contains two numbers between 0 and 3
            if (tokens[0] == "0" || tokens[0] == "1"|| tokens[0] == "2" || tokens[0] == "3") {
                if (tokens[1] == "0" || tokens[1] == "1"|| tokens[1] == "2" || tokens[1] == "3") {
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
}
