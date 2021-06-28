package de.uni_passau.se.memory.Model;

import java.io.*;
import java.util.List;

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
     * Stores the path to highScoreHistory.csv.
     */
    private final String path = "src/main/resources/highScoreHistory.csv";

    /**
     * Stores the {@link HighScoreHistory} in a list. At first
     * position it contains the player name and at second place his high score.
     */
    private List<String[]> highScoreList;

    /**
     * Creates a new {@code INSTANCE} of the {@link HighScoreHistory}.
     */
    private static class InstanceHolder {
        private static final HighScoreHistory INSTANCE = new HighScoreHistory();
    }

    /**
     * Constructs a new highScoreHistory-object.
     */
    private HighScoreHistory() {
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
     * Returns a new {@code INSTANCE} of the {@link HighScoreHistory}.
     *
     * @return the INSTANCE of a {@link HighScoreHistory}.
     */
    public static HighScoreHistory getInstance() {
        return HighScoreHistory.InstanceHolder.INSTANCE;
    }

    /**
     * Loads the {@link HighScoreHistory} from the highScoreHistory.csv file
     * and stores it in the {@code highScoreList}.
     *
     * @throws IOException if highScoreHistory.csv is not found
     */
    private void loadHighScoreHistory() throws IOException {
        String str;
        int i = 0;
        while (!((str = bufferedReader.readLine()) == null)) {
            String[] tokens = str.split(";");
            highScoreList.add(i, tokens);
            i++;
        }
        bufferedReader.close();
    }

    /**
     * Saves the {@link HighScoreHistory} from the {@code highScoreList} in the
     * highScoreHistory.csv file.
     *
     * @throws FileNotFoundException if highScoreHistory.csv is not found
     */
    public void saveHighScoreHistory() throws FileNotFoundException {
        fileOutputStream = new FileOutputStream(path, false);
        printWriter = new PrintWriter(fileOutputStream);
        printWriter.write("");
        fileOutputStream = new FileOutputStream(path, true);
        printWriter = new PrintWriter(fileOutputStream);
        for (String[] strings : highScoreList) {
            printWriter.println(strings[0] + ";" + strings[1]);
        }
        printWriter.close();
    }

    /**
     * Updates the {@link HighScoreHistory} with the current winning players
     * and their high score.
     *
     * @param winningPlayers of the current game.
     * @param highScore      of the winning players.
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
     * @return true if the players data was updated.
     */
    private boolean updateHighScore(String player, int highScore) {
        int pos = 0;
        for (String[] values : highScoreList) {
            if (values[0].equals(player)) {
                if (Integer.parseInt(values[1]) < highScore) {
                    //Deletes old data and inserts it at the new correct
                    // position
                    highScoreList.remove(pos);
                    addNewHighScore(player, highScore);
                }
                return true;
            }
            pos++;
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
        int pos = 0;
        for (String[] values : highScoreList) {
            if (values[0].equals("Not yet occupied!")) {
                createNewList(pos, player, String.valueOf(highScore));
            }
            if (Integer.parseInt(values[1]) <= highScore) {
                createNewList(pos, player, String.valueOf(highScore));
            }
            pos++;
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
        String[] newPlayer = {winningPlayer, String.valueOf(highScore)};
        highScoreList.add(pos, newPlayer);
        if (highScoreList.get(10) != null) {
            highScoreList.remove(10);
        }
    }
}
