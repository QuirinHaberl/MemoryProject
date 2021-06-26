package Control;

import Model.PlayerList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@link Database} controls the storage of playerProfiles.
 */
public class Database {

    /**
     * Stores the path to profiles.csv.
     */
    private final String path = "profiles/profiles.csv";

    /**
     * Stores the FileReader.
     */
    private FileReader fr;

    /**
     * Stores the FileOutputStream.
     */
    private FileOutputStream fos;

    /**
     * Stores the PrintWriter.
     */
    private PrintWriter pw;

    /**
     * Stores the BufferedReader.
     */
    private BufferedReader br;

    /**
     * Stores all playerProfiles
     */
    private List<String[]> playerProfiles;

    /**
     * Constructs a new database-object.
     */
    public Database() {
        try {
            this.fr = new FileReader(path);
            this.br = new BufferedReader(fr);

            this.playerProfiles = new ArrayList<>();
            loadPlayerProfiles();

            this.fos = new FileOutputStream(path);
            this.pw = new PrintWriter(fos);

        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }

    /**
     * Loads all existing playerProfiles out of profiles.csv.
     *
     * @throws IOException if profiles.csv is not found
     */
    public void loadPlayerProfiles() throws IOException {
        String[] values;
        String playerInfo;
        int pos = 0;

        while (!((playerInfo = br.readLine()) == null)) {
            values = playerInfo.split(";");

            playerProfiles.add(pos, values);
            pos++;
        }
        br.close();
    }

    /**
     * Stores all updated playerProfiles in profiles.csv.
     *
     * @param playerList contains all players of the current game
     */
    public void storePlayerProfiles(PlayerList playerList) {
        for (String[] playerProfile : playerProfiles) {
            pw.println(playerProfile[0] + ";" +
                    playerProfile[1] + ";" +
                    playerProfile[2] + ";");
        }

        boolean playerHasNoProfile;

        for (int i = 0; i < playerList.getCount(); i++) {
            playerHasNoProfile = true;
            for (String[] playerProfile : playerProfiles) {
                if (playerList.getPlayer(i).getName().equals(playerProfile[0])) {
                    playerHasNoProfile = false;
                    break;
                }
            }
            if (playerHasNoProfile) {
                //TODO Highscore muss angepasst werden
                pw.println(playerList.getPlayer(i).getName() + ";" +
                        playerList.getPlayer(i).getScore() + ";" +
                        playerList.getPlayer(i).getAchievements().getGameCounter());
            }
        }
        pw.close();
    }

    /**
     * Gets all playerProfiles
     *
     * @return all playerProfiles
     */
    public List<String[]> getPlayerProfiles() {
        return playerProfiles;
    }
}
