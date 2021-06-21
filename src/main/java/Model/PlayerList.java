package Model;

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
     * Checks, whether a name already exists.
     *
     * @param name of a player
     * @return if a name is already in use
     */
    public boolean validateName(String name) {
        Player current = front;
        for (int i = 0; i < count; i++) {
            if (current.name.equals(name)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * Adds a {@link Player} to the {@link PlayerList}.
     *
     * @param name of a player
     */
    public void addPlayer(String name) {
        if (!(validateName(name))) {
            throw new IllegalArgumentException("This name exists already! You should use another name!");
        }
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
     * @return the Model.Enums.Player
     */
    public Player getPlayer(int position) {
        if (front == null) {
            throw new IllegalArgumentException("There is no Model.Enums.Player now!");
        } else if (position >= count || position < 0) {
            throw new IllegalArgumentException("Position is bigger than count or smaller than 0!");
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
     * @return highest {@code score}
     */
    public int getHighestScore() {
        if (front == null) {
            throw new IllegalArgumentException("There is no Model.Enums.Player now!");
        }
        int highestScore = front.getScore();
        for (int i = 0; i < count - 1; i++) {
            if (getPlayer(i).getNext().getScore() > highestScore) {
                highestScore = getPlayer(i).getNext().getScore();
            }
        }
        return highestScore;
    }

    /**
     * Gets all {@link Player}'s with the highest {@code score}.
     *
     * @param playerList the list of all current players
     * @return list of all {@link Player} who have the highest {@code Model.Enums.Player
     */
    public PlayerList getWinningPlayers(PlayerList playerList) {
        if (front == null) {
            throw new IllegalArgumentException("There is no Model.Enums.Player now!");
        }
        int highestScore = getHighestScore();

        for (Player player = playerList.front;
             player.next != front; player = player.next) {

            if (player.getScore() != highestScore) {
                int pos = getPlayerPosition(player);
                deletePlayer(pos);
            }
        }
        return playerList;
    }
}