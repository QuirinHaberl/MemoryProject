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
     * The maximum amount of players in a game.
     */
    public static final int MAX_PLAYER_AMOUNT = 4;

    /**
     * Constructs a new {@link PlayerList}.
     */
    public PlayerList() {
        this.front = null;
        this.rear = null;
        size = 0;
    }

    /**
     * Helper for the getSortedPlayerList()-method.
     *
     * @param names        of the players
     * @param scores       of the players
     * @param i            incrementer passed by sort()
     * @param currentName  of current player
     * @param currentScore of current player
     */
    public static void sortingHelper(
            String[] names, int[] scores, int i, String currentName, int currentScore) {
        int j = i;

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
            setNextPlayer(player, player);
            setRearPlayer(player, player);
        } else {
            Player lastPlayer = front;
            while (getNextPlayer(lastPlayer) != front) {
                lastPlayer = getNextPlayer(lastPlayer);
            }
            setNextPlayer(lastPlayer, player);
            setNextPlayer(player, front);
            front.setRear(player);
            setRearPlayer(player, lastPlayer);
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
            current = getNextPlayer(current);
        }
        return current;
    }

    /**
     * Gets the next player in relation of a given player.
     * Gets the next player.
     *
     * @param player whose successor is requested
     * @return the successor of a given player
     */
    public Player getNextPlayer(Player player) {
        return player.getNext();
    }

    /**
     * Sets the next player in relation of a given player.
     *
     * @param player whose successor is set
     * @param next successor to be set
     */
    public void setNextPlayer(Player player, Player next) {
        player.setNext(next);
    }

    /**
     * Sets the previous player in relation of a given player.
     *
     * @param player whose predecessor is set
     * @param rear predecessor to be set
     */
    public void setRearPlayer(Player player, Player rear) {
        player.setRear(rear);
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
        int resetScore = 0;
        for (int i = 0; i < size; i++) {
            setPlayerScore(getPlayer(i), resetScore);
        }
    }

    /**
     * Sets the score of a given player.
     *
     * @param player whose score is set
     * @param score  to be set
     */
    public void setPlayerScore(Player player, int score) {
        player.setScore(score);
    }

    /**
     * Gets the current highest {@code score} of all {@link Player}'s.
     *
     * @return highest {@code score} and how many players reached this score
     */
    public int getHighestScore() {
        int highestScore = 0;

        for (int i = 0; i < size; i++) {
            if (getScoreAtPosition(i) > highestScore) {
                highestScore = getScoreAtPosition(i);
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
            if (getScoreAtPosition(i) == highestScore) {
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
            if (getScoreAtPosition(i) == highestScore) {
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
        return getPlayerName(getPlayer(position));
    }

    /**
     * Gets the {@code score} of player at selected position.
     *
     * @param position of a {@link Player}
     * @return the {@code score} of a {@link Player}
     */
    public int getScoreAtPosition(int position) {
        Player player = getPlayer(position);
        return getPlayerScore(player);
    }

    /**
     * Gets the score of a player.
     *
     * @param player whose score is requested
     * @return the score of a player
     */
    public int getPlayerScore(Player player) {
        return player.getScore();
    }

    /**
     * Sorts the playerList.
     *
     * @return a sorted playerList
     */
    public String[] getSortedPlayerList() {
        String[] output = new String[size * 2];
        String[] names = new String[size];
        int[] scores = new int[size];
        for (int i = 0; i < size; i++) {
            names[i] = this.getPlayerName(i);
            scores[i] = this.getScoreAtPosition(i);
        }
        for (int i = 1; i < size; i++) {
            String currentName = names[i];
            int currentScore = scores[i];

            sortingHelper(names, scores, i, currentName, currentScore);
        }
        for (int i = 0; i < size; i++) {
            output[i] = names[size - 1 - i];
            output[size + i] = String.valueOf(scores[size - 1 - i]);
        }
        return output;
    }

    /**
     * Gets the amount of gamesPlayed.
     *
     * @param player whose gamesPlayed are requested
     * @return amount of gamesPlayed
     */
    public int getPlayerGamesPlayed(Player player) {
        return player.getGamesPlayed();
    }

    /**
     * Gets the amount of gamesWon.
     *
     * @param player whose gamesWon are requested
     * @return amount of gamesWon
     */
    public int getPlayerGamesWon(Player player) {
        return player.getGamesWon();
    }

    /**
     * Gets the highScore of a player.
     *
     * @param player whose highScore is requested
     * @return highScore of a player
     */
    public int getPlayerHighScore(Player player) {
        return player.getHighScore();
    }

    /**
     * Gets the name of a player.
     *
     * @param player whose name is requested
     * @return the name of a player
     */
    public String getPlayerName(Player player) {
        return player.getName();
    }

    /**
     * Generates a string containing the players and their found cards.
     *
     * @return all players with their found cards
     */
    public String foundCardsToString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            str.append(this.getPlayerName(getPlayer(i))).append(":" + " ");
            for (int j = 0; j < this.getPlayerFoundCards(getPlayer(i)).size(); j++) {
                str.append(visualizeFoundCard(this.getPlayerFoundCards(getPlayer(i)).get(j))).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * Visualises a given card by returning its value
     *
     * @param card to be visualized
     * @return the value of a card as a string
     */
    public String visualizeFoundCard(Card card) {
        return card.visualizeCard();
    }

    /**
     * Gets the foundCards of a given player.
     *
     * @param player whose found cards are requested
     * @return found cards of the selected player
     */
    public List<Card> getPlayerFoundCards(Player player) {
        return player.getFoundCards();
    }

    /**
     * Sets the amount of lives a player has.
     *
     * @param player whose lives are set
     * @param lives to be set
     */
    public void setPlayerLives(Player player, int lives){
        player.setLives(lives);
    }

    /**
     * Gets the amount of lives a player has.
     *
     * @param player whose lives are requested
     * @return the lives of a player
     */
    public int getPlayerLives(Player player){
        return player.getLives();
    }

    /**
     * Reduces the amount of lives a player has.
     *
     * @param player whose lives are reduced by 1
     */
    public void reducePlayerLives(Player player){
        player.reduceLives();
    }

    /**
     * Sets the rear of a given player.
     *
     * @param player whose rear is set
     * @param rear to be set
     */
    public void setPlayerRear(Player player, Player rear){
        player.setRear(rear);
    }

    /**
     * Gets the rear of a given player.
     *
     * @param player whose rear is requested
     * @return the rear of a player
     */
    public Player getPlayerRear(Player player){
        return player.getRear();
    }

    /**
     * Returns the profile of a given player as a string.
     *
     * @param player whose profile is requested
     * @return profile as a string
     */
    public String getPlayerProfileToString(Player player){
        return player.profileToString();
    }
}
