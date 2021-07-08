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
    private int count;

    /**
     * Constructs a new {@link PlayerList}.
     */
    public PlayerList() {
        this.front = null;
        this.rear = null;
        count = 0;
    }

    /**
     * Gets the amount of players.
     *
     * @return count
     */
    public int getCount() {
        return count;
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
        count++;
    }

    /**
     * Deletes a {@link Player}.
     *
     * @param position of the {@link Player} that is being deleted
     */
    public void deletePlayer(int position) {

        if (position == 0) {
            if (count == 1) {
                front = null;
                rear = null;
            } else {
                front = front.next;
            }
            count--;
        } else if (position == count - 1) {
            Player current = getPlayer(count - 2);
            current.next = null;
            rear = current;
            count--;
        } else {
            Player current = front;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            Player temp = current.next;
            current.next = temp.next;
            temp.next = null;
            count--;
        }
    }

    /**
     * Deletes all {@link Player}'s from the {@link PlayerList}.
     */
    public void deleteAllPlayers() {
        this.front = null;
        this.rear = null;
        count = 0;
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
        for (int i = 0; i < count; i++) {
            getPlayer(i).setScore(0);
        }
    }

    /**
     * Gets the position of a {@link Player}.
     *
     * @param player the current {@link Player}
     * @return the position
     */
    public int getPlayerPosition(Player player) {
        Player current = front;
        int position = 0;
        for (int i = 0; i < count; i++) {
            if (current == player) {
                break;
            }
            current = current.next;
            position++;
        }
        return position;
    }

    /**
     * Gets the current highest {@code score} of all {@link Player}'s.
     *
     * @return highest {@code score} and how many players reached this score
     */
    public int getHighestScore() {
        int highestScore = 0;

        for (int i = 0; i < count; i++) {
            if (getPlayerScore(i) > highestScore) {
                highestScore = getPlayerScore(i);
            }
        }
        return highestScore;
    }

    public int getCountOfWinningPlayers() {
        int playersWithHighestScore = 0;
        int highestScore = getHighestScore();

        for (int i = 1; i < count; i++) {
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


        for (int i = 0; i < count; i++) {
            if (getPlayerScore(i) == highestScore) {
                winningPlayers.add(getPlayerName(i));
            }
        }

        return winningPlayers;
    }

    /**
     * Increments the {@code score} a a selected {@link Player}.
     *
     * @param position of the {@link Player}
     */
    public void updatePlayerScore(int position) {
        this.getPlayer(position).updateScore();
    }

    /**
     * Get the name of a {@link Player}.
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
     * Gets the ranking of all {@link Player}'s.
     *
     * @param name of the Player
     * @return the ranking of the Player
     */
    public int getRanking(String name) {
        //this.sort();
        int ranking = 0;
        for (int i = 0; i < count; i++) {
            if (name.equals(this.getPlayer(i).getName())) {
                ranking = count - i;
            }
        }
        return ranking;
    }

    //TODO
    public String[] getSorted() {
        String[] output = new String[count * 2];
        String[] names = new String[count];
        int[] scores = new int[count];
        for (int i = 0; i < count; i++) {
            names[i] = this.getPlayerName(i);
            scores[i] = this.getPlayerScore(i);
        }
        for (int i = 1; i < count; i++) {
            String currentName = names[i];
            int currentScore = scores[i];

            // Element is moved to the left until it is at the right position.
            int j = i;
            while (j > 0 && currentScore < scores[j - 1]) {
                scores[j] = scores[j - 1];
                names[j] = names[j - 1];
                j--;
            }
            scores[j] = currentScore;
            names[j] = currentName;
        }
        for (int i = 0; i < count; i++) {
            output[i] = names[count - 1 - i];
            output[count + i] = String.valueOf(scores[count - 1 - i]);
        }
        return output;
    }
}