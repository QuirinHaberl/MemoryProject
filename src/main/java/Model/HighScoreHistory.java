package Model;

import java.io.*;

/**
 * The class {@link HighScoreHistory} controls the storage of the high score
 * history.
 */
public class HighScoreHistory {

    /**
     * Stores the FileOutputStream.
     */
    private FileOutputStream fileOutputStream;

    /**
     * Stores the PrintWriter.
     */
    private PrintWriter printWriter;

    /**
     * Stores the BufferedReader.
     */
    private BufferedReader bufferedReader;

    /**
     * Stores the path to highScoreHistory.txt.
     */
    private final String path = "src/main/resources/highScoreHistory.txt";

    /**
     * Stores the {@link HighScoreHistory} in a list.
     */
    private String[][] highScoreList = new String[10][2];

    /**
     * Constructs a new highScoreHistory-object.
     */
    public HighScoreHistory() {
        try {
            FileReader fileReader = new FileReader(path);
            this.bufferedReader = new BufferedReader(fileReader);
            this.fileOutputStream = new FileOutputStream(path, true);
            this.printWriter = new PrintWriter(fileOutputStream);
            loadHighScoreHistory();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }

    /**
     * Loads the {@link HighScoreHistory} from the highScoreHistory.txt file
     * and stores it in the {@code highScoreList}.
     *
     * @throws IOException if highScoreHistory.txt is not found
     */
    private void loadHighScoreHistory() throws IOException {
        String str;
        int i = 0;
        while (!((str = bufferedReader.readLine()) == null)) {
            String[] tokens = str.trim().split("\\s+");
            highScoreList[i][0] = tokens[0];
            highScoreList[i][1] = tokens[1];
            i++;
        }
        bufferedReader.close();
    }

    /**
     * Saves the {@link HighScoreHistory} from the {@code highScoreList} in the
     * highScoreHistory.txt file.
     *
     * @throws FileNotFoundException if highScoreHistory.txt is not found
     */
    public void saveHighScoreHistory() throws FileNotFoundException {
        fileOutputStream = new FileOutputStream(path,false);
        printWriter = new PrintWriter(fileOutputStream);
        printWriter.write("");
        fileOutputStream = new FileOutputStream(path,true);
        printWriter = new PrintWriter(fileOutputStream);
        for (String[] strings : highScoreList) {
            printWriter.println(strings[0] + " " + strings[1]);
        }
        printWriter.close();
    }

    /**
     * Updates the {@link HighScoreHistory} with the current winning players
     * and their high score.
     *
     * @param winningPlayers    of the current game.
     * @param highScore         of the winning players.
     */
    public void updateHighScoreHistory(String[] winningPlayers,
                                       int highScore) {
        for (int i = winningPlayers.length - 1; i >= 0; i--) {
            if (!updateHighScore(winningPlayers[i], highScore)) {
                addNewHighScore(winningPlayers[i], highScore);
            }
        }
    }


    /**
     * Updates the high score of the handed over player, if he is already in
     * the {@link HighScoreHistory}, and updates his position in the
     * {@link HighScoreHistory}.
     *
     * @param player    that won the current game.
     * @param highScore of the winning player.
     * @return  true if the players data was updated.
     */
    private boolean updateHighScore(String player, int highScore) {
        for (int i = 0; i < highScoreList.length; i++) {
            if (highScoreList[i][0].equals(player)) {
                if (Integer.parseInt(highScoreList[i][1]) < highScore) {
                    //Deletes old data and inserts it at the new correct
                    // position
                    for (int j = i; j < highScoreList.length; j++) {
                        highScoreList[j][0] = highScoreList[j + 1][0];
                        highScoreList[j][1] = highScoreList[j + 1][1];
                    }
                    createNewList(i, player, String.valueOf(highScore));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a player with his high score to the {@code highScoreList}.
     *
     * @param player    that won the current game.
     * @param highScore of the winning player.
     */
    private void addNewHighScore(String player, int highScore) {
        for (int j = 0; j < highScoreList.length; j++) {
            if (highScoreList[j][0].equals("Not yet occupied!")) {
                createNewList(j, player, String.valueOf(highScore));
            }
            if (Integer.parseInt(highScoreList[j][1]) <= highScore) {
                createNewList(j, player, String.valueOf(highScore));
            }
        }
    }

    /**
     * Creates a new {@code highScoreList} with the new winning player and his
     * high score. (If {@code highScoreList} is already full, the last player
     * and his score are removed from the {@code highScoreList}.
     *
     * @param pos           of the winning player in the {@code highScoreList}.
     * @param winningPlayer of the current game.
     * @param highScore     of the winning player.
     */
    private void createNewList(int pos, String winningPlayer,
                                     String highScore) {
        String[][] str = new String[highScoreList.length][2];
        for (int k = 0; k < pos; k++) {
            str[k][0] = highScoreList[k][0];
            str[k][1] = highScoreList[k][1];
        }
        for (int l = pos; l < highScoreList.length - 1; l++) {
            str[l + 1][0] = highScoreList[l][0];
            str[l + 1][1] = highScoreList[l][1];
        }
        str[pos][0] = winningPlayer;
        str[pos][1] = highScore;
        highScoreList = str;
    }
}
