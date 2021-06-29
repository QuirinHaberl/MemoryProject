package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.View.View;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@link HighScoreHistory} controls the storage of the high score
 * history.
 */
public class HighScoreHistory {

    /**
     * Stores the path to highScoreHistory.csv.
     */
    String path;

    /**
     * Stores the {@link HighScoreHistory} in a list. At first
     * position it contains the player name and at second place his high score.
     */
    private final List<String[]> highScoreList;

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
        this.path = "src/main/resources/highScoreHistory.csv";
        this.highScoreList = new ArrayList<>();
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
     */
    public void loadHighScoreHistory() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String[] values;
            String highScoreInfo;
            int pos = 0;

            while (!((highScoreInfo = br.readLine()) == null)) {
                values = highScoreInfo.split(";");

                highScoreList.add(pos, values);
                pos++;
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            View.error("File not found!");
        } catch (IOException e) {
            View.error("Couldn't write to profiles.csv");
        }
    }

    /**
     * Saves the {@link HighScoreHistory} from the {@code highScoreList} in the
     * highScoreHistory.csv file.
     */
    public void saveHighScoreHistory() {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < highScoreList.size(); i++) {
                if (highScoreList.get(i)[0].equals("Not yet occupied!")) {
                    pw.println(highScoreList.get(i)[0]);
                } else {
                    pw.println(highScoreList.get(i)[0] + ";" + highScoreList.get(i)[1]);
                }
            }
            pw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            View.error("File not found!");
        } catch (IOException e) {
            View.error("Couldn't write to profiles.csv");
        }
    }

    /**
     * Gets all playerProfiles
     *
     * @return all playerProfiles
     */
    public List<String[]> getHighScoreHistory() {
        return highScoreList;
    }

    /**
     * Updates the {@link HighScoreHistory} with the current winning players
     * and their high score.
     *
     * @param winningPlayers of the current game.
     * @param highestScore      of the winning players.
     */
    public void updateHighScoreHistory(String[] winningPlayers,
                                       int highestScore) {
        for (int i = winningPlayers.length - 1; i >= 0; i--) {
            if (!updateHighScore(winningPlayers[i], highestScore)) {
                addNewHighScore(winningPlayers[i], highestScore);
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
        for (int i = 0; i < highScoreList.size(); i++) {
            if (player.equals(highScoreList.get(i)[0])) {
                if (Integer.parseInt(highScoreList.get(i)[1]) < highScore) {
                    //Deletes old data and inserts it at the new correct
                    // position
                    highScoreList.remove(i);
                    addNewHighScore(player, highScore);
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
        for (int i = 0; i < highScoreList.size(); i++) {
            if (highScoreList.get(i)[0].equals("Not yet occupied!")) {
                highScoreList.remove(i);
                String[] newPlayer = {player, String.valueOf(highScore)};
                highScoreList.add(i, newPlayer);
                break;
            } else {
                if (Integer.parseInt(highScoreList.get(i)[1]) <= highScore) {
                    String[] newPlayer = {player, String.valueOf(highScore)};
                    highScoreList.add(i, newPlayer);
                    if (highScoreList.size() > 10) {
                        highScoreList.remove(10);
                    }
                    break;
                }
            }
        }
    }
}