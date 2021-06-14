package Model;

/**
 * this class implements a PlayerList
 */
public class PlayerList {
    /**
     * the attribute is the front of the PlayerList
     */
    private Player front;

    /**
     * the attribute is the rear of the PlayerList
     */
    private Player rear;

    /**
     * the attribute records how many players in this PlayerList
     */
    private int count;

    /**
     * constructs a new PlayerList
     */
    public PlayerList() {
        this.front = null;
        this.rear = null;
        count = 0;
    }

    /**
     * @return count
     */
    public int getCount() {
        return count;
    }


    /**
     * checks if the name has existed already in the PlayerList
     *
     * @param name that the Player gonna use
     * @return if the name is effective
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
     * adds a Player to the PlayerList
     * firstly checks if the name is effective
     * Structure of the list is a circle, that means the next node from the
     * last one is the front node
     *
     * @param name that the Player gonna use
     */
    public void addPlayer(String name) {
        if (!(validateName(name))) {
            throw new IllegalArgumentException("This name exists already! You should use another name!");
        }
        Player player = new Player(name, 0, null, null);
        if (front == null) {
            front = player;
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

        }
        count++;
    }

    /**
     * deletes a Player from the PlayerList by its position
     *
     * @param position of the {@link Player} that will be deleted
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
     * return a Player by its position
     *
     * @param position of the {@link Player}
     * @return the Player
     */
    public Player getPlayer(int position) {

        if (front == null) {
            throw new IllegalArgumentException("There is no Player now!");
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
     * return the front of the PlayerList
     *
     * @return the fornt
     */
    public Player getFront() {
        return front;
    }

    /**
     * return the rear of the PlayerList
     *
     * @return the rear
     */
    public Player getRear() {
        return rear;
    }

    /**
     * adds 1 score to the score of a Player
     * if the Player found two same cards, then its score will be added 1
     *
     * @param position of the {@link Player}
     */
    public void updatePlayerScore(int position) {
        this.getPlayer(position).addScore();
    }

    /**
     * return the name of a Player whom we find  by position
     *
     * @param position of a Player
     * @return the name of a {@link Player}
     */
    public String getPlayerName(int position) {
        return getPlayer(position).getName();
    }

    /**
     * return the score of a Player whom we find by position
     *
     * @param position of a Player
     * @return the score of a {@link Player}
     *
     */
    public int getPlayerScore(int position) {
        return getPlayer(position).getScore();
    }

    /**
     * outputs the name and the score of a Player on the console whom we find by position
     *
     * @param position of a Player
     */
    public void printPlayerStatus(int position) {
        getPlayer(position).printPlayerStatus();
    }

    /**
     * outputs the name and score of all players on the console
     */
    public void printEveryPlayerStatus() {
        if (front == null) {
            throw new IllegalArgumentException("There is no Player now!");
        }
        for (int i = 0; i < count; i++) {
            printPlayerStatus(i);
        }
    }

    /**
     * return the position of a {@link Player}
     *
     * @param player the current {@link Player}
     * @return the position if the {@link Player}
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
     * return a copied PlayerList
     * //TODO Diese Methode gibt aber nichts zurück? -Jan
     */
    public void copy() {
        for (int i = 0; i < count; i++) {
            addPlayer(this.getPlayer(i).getName());
            getPlayer(i).setScore(this.getPlayer(i).getScore());
        }
    }

    /**
     * @return the Player who has the highest score
     */
    public Player getWinningPlayer() {
        if (front == null) {
            throw new IllegalArgumentException("There is no Player now!");
        }
        Player current = front;
        Player playerWithHighestScore = front;
        for (int i = 0; i < count; i++) {
            if (current == rear) {
                break;
            }
            if (current.next.getScore() >= playerWithHighestScore.getScore()) {
                playerWithHighestScore = current.next;
            }
            current = current.next;
        }
        return playerWithHighestScore;
    }

    /**
     * output the name and score of the Player who has the highest score
     */
    public void printWinningPlayer() {
        getWinningPlayer().printPlayerStatus();
    }

    /**
     * sort the PlayerList according to score
     *
     * @return the sorted {@link PlayerList}
     */
    public PlayerList sort() {
        if (count == 0) {
            throw new IllegalArgumentException("The playList is empty, can't be sorted!");
        }
        for (int i = 0; i < count; i++) {           // that is based on the select sort thought.
            int min = i;
            for (int j = i + 1; j < count; j++) {
                if (this.getPlayer(j).score < this.getPlayer(min).score) {
                    min = j;
                }
            }
            String name = this.getPlayer(i).name;  // that just change the name and score, every Player including "next" won't be changed.
            int score = this.getPlayer(i).score;
            this.getPlayer(i).name = this.getPlayer(min).name;
            this.getPlayer(i).score = this.getPlayer(min).score;

            this.getPlayer(min).name = name;
            this.getPlayer(min).score = score;
        }
        return this;
    }

    /**
     * return the ranking of the Player who has the name
     *
     * @param name of the Player
     * @return the ranking of the Player
     */
    public int getRanking(String name) {
        this.sort();
        int ranking = 0;
        for (int i = 0; i < count; i++) {
            if (name.equals(this.getPlayer(i).name)) {
                ranking = count - i;
            }
        }
        return ranking;
    }

    /**
     * output the name and score of all players according to the score
     */
    public void printPlayerScores() {
        PlayerList sort = this.sort();          //firstly sorts the PlayerList
        printEveryPlayerStatus();          //output the information of all players
    }

    public void print() {
        for (Player p = front; p.next != front; p = p.next) {
            System.out.println("[" + p.getName() + "]");
        }
    }
}