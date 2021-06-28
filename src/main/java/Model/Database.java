package Model;

import View.View;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class {@link Database} controls the storage of playerProfiles.
 */
public class Database {

    String path;

    /**
     * Stores all playerProfiles
     * A profile has the following structure:
     * playerID;player1;highScore;gamePlayed;gamesWon
     */
    private List<String[]> playerProfiles;

    /**
     * Constructs a new database-object.
     */
    public Database() {
        this.path = "src/main/resources/profiles.csv";
        this.playerProfiles = new ArrayList<>();
    }

    /**
     * Loads all existing playerProfiles out of profiles.csv.
     */
    public void loadPlayerProfiles() {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String[] values;
            String playerInfo;
            int pos = 0;

            while (!((playerInfo = br.readLine()) == null)) {
                values = playerInfo.split(";");

                playerProfiles.add(pos, values);
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
     * Stores all updated playerProfiles in profiles.csv.
     * A profile has the following structure:
     * playerID;player1;highScore;gamePlayed;gamesWon
     */
    public void storePlayerProfiles() {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            PrintWriter pw = new PrintWriter(fos);
            for (String[] playerProfile : playerProfiles) {
                pw.println(playerProfile[0] + ";" +
                        playerProfile[1] + ";" +
                        playerProfile[2] + ";" +
                        playerProfile[3] + ";" +
                        playerProfile[4] + ";");
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
    public List<String[]> getPlayerProfiles() {
        return playerProfiles;
    }

    public static void main(String[] args) {
        Database database = new Database();
        for (int i = 0; i < database.getPlayerProfiles().size(); i++) {
            //System.out.println(Arrays.toString(database.getPlayerProfiles().get(i)));
        }
        PlayerList playerList = new PlayerList();
        database.loadPlayerProfiles();
        database.storePlayerProfiles();
    }
}
