package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
import de.uni_passau.se.memory.Model.Enums.TurnStatus;
import de.uni_passau.se.memory.View.View;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class implements a {@link Game}.
 */
public final class Game {

    /**
     * Stores the current {@link TurnStatus}.
     */
    private TurnStatus turnStatus;

    /**
     * Stores the current {@link GameStatus}.
     */
    private GameStatus gameStatus;

    /**
     * Stores the current {@link PlayingField}.
     */
    private final PlayingField playingField;

    /**
     * Stores a {@link Player} in the {@link PlayerList}.
     */
    private final PlayerList playerList;

    private int playerAmount;

    /**
     * Stores player-profiles
     */
    Database database;

    /**
     * Loads all existing profiles for the current players.
     * A playerProfile has the following structure:
     * playerId;playerName;highScore;gamesPlayed;gamesWon
     *
     * @param playerProfiles to be used
     */
    public void saveProfile(List<String[]> playerProfiles) {
        for (String[] playerProfile : playerProfiles) {
            for (int j = 0; j < getPlayerList().getCount(); j++) {
                if (playerProfile[0].equals(getPlayerList().
                        getPlayer(j).getName()) && !(
                        getPlayerList().getPlayer(j).getName().equals("Player1")
                                || getPlayerList().getPlayer(j).getName().equals("Player2")
                                || getPlayerList().getPlayer(j).getName().equals("Player3")
                                || getPlayerList().getPlayer(j).getName().equals("Player4")
                )) {
                    playerProfile[0] = getPlayerList().getPlayer(j).
                            getName() + "";
                    playerProfile[1] = getPlayerList().getPlayer(j).
                            getAchievements().getHighScore() + "";
                    playerProfile[2] = getPlayerList().getPlayer(j).
                            getAchievements().getGamesPlayed() + "";
                    playerProfile[3] = getPlayerList().getPlayer(j).
                            getAchievements().getGamesWon() + "";
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
     */
    public void useProfile(List<String[]> playerProfiles, List<Boolean> usesProfiles) {
        boolean hasProfile;
        for (int i = 0; i < getPlayerList().getCount(); i++) {
            if (usesProfiles.get(i)) {
                hasProfile = false;
                int j;
                for (j = 0; j < playerProfiles.size(); j++) {
                    if (getPlayerList().getPlayer(i).getName().
                            equals(playerProfiles.get(j)[0])) {
                        hasProfile = true;
                        break;
                    }

                }
                if (hasProfile) {
                    getPlayerList().getPlayer(i).setName(playerProfiles.get(j)[0]);
                    getPlayerList().getPlayer(i).getAchievements().
                            setHighScore(Integer.parseInt(playerProfiles.get(j)[1]));
                    getPlayerList().getPlayer(i).getAchievements().
                            setGamesPlayed(Integer.parseInt(playerProfiles.get(j)[2]));
                    getPlayerList().getPlayer(i).getAchievements().
                            setGamesWon(Integer.parseInt(playerProfiles.get(j)[3]));
                } else {
                    String[] newProfile = new String[]{
                            getPlayerList().getPlayer(i).getName(),
                            getPlayerList().getPlayer(i).getAchievements().getHighScore() + "",
                            getPlayerList().getPlayer(i).getAchievements().getGamesPlayed() + "",
                            getPlayerList().getPlayer(i).getAchievements().getGamesWon() + ""
                    };
                    playerProfiles.add(newProfile);
                }
            }
        }
    }

    /**
     * Stores the progress of all playerProfiles and the HighScoreHistory.
     */
    public void storeProgress() {
        getDatabase().storeHighScoreHistory();
        saveProfile(getDatabase().getPlayerProfiles());
        getDatabase().storePlayerProfiles();
    }

    /**
     * Checks weather a {@link Player} has earned a new achievement
     *
     * @param player who is being checked
     */
    public String checkForAchievements(Player player) {
        player.getAchievements().updatePairCounters();
        player.getAchievements().checkFoundPairsTotal();
        player.getAchievements().checkFoundPairsStreak();
        player.getAchievements().checkHighScore();
        if (!(player.getAchievements().getCurrentAchievements().isEmpty())) {
            View.printAchievement(player.getAchievements().getCurrentAchievements(), player);
            String currentAchievement = player.getAchievements().getCurrentAchievements();
            player.getAchievements().clearCurrentAchievement();
            return currentAchievement;
        }
        return "";
    }

    /**
     * Checks weather multiple players have earned an achievement.
     *
     * @param players who are being checked
     */
    public String checkForAchievements(PlayerList players) {
        String currentAchievement = "";

        if (checkForDraw(players)) {

            int highestScore = players.getHighestScore()[0];
            for (int i = 0; i < players.getCount(); i++) {
                players.getPlayer(i).getAchievements().updateGamesPlayed();

                //If a player has won a game
                if (players.getPlayerScore(i) == highestScore) {
                    players.getPlayer(i).getAchievements().updateGamesWon();
                    players.getPlayer(i).getAchievements().checkGamesWon();

                    //If a player has earned a new achievement
                    if (!(players.getPlayer(i).getAchievements().getCurrentAchievements().isEmpty())) {
                        View.printAchievement(players.getPlayer(i).getAchievements().getCurrentAchievements(), players.getPlayer(i));
                        currentAchievement = currentAchievement +
                                players.getPlayer(i).getName() + " has earned:\n" +
                                players.getPlayer(i).getAchievements().getCurrentAchievements() + "\n";
                        players.getPlayer(i).getAchievements().clearCurrentAchievement();
                        break;
                    }
                }
            }
            return currentAchievement;
        } else {
            return "This game was a draw!";
        }
    }

    public boolean checkForDraw(PlayerList players) {
        int highestScore = players.getHighestScore()[0];
        int playersWithHighestScore = 0;
        for (int i = 0; i < players.getCount(); i++) {
            if (players.getPlayer(i).getScore() == highestScore) {
                playersWithHighestScore++;
            }
        }
        return playersWithHighestScore == 1;
    }

    /**
     * This is a inner class for the timer of a {@link Game}.
     * The default time is 120 seconds (2 minutes)
     */
    public class startCountDown {
        private int count = 120;
        int remainingTime = count;

        /**
         * Gets the {@code remainingTime} of a {@link Game}.
         *
         * @return the {@code remainingTime}
         */
        public int getCount() {
            return remainingTime;
        }

        /**
         * Initiates the countDown.
         */
        public startCountDown() {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                public void run() {
                    remainingTime = count;
                    if (count > 0) {
                        count--;
                    }

                    if (count == 0) {
                        timer.cancel();
                        timer.purge();
                        time = null;
                    }
                }
            };
            timer.schedule(task, 0, 1000);
        }
    }

    /**
     * This is the associated attribute of type CountDown for the game.
     */
    private startCountDown time;

    /**
     * Creates a new {@code INSTANCE} of the {@link Game}.
     */
    private static class InstanceHolder {
        private static final Game INSTANCE = new Game();
    }

    /**
     * The constructor initiates the {@link Game}, the turn and creates a new {@link PlayingField}
     */
    public Game() {
        this.turnStatus = TurnStatus.IDLE;
        this.gameStatus = GameStatus.MENU;
        this.playerList = new PlayerList();
        this.playingField = new PlayingField();
        this.database = Database.getInstance();
        this.database.loadHighScoreHistory();
    }

    /**
     * Returns a new {@code INSTANCE} of the {@link Game}.
     *
     * @return the INSTANCE of a {@link Game}.
     */
    public static Game getInstance() {
        return Game.InstanceHolder.INSTANCE;
    }

    /**
     * Returns, the the end of the time is reached and new settings can be made.
     *
     * @return if the time has ended
     */

    /**
     * Gets the {@code playingField}.
     *
     * @return the current {@code playingField}
     */
    public PlayingField getPlayingField() {
        return playingField;
    }

    /**
     * Gets the {@link TurnStatus}.
     *
     * @return the {@link TurnStatus}
     */
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }

    /**
     * Sets the {@link TurnStatus}.
     *
     * @param turnStatus sets the {@link TurnStatus}
     */
    public void setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
    }

    /**
     * Gets the {@link GameStatus}.
     *
     * @return the {@link GameStatus}
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Gets the {@code playerList}.
     *
     * @return the current {@code playerList}
     */
    public PlayerList getPlayerList() {
        return playerList;
    }

    /**
     * Sets the {@link GameStatus}.
     *
     * @param gameStatus sets the {@link GameStatus}
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * Gets a specified {@link Card}.
     *
     * @param row of the selected card
     * @param col of the selected card
     * @return the selected card
     */
    public Card getCard(int row, int col) {
        return playingField.getBoard()[row][col];
    }

    /**
     * Reveals the first selected {@link Card} of a turn.
     *
     * @param firstRow of the {@link Card}
     * @param firstCol of the {@link Card}
     * @return the current {@link CardStatus} of a {@link Card}
     */
    public CardStatus revealFirstCard(int firstRow, int firstCol) {
        Card firstCard = getCard(firstRow, firstCol);
        if (firstCard.getCardStatus().equals(CardStatus.CLOSED)) {
            firstCard.setCardStatus(CardStatus.OPEN);
            setTurnStatus(TurnStatus.ACTIVE);
        }
        return firstCard.getCardStatus();
    }

    /**
     * Reveals the second selected {@link Card} of a turn.
     * This method used to check information about the {@link CardStatus}
     *
     * @param secondRow of the {@link Card}
     * @param secondCol of the {@link Card}
     * @return the current {@link CardStatus} of a {@link Card}
     */
    public CardStatus revealSecondCard(int secondRow, int secondCol) {
        Card secondCard = getCard(secondRow, secondCol);
        if (secondCard.getCardStatus().equals(CardStatus.OPEN)) {
            secondCard.setCardStatus(CardStatus.AlREADYOPEN);
        } else if (secondCard.getCardStatus().equals(CardStatus.CLOSED)) {
            secondCard.setCardStatus(CardStatus.OPEN);
            setTurnStatus(TurnStatus.IDLE);
        }
        return getCard(secondRow, secondCol).getCardStatus();
    }

    /**
     * Checks whether two selected {@link Card}'s have the same value and form a pair.
     *
     * @param rowFirstCard  Row of the first {@link Card}
     * @param colFirstCard  Column of the first {@link Card}
     * @param rowSecondCard Row of the second {@link Card}
     * @param colSecondCard Column of the second {@link Card}
     * @return whether a pair was found (true) or not (false)
     */
    public boolean pairCheck(int rowFirstCard, int colFirstCard, int rowSecondCard, int colSecondCard) {
        Card firstCard = getCard(rowFirstCard, colFirstCard);
        Card secondCard = getCard(rowSecondCard, colSecondCard);
        if (firstCard.getValue().equals(secondCard.getValue())) {
            removeCards(rowFirstCard, colFirstCard, rowSecondCard, colSecondCard);
            return true;
        } else {
            closeCards(rowFirstCard, colFirstCard, rowSecondCard, colSecondCard);
            return false;
        }
    }

    /**
     * Checks whether all cards have already been turned over.
     * If true, then the images of all cards are open.
     * If false, at least one image is still hidden.
     *
     * @return whether all cards are open or not.
     */
    public boolean areAllCardsOpen() {
        for (int row = 0; row < playingField.getBoard().length; ++row) {
            for (int col = 0; col < playingField.getBoard()[row].length; ++col) {
                if (!(getCard(row, col).getCardStatus().equals(CardStatus.FOUND))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Removes a pair of {@link Card}'s from the {@code board}.
     *
     * @param row1 Row of the first selected card
     * @param col1 Column of the first selected card
     * @param row2 Row of the second selected card
     * @param col2 Column of the second selected card
     */
    public void removeCards(int row1, int col1, int row2, int col2) {
        playingField.getBoard()[row1][col1].setCardStatus(CardStatus.FOUND);
        playingField.getBoard()[row2][col2].setCardStatus(CardStatus.FOUND);
    }

    /**
     * Closes a pair of {@link Card}'s on the {@code board}.
     *
     * @param row1 Row of the first selected card
     * @param col1 Column of the first selected card
     * @param row2 Row of the second selected card
     * @param col2 Column of the second selected card
     */
    public void closeCards(int row1, int col1, int row2, int col2) {
        playingField.getBoard()[row1][col1].setCardStatus(CardStatus.CLOSED);
        playingField.getBoard()[row2][col2].setCardStatus(CardStatus.CLOSED);
    }

    /**
     * Closes all {@link Card}'s.
     */
    public void closeAllCards() {
        for (Card[] cards : playingField.getBoard()) {
            for (Card card : cards) {
                card.setCardStatus((CardStatus.CLOSED));
            }
        }
    }

    /**
     * Returns a {@link Player} to main menu.
     *
     * @param players list of all players
     */
    public void returnToMenu(PlayerList players) {
        players.deleteAllPlayers();
        setGameStatus(GameStatus.MENU);
        setTurnStatus(TurnStatus.IDLE);
    }

    /**
     * Quits a running {@link Game}.
     * Update the gameSum of every player
     */
    public void quitGame() {
        for (int i = 0; i < this.getPlayerList().getCount(); i++) {
            this.getPlayerList().getPlayer(i).getAchievements().updateGamesPlayed();
        }
        setGameStatus(GameStatus.END);
    }


    /**
     * Resets the {@link Game} and restarts it.
     *
     * @param players list of all players
     * @return the rear {@link Player} of the {@link PlayerList}, so that
     * the next turn is started with the first {@link Player}
     */
    public Player resetGame(PlayerList players) {
        setTurnStatus(TurnStatus.IDLE);
        closeAllCards();
        players.resetAllScores();
        View.printBoard(getPlayingField());
        return players.getRear();
    }

    /**
     * Restarts the {@link Game} with repositioned cards.
     *
     * @param players list of all players
     * @return the rear {@link Player} of the {@link PlayerList}, so that
     * the next turn is started with the first {@link Player}
     */
    public Player restartGame(PlayerList players) {
        setTurnStatus(TurnStatus.IDLE);
        closeAllCards();
        playingField.shuffleBoard();
        players.resetAllScores();
        View.printBoard(playingField);
        return players.getRear();
    }

    /**
     * Adds players to the {@link PlayerList}.
     *
     * @param numPlayers  Number of players to be added
     * @param playerNames Names of the new players
     */
    public void addPlayers(int numPlayers, String[] playerNames) {
        for (int i = 0; i < numPlayers; ++i) {
            playerList.addPlayer(playerNames[i]);
        }
    }

    public void addPlayer(String playerName) {
        playerList.addPlayer(playerName);
    }


    /**
     * Gets the the board of a {@code playingField}.
     *
     * @return the board of a {@code playingField}
     */
    public Card[][] getBoard() {
        return playingField.getBoard();
    }

    /**
     * Starts the timer of the {@link Game}.
     */
    public void startTimer() {
        this.time = new startCountDown();
    }

    /**
     * Gets the time of the {@link Game}.
     *
     * @return the time of the {@link Game}.
     */
    public startCountDown getTime() {
        return time;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
