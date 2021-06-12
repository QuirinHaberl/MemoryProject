package Player;

/**
 * this class implements a playerList
 */
public class playerList {
    /**
     * the attribute is the front of the playerList
     */
    player front;

    /**
     * the attribute is the rear of the playerList
     */
    player rear;

    /**
     * the attribute records how many players in this playerList
     */
    int count;

    /**
     * constructs a new playerList
     *
     * @param front
     * @param rear
     */
    public playerList(player front, player rear) {
        this.front = null;
        this.rear = null;
    }

    /**
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * checks if the name has existed already in the playerList
     *
     * @param name that the player gonna use
     * @return if the name is effective
     */
    public boolean ifNameEffective(String name) {
        boolean effective = true;
        player current = front;
        for (int i = 0; i < count; i++) {
            if (current.name == name) {
                effective = false;
            }
            current = current.next;
        }
        return effective;
    }

    /**
     * adds a player to the playerList
     * firstly checks if the name is effective
     *
     * @param name that the player gonna use
     */
    public void addPlayer(String name) {
        if (ifNameEffective(name) == false) {
            throw new IllegalArgumentException("This name exists already! You should use another name!");
        }
        player player = new player(name, 0, null);
        if (front == null) {
            front = player;
            rear = player;
            count++;
        } else {
            rear.sstNext(player);
            rear = player;
            count++;
        }
    }

    /**
     * deletes a player from the playerList by its position
     *
     * @param position of the {@link Player} that will be deleted
     */
    public void deletePlayer(int position) {

        if (position == 0) {
            if (count == 1) {
                front = null;
                rear = null;
                count--;
            } else {
                front = front.next;
                count--;
            }
        } else if (position == count - 1) {
            player current = getPlayer(count - 2);
            current.next = null;
            rear = current;
            count--;
        } else {
            player current = front;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            player temp = current.next;
            current.next = temp.next;
            temp.next = null;
            count--;
        }
    }

    /**
     * return a player by its position
     *
     * @param position of the {@link Player}
     * @return the player
     */
    public player getPlayer(int position) {

        if (front == null) {
            throw new IllegalArgumentException("There is no Player now!");
        } else if (position >= count || position < 0) {
            throw new IllegalArgumentException("Position is bigger than count or smaller than 0!");
        }
        player current = front;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * return the front of the playerList
     *
     * @return the fornt
     */
    public player getFront() {
        return front;
    }

    /**
     * return the rear of the playerList
     *
     * @return the rear
     */
    public player getRear() {
        return rear;
    }

    /**
     * adds 1 score to the score of a player
     * if the player found two same cards, then its score will be added 1
     *
     * @param position of the {@link Player}
     */
    public void addScoreForPlayer(int position) {
        this.getPlayer(position).addScore();
    }

    /**
     * return the name of a player whom we find  by position
     *
     * @param position of a player
     * @return the name of a {@link Player}
     */
    public String getNameOfPlayer(int position) {
        return getPlayer(position).getName();
    }

    /**
     * return the score of a player whom we find by position
     *
     * @param position of a player
     * @return the score of a {@link Player}
     */
    public int getScoreOfPlayer(int position) {
        return getPlayer(position).getScore();
    }

    /**
     * outputs the name and the score of a player on the console whom we find by position
     *
     * @param position of a player
     */
    public void outputTheStatusOfPlayer(int position) {
        getPlayer(position).outputStatusOfPlayer();
    }

    /**
     * outputs the name and score of all players on the console
     */
    public void outputTheStatusofAllPlayers() {
        if (front == null) {
            throw new IllegalArgumentException("There is no player now!");
        }
        for (int i = 0; i < count; i++) {
            outputTheStatusOfPlayer(i);
        }
    }

    /**
     * return the position of a player
     *
     * @param player
     * @return the position if the player
     */
    public int getThePositionOfPlayer(player player) {
        player current = front;
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
     * return a copied playerList
     *
     * @return a copy of the playerList
     */
    public playerList copy() {
        playerList list = new playerList(null, null);
        for (int i = 0; i < count; i++) {
            list.addPlayer(this.getPlayer(i).getName());
            list.getPlayer(i).setScore(this.getPlayer(i).getScore());
        }
        return list;
    }

    /**
     * @return the player who has the highest score
     */
    public player getThePlayerWithHighestScore() {
        if (front == null) {
            throw new IllegalArgumentException("There is no player now!");
        }
        player current = front;
        player playerWithHighestScore = front;
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
     * output the name and score of the player who has the highest score
     */
    public void outputStatusOfPlayerWithHighestScore() {
        getThePlayerWithHighestScore().outputStatusOfPlayer();
    }

    /**
     * sort the playerList according to score
     *
     * @return the sorted {@link playerList}
     */
    public playerList sort() {
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
            String name = this.getPlayer(i).name;  // that just change the name and score, every player including "next" won't be changed.
            int score = this.getPlayer(i).score;
            this.getPlayer(i).name = this.getPlayer(min).name;
            this.getPlayer(i).score = this.getPlayer(min).score;

            this.getPlayer(min).name = name;
            this.getPlayer(min).score = score;
        }
        return this;
    }

    /**
     * return the ranking of the player who has the name
     *
     * @param name of the player
     * @return the ranking of the player
     */
    public int theRankingOfPlayer(String name) {
        this.sort();
        int ranking = 0;
        for (int i = 0; i < count; i++) {
            if (name == this.getPlayer(i).name) {
                ranking = count - i;
            }
        }
        return ranking;
    }

    /**
     * output the name and score of all players according to the score
     */
    public void outputPlayersAccordingToScore() {
        this.sort();          //firstly sorts the playerList
        outputTheStatusofAllPlayers();          //output the information of all players
    }
}