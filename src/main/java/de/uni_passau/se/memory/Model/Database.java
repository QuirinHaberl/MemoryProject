package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.gui.View;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@link Database} controls the storage of playerProfiles.
 */
public class Database {

    /**
     * Stores the path to highScoreHistory.csv.
     */
    private static final String pathToProfiles =
            "src/main/resources/de/uni_passau/se/memory/Database/profiles.csv";

    /**
     * Stores the path to highScoreHistory.csv.
     */
    private static final String pathToHighScores =
            "src/main/resources/de/uni_passau/se/memory/Database/highScoreHistory.csv";

    /**
     * Stores the {@code HighScoreHistory} in a list. At first
     * position it contains the player name and at second place his high score.
     */
    private final List<String[]> highScoreList;

    /**
     * Stores all playerProfiles
     * A profile has the following structure:
     * playerId;player1;highScore;gamePlayed;gamesWon
     */
    private final List<String[]> playerProfiles;

    /**
     * Creates a new {@code INSTANCE} of the {@link Database}.
     */
    private static class InstanceHolder {
        private static final Database INSTANCE = new Database();
    }

    /**
     * Constructs a new database-object.
     */
    public Database() {
        this.playerProfiles = new ArrayList<>();
        this.highScoreList = new ArrayList<>();
    }

    /**
     * Returns a new {@code INSTANCE} of the {@link Database}.
     *
     * @return the INSTANCE of a {@link Database}.
     */
    public static Database getInstance() {
        return Database.InstanceHolder.INSTANCE;
    }

    public void loadPlayerProfiles() {
        loadFromFile(pathToProfiles, playerProfiles);
    }

    public void loadHighScoreHistory() {
        loadFromFile(pathToHighScores, highScoreList);
    }

    /**
     * Loads the {@code HighScoreHistory} from the highScoreHistory.csv file
     * and stores it in the {@code highScoreList}.
     */
    public void loadFromFile(String path, List<String[]> list) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String[] values;
            String infos;
            int pos = 0;

            while (!((infos = br.readLine()) == null)) {
                values = infos.split(";");

                list.add(pos, values);
                pos++;
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            View.error("File not found!");
        } catch (IOException e) {
            View.error("Couldn't read from " + path);
        }
    }

    /**
     * Stores all updated playerProfiles in profiles.csv.
     * A profile has the following structure:
     * playerId;player1;highScore;gamePlayed;gamesWon
     */
    public void storePlayerProfiles() {
        try {
            FileOutputStream fos = new FileOutputStream(pathToProfiles);
            PrintWriter pw = new PrintWriter(fos);
            for (String[] playerProfile : playerProfiles) {
                pw.println(playerProfile[0] + ";" +
                        playerProfile[1] + ";" +
                        playerProfile[2] + ";" +
                        playerProfile[3] + ";");
            }
            pw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            View.error("File not found!");
        } catch (IOException e) {
            View.error("Couldn't write to " + pathToProfiles);
        }
    }

    /**
     * Saves the {@code HighScoreHistory} from the {@code highScoreList} in the
     * highScoreHistory.csv file.
     */
    public void storeHighScoreHistory() {
        try {
            FileOutputStream fos = new FileOutputStream(pathToHighScores);
            PrintWriter pw = new PrintWriter(fos);
            for (String[] strings : highScoreList) {
                if (strings[0].equals("Not yet occupied!")) {
                    pw.println(strings[0] + ";" + "0");
                } else {
                    pw.println(strings[0] + ";" + strings[1]);
                }
            }
            pw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            View.error("File not found!");
        } catch (IOException e) {
            View.error("Couldn't read from " + pathToHighScores);
        }
    }

    /**
     * Gets all playerProfiles
     *
     * @return all playerProfiles
     */
    public List<String[]> getPlayerProfiles() {
        return this.playerProfiles;
    }

    /**
     * Gets all playerProfiles
     *
     * @return all playerProfiles
     */
    public List<String[]> getHighScoreHistory() {
        return this.highScoreList;
    }

    /**
     * Updates the {@code HighScoreHistory} with the current winning players
     * and their high score.
     *
     * @param winningPlayers of the current game.
     * @param highestScore   of the winning players.
     */
    public void updateHighScoreHistory(List<String> winningPlayers,
                                       int highestScore) {
        for (int i = winningPlayers.size() - 1; i >= 0; i--) {
            if (!updateHighScore(winningPlayers.get(i), highestScore)) {
                addNewHighScore(winningPlayers.get(i), highestScore);
            }
        }
    }

    /**
     * Updates the high score of the handed over player, if he is already in
     * the {@code HighScoreHistory}, and updates his position in the
     * {@code HighScoreHistory}.
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