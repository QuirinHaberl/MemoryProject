package de.uni_passau.se.memory.Model;

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
     * @return the de.uni_passau.se.memory.Model.Enums.Player
     */
    public Player getPlayer(int position) {
        if (front == null) {
            throw new IllegalArgumentException("There is no de.uni_passau.se.memory.Model.Enums.Player now!");
        }
        //TODO Enable again
        /*else if (position > count || position < 0) {
            throw new IllegalArgumentException("Position is bigger than count or smaller than 0!");
        }*/
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
    public int[] getHighestScore() {
        int num = 0; //counts how many players have reached the highest score
        int highestScore = front.getScore();

        for (int i = 1; i < count; i++) {
            if (getPlayerScore(i) > highestScore) {
                highestScore = getPlayerScore(i);
            }
        }

        for (int j = 0; j < count; j++) {
            if (getPlayerScore(j) == highestScore) {
                num = num + 1;
            }
        }

        int[] scoreInformation = {highestScore, num};
        return scoreInformation;
    }

    /**
     * Gets all {@link Player}'s with the highest {@code score}.
     *
     * @return list of all {@link Player} who have the highest {@code score}
     */
    public String[] winningPlayersToString() {
        int num = getHighestScore()[1]; // num of players who reached the highest score
        int highestScore = getHighestScore()[0];
        String[] winningPlayers = new String[num];
        int j = 0;

        for (int i = 0; i < getCount(); i++) {
            if (getPlayerScore(i) == highestScore) {
                winningPlayers[j] = getPlayerName(i);
                if (j < num) {
                    j = j + 1;
                } else break;
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
        this.getPlayer(position).addScore();
    }

    /**
     * Get the name of a {@link Player}.
     *
     * @param position of a de.uni_passau.se.memory.Model.Enums.Player
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
     * @param name of the de.uni_passau.se.memory.Model.Enums.Player
     * @return the ranking of the de.uni_passau.se.memory.Model.Enums.Player
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
}