package de.uni_passau.se.memory.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a {@link PlayerList}.
 */
public class PlayerList {
    /**
     * Stores the first element of the {@link PlayerList}.
     */
    private Player front;

    /**
     * Stores the last element of the {@link PlayerList}.
     */
    private Player rear;

    /**
     * Stores the amount of players.
     */
    private int size;

    /**
     * Constructs a new {@link PlayerList}.
     */
    public PlayerList() {
        this.front = null;
        this.rear = null;
        size = 0;
    }

    /**
     * Gets the amount of players.
     *
     * @return count
     */
    public int size() {
        return size;
    }


    /**
     * Adds a {@link Player} to the {@link PlayerList}.
     *
     * @param name of a player
     */
    public void addPlayer(String name) {
        Player player = new Player(name, 0, null, null);
        if (front == null) {
            front = player;
            rear = player;
            player.next = player;
            player.rear = player;
        } else {
            Player p = front;
            while (p.next != front) {
                p = p.next;
            }
            p.next = player;
            player.next = front;
            front.rear = player;
            player.rear = p;
            rear = player;
        }
        size++;
    }

    /**
     * Deletes all {@link Player}'s from the {@link PlayerList}.
     */
    public void deleteAllPlayers() {
        this.front = null;
        this.rear = null;
        size = 0;
    }

