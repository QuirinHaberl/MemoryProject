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

        //Attributes to temporary save the first revealed card
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

            //Implementation of game phases
            switch (control.getGameStatus()) {
                case NOTSTART:
                    //Reveals first card
                    revealFirstCard(tokens, control);
                    //Saving first revealed card and its position
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
                    //Stops game because it is over
                    System.out.println("You won!");
                    quit = true;
                    break;
            }
        }
    }

    /**
     * First card is revealed and the the current playing field is displayed,
     * marking left cards with X and already open cards with spaces.
     *
     * @param tokens Passed input (coordinates of the card to be revealed)
     * @param control Passed control
     */
    private static void revealFirstCard(String[] tokens, Control control) {
        if (correctInput(tokens)) {
            int numberIntRow = Integer.parseInt(tokens[0]);
            int numberIntCol = Integer.parseInt(tokens[1]);
            RevealedCard revealedCard = control.revealCard(numberIntRow, numberIntCol);
            if (!revealedCard.isCanBeRevealed()) {
                error("Card is already revealed");
            } else {
                boolean[][] playingField = revealedCard.getOutput();
                //Column designation
                String line = ("  0 1 2 3");
                int card =
                        visualizeCard(revealedCard.getRevealedCard().getValue());
                for (int i = 0; i < playingField.length; i++) {
                    //Row designation
                    line = line + "\r" + i + " ";
                    //Output of the current playing field
                    for (int j = 0; j < playingField[i].length; j++) {
                        //Display the first card and display the current playing
                        // field
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
    }

    /**
     * Second card is revealed and the the current playing field is displayed,
     * marking left cards with X and already open cards with spaces.
     *
     * @param tokens Passed input (coordinates of the card to be revealed)
     * @param control Passed control
     * @param firstRow Row of the current first revealed card
     * @param firstCol Column of the current first revealed card
     * @param firstCard Reference to the first revealed card
     */
    private static void revealSecondCard(String[] tokens, Control control,
                                        int firstRow, int firstCol,
                                        int firstCard) {
        if (correctInput(tokens)) {
            int numberIntRow = Integer.parseInt(tokens[0]);
            int numberIntCol = Integer.parseInt(tokens[1]);
            RevealedCard revealedCard = control.revealCard(numberIntRow, numberIntCol);
            if (!revealedCard.isCanBeRevealed()) {
                error("Card is already revealed");
            } else {
                boolean[][] playingField = revealedCard.getOutput();
                //Column designation
                String line = ("  0 1 2 3");
                int card =
                        visualizeCard(revealedCard.getRevealedCard().getValue());
                for (int i = 0; i < playingField.length; i++) {
                    //Row designation
                    line = line + "\r" + i + " ";
                    //Output of the current playing field
                    for (int j = 0; j < playingField[i].length; j++) {
                        //Display the first card
                        if (firstRow == i && firstCol == j) {
                            line = line + firstCard + " ";
                        }
                        //Display the second card and display the current playing
                        // field
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
    }

    /**
     * Visualizes the current playing field, marking left cards with X and
     * already open cards with spaces.
     *
     * @param tokens Passed input (coordinates of the card to be revealed)
     * @param control Passed control
     */
    private static void showPlayingField(String[] tokens, Control control) {
        boolean[][] playingField = control.getControlArray();
        //Column designation
        String line = ("  0 1 2 3");
        for (int i = 0; i < playingField.length; i++) {
            //Row designation
            line = line + "\r" + i + " ";
            //Output of the current playing field
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
     * Visualizes card, defined by cardValue
     *
     * @param cardValue Type of card
     * @return Visualization of the card value transferred
     */
        private static int visualizeCard(CardValue cardValue) {
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
            if (tokens[0].equals("0") || tokens[0].equals("1") || tokens[0].equals("2") || tokens[0].equals("3")) {
                if (tokens[1].equals("0") || tokens[1].equals("1")|| tokens[1].equals("2") || tokens[1].equals("3")) {
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
