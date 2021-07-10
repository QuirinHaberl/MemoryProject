package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.gui.View;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
     * playerName;highScore;gamePlayed;gamesWon
     */
    private List<String[]> playerProfiles;

    private List<Boolean> usesProfiles;

    /**
     * Constructs a new database.
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

    /**
     * Stores the progress of all playerProfiles and the HighScoreHistory.
     *
     * @param playerList contains the progress to be stored.
     */
    public void storeProgress(PlayerList playerList) {
        saveProfile(playerList);
        storePlayerProfiles();
        storeHighScoreHistory();
    }

    /**
     * Loads all existing profiles for the current players.
     * A playerProfile has the following structure:
     * playerName;highScore;gamesPlayed;gamesWon
     *
     * @param playerList to be used
     */
    public void useProfile(PlayerList playerList) {
        boolean hasProfile;
        Player player;

        for (int i = 0; i < playerList.size(); i++) {
            player = playerList.getPlayer(i);

            if (usesProfiles.get(i)) {
                hasProfile = false;
                int j = 0;

                while (j < playerProfiles.size()) {
                    if (playerList.getPlayerName(player).equals(playerProfiles.get(j)[0])) {
                        hasProfile = true;
                        break;
                    }
                    j++;
                }

                if (hasProfile) {
                    loadPlayerProfile(player, playerProfiles.get(j));
                } else {
                    createPlayerProfile(player);
                }
            }
        }
    }

    /**
     * Sets which player wants to use a profile in usesProfiles.
     *
     * @param checkBoxes contains if a player wants to use a profile
     * @param textFields contains if a player uses default profiles
     */
    public void setUsesProfiles(CheckBox[] checkBoxes, TextField[] textFields) {
        usesProfiles = new ArrayList<>();
        for (int i = 0; i < checkBoxes.length; i++) {
            if (textFields[i].getText().isEmpty()) {
                usesProfiles.add(false);
            } else {
                usesProfiles.add(checkBoxes[i].isSelected());
            }
        }
    }

    /**
     * Updates the dataBase for a new game.
     *
     * @param playerAmount number of players
     * @param playerList   of all existing players
     */
    public void updateDataBase(int playerAmount, PlayerList playerList) {
        resetPlayerProfiles();
        loadPlayerProfiles();
        setUsesProfileOnEveryPlayer(playerAmount);
        useProfile(playerList);
    }

    /**
     * Sets profiles for every player.
     *
     * @param playerAmount is the amount for players to use profiles
     */
    public void setUsesProfileOnEveryPlayer(int playerAmount) {
        usesProfiles = new ArrayList<>();
        for (int i = 0; i < playerAmount; i++) {
            usesProfiles.add(true);
        }
    }

    /**
     * Loads the profile from playerProfile to a player.
     *
     * @param player        to be updated
     * @param playerProfile to be used
     */
    public void loadPlayerProfile(Player player, String[] playerProfile) {
        player.setHighScore(Integer.parseInt(playerProfile[1]));
        player.setGamesPlayed(Integer.parseInt(playerProfile[2]));
        player.setGamesWon(Integer.parseInt(playerProfile[3]));
    }

    /**
     * Creates a new profile for a player.
     *
     * @param player for which a profile is generated
     */
    public void createPlayerProfile(Player player) {
        String[] newProfile = new String[]{
                player.getName(),
                player.getHighScore() + "",
                player.getGamesPlayed() + "",
                player.getGamesWon() + ""
        };
        playerProfiles.add(newProfile);
    }

    /**
     * Loads all playerProfiles from pathToPlayerProfiles.
     */
    public void loadPlayerProfiles() {
        loadFromFile(pathToProfiles, playerProfiles);
    }

    /**
     * Loads the highScoreHistory from pathToHighScoreHistory.
     */
    public void loadHighScoreHistory() {
        loadFromFile(pathToHighScores, highScoreList);
    }

    /**
     * Loads the {@code HighScoreHistory} from the highScoreHistory.csv file
     * and stores it in the {@code highScoreList}.
     *
     * @param path to the file to be loaded
     * @param list specifies playerProfiles or highScoreHistory
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
     * Loads all existing profiles for the current players.
     * A playerProfile has the following structure:
     * playerName;highScore;gamesPlayed;gamesWon
     *
     * @param playerList to be used
     */
    public void saveProfile(PlayerList playerList) {
        Player player;
        String name;
        for (String[] playerProfile : playerProfiles) {
            for (int j = 0; j < playerList.size(); j++) {
                player = playerList.getPlayer(j);
                name = getPlayerName(player);

                if (playerProfile[0].equals(playerList.getPlayerName(player))) {
                    switch (name) {
                        case "Player1", "Player2", "Player3", "Player4":
                            break;
                        default:
                            playerProfile[0] = playerList.getPlayerName(player) + "";
                            playerProfile[1] = playerList.getPlayerHighScore(player) + "";
                            playerProfile[2] = playerList.getPlayerGamesPlayed(player) + "";
                            playerProfile[3] = playerList.getPlayerGamesWon(player) + "";
                            break;
                    }
                }
            }
        }
    }

    /**
     * Gets the name of a given player.
     *
     * @param player whose name is requested
     * @return the name of a player
     */
    public String getPlayerName(Player player) {
        return player.getName();
    }

    /**
     * Stores all updated playerProfiles in profiles.csv.
     * A profile has the following structure:
     * playerName;highScore;gamePlayed;gamesWon
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
     * Resets a playerProfile to a empty ArrayList.
     */
    public void resetPlayerProfiles() {
        playerProfiles = new ArrayList<>();
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

    /**
     * Creates a new {@code INSTANCE} of the {@link Database}.
     */
    private static class InstanceHolder {
        private static final Database INSTANCE = new Database();
    }
}