    /**
     * Gets the {@link Player} at {@code position}.
     *
     * @param position of the {@link Player}
     * @return the Player
     */
    public Player getPlayer(int position) {
        if (front == null) {
            throw new IllegalArgumentException("There is no Player now!");
        }
        Player current = front;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * Gets the front.
     *
     * @return the front
     */
    public Player getFront() {
        return front;
    }

    /**
     * Gets the last element.
     *
     * @return the rear
     */
    public Player getRear() {
        return rear;
    }

    /**
     * Resets all {@code score}'s of {@link Player}'s.
     */
    public void resetAllScores() {
        for (int i = 0; i < size; i++) {
            getPlayer(i).setScore(0);
        }
    }

    /**
     * Gets the current highest {@code score} of all {@link Player}'s.
     *
     * @return highest {@code score} and how many players reached this score
     */
    public int getHighestScore() {
        int highestScore = 0;

        for (int i = 0; i < size; i++) {
            if (getPlayerScore(i) > highestScore) {
                highestScore = getPlayerScore(i);
            }
        }
        return highestScore;
    }

    /**
     * Gets the number of players with the highest score.
     *
     * @return how many player have the highest score
     */
    public int getCountOfWinningPlayers() {
        int playersWithHighestScore = 0;
        int highestScore = getHighestScore();

        for (int i = 1; i < size; i++) {
            if (getPlayerScore(i) == highestScore) {
                playersWithHighestScore = playersWithHighestScore + 1;
            }
        }
        return playersWithHighestScore;
    }

    /**
     * Gets all {@link Player}'s with the highest {@code score}.
     *
     * @return list of all {@link Player} who have the highest {@code score}
     */
    public List<String> winningPlayersToString() {
        int highestScore = getHighestScore();
        List<String> winningPlayers = new ArrayList<>();


        for (int i = 0; i < size; i++) {
            if (getPlayerScore(i) == highestScore) {
                winningPlayers.add(getPlayerName(i));
            }
        }

        return winningPlayers;
    }

    /**
     * Gets the name of a {@link Player}.
     *
     * @param position of a Player
     * @return the name of a {@link Player}
     */
    public String getPlayerName(int position) {
        return getPlayer(position).getName();
    }

    /**
     * Gets the {@code score} of a selected {@link Player}.
     *
     * @param position of a {@link Player}
     * @return the {@code score} of a {@link Player}
     */
    public int getPlayerScore(int position) {
        return getPlayer(position).getScore();
    }

    /**
     * Sorts the playerList.
     *
     * @return a sorted playerList
     */
    public String[] getSorted() {
        String[] output = new String[size * 2];
        String[] names = new String[size];
        int[] scores = new int[size];
        for (int i = 0; i < size; i++) {
            names[i] = this.getPlayerName(i);
            scores[i] = this.getPlayerScore(i);
        }
        for (int i = 1; i < size; i++) {
            String currentName = names[i];
            int currentScore = scores[i];

            helper(names, scores, i, currentName, currentScore);
        }
        for (int i = 0; i < size; i++) {
            output[i] = names[size - 1 - i];
            output[size + i] = String.valueOf(scores[size - 1 - i]);
        }
        return output;
    }

    /**
     * Helper for sort().
     *
     * @param names        of the players
     * @param scores       of the players
     * @param i            incrementer passed by sort()
     * @param currentName  of current player
     * @param currentScore of current player
     */
    public static void helper(String[] names, int[] scores, int i,
                              String currentName, int currentScore) {
        int j = i;
        //TODO Ich habe am Code nichts geändert, nur diese Passage automatisch extrahieren lassen.
        // Element is moved to the left until it is at the right position.
        while (j > 0 && currentScore < scores[j - 1]) {
            scores[j] = scores[j - 1];
            names[j] = names[j - 1];
            j--;
        }
        scores[j] = currentScore;
        names[j] = currentName;
    }

    /**
     * Loads all existing profiles for the current players.
     * A playerProfile has the following structure:
     * playerId;playerName;highScore;gamesPlayed;gamesWon
     *
     * @param playerProfiles to be used
     */
    public void saveProfile(List<String[]> playerProfiles) {
        Player player;
        for (String[] playerProfile : playerProfiles) {
            for (int j = 0; j < size(); j++) {
                player = getPlayer(j);
                if (playerProfile[0].equals(getPlayerName(player)) && !(
                        getPlayerName(player).equals("Player1")
                                || getPlayerName(player).equals("Player2")
                                || getPlayerName(player).equals("Player3")
                                || getPlayerName(player).equals("Player4")
                )) {
                    playerProfile[0] = getPlayerName(player) + "";
                    playerProfile[1] = getPlayerHighScore(player) + "";
                    playerProfile[2] = getPlayerGamesPlayed(player) + "";
                    playerProfile[3] = getPlayerGamesWon(player) + "";
                    break;
                }
            }
        }
    }

    /**
     * Loads all existing profiles for the current players.
     * A playerProfile has the following structure:
     * playerId;playerName;highScore;gamesPlayed;gamesWon
     *
     * @param playerProfiles to be used
     * @param usesProfiles   specifies which player wants to use a profile
     */
    public void useProfile(List<String[]> playerProfiles, List<Boolean> usesProfiles) {
        boolean hasProfile;
        Player player;
        for (int i = 0; i < size(); i++) {
            player = getPlayer(i);

            if (usesProfiles.get(i)) {
                hasProfile = false;
                int j;
                for (j = 0; j < playerProfiles.size(); j++) {
                    if (getPlayerName(player).
                            equals(playerProfiles.get(j)[0])) {
                        hasProfile = true;
                        break;
                    }

                }
                if (hasProfile) {
                    setPlayerHighScore(player, Integer.parseInt(playerProfiles.get(j)[1]));
                    setPlayerGamesPlayed(player, Integer.parseInt(playerProfiles.get(j)[2]));
                    setPlayerGamesWon(player, Integer.parseInt(playerProfiles.get(j)[3]));
                } else {
                    String[] newProfile = new String[]{
                            getPlayerName(player),
                            getPlayerHighScore(player) + "",
                            getPlayerGamesPlayed(player) + "",
                            getPlayerGamesWon(player) + ""
                    };
                    playerProfiles.add(newProfile);
                }
            }
        }
    }

    /**
     * Gets the amount of gamesPlayed.
     * Ensures Demeter's Law.
     *
     * @param player whose gamesPlayed are requested
     * @return amount of gamesPlayed
     */
    public int getPlayerGamesPlayed(Player player) {
        return player.getGamesPlayed();
    }

    /**
     * Gets the amount of gamesWon.
     * Ensures Demeter's Law.
     *
     * @param player whose gamesWon are requested
     * @return amount of gamesWon
     */
    public int getPlayerGamesWon(Player player) {
        return player.getGamesWon();
    }

    /**
     * Gets the highScore of a player.
     * Ensures Demeter's Law.
     *
     * @param player whose highScore is requested
     * @return highScore of a player
     */
    public int getPlayerHighScore(Player player) {
        return player.getHighScore();
    }

    /**
     * Gets the name of a player.
     * Ensures Demeter's Law.
     *
     * @param player whose name is requested
     * @return the name of a player
     */
    public String getPlayerName(Player player) {
        return player.getName();
    }

    /**
     * Sets the amount of gamesPlayed.
     *
     * @param player      whose amount of gamesPlayed is set
     * @param gamesPlayed amount of gamesPlayed
     */
    public void setPlayerGamesPlayed(Player player, int gamesPlayed) {
        player.setGamesPlayed(gamesPlayed);
    }

    /**
     * Sets the amount of gamesWon.
     *
     * @param player   whose amount of gamesWon is set
     * @param gamesWon amount of gamesWon
     */
    public void setPlayerGamesWon(Player player, int gamesWon) {
        player.setGamesWon(gamesWon);
    }

    /**
     * Sets the highScore of a player.
     *
     * @param player    whose highScore is set
     * @param highScore of a player
     */
    public void setPlayerHighScore(Player player, int highScore) {
        player.setHighScore(highScore);
    }
}