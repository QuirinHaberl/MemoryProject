package de.uni_passau.se.memory.Model;

import de.uni_passau.se.memory.Controller.SinglePlayerMode;
import de.uni_passau.se.memory.Model.Enums.CardStatus;
import de.uni_passau.se.memory.Model.Enums.CardValue;
import de.uni_passau.se.memory.Model.Enums.GameStatus;
import de.uni_passau.se.memory.Model.Enums.TurnStatus;
import de.uni_passau.se.memory.gui.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class implements a {@link Game}.
 */
public final class Game {

    /**
     * Start time of the count down.
     */
    public static final int START_TIME = 120;
    /**
     * Used to calculate the length of a second.
     */
    public static final int ONE_SECOND = 1000;
    /**
     * Amount of lives used for the singlePlayer.
     */
    public static final int FIVE_LIVES = 5;
    /**
     * Stores the current {@link PlayingField}.
     */
    public final PlayingField playingField;
    /**
     * Stores playerProfiles.
     */
    private final Database database;
    /**
     * Stores a {@link Player} in the {@link PlayerList}.
     */
    private PlayerList playerList;
    /**
     * Stores the current {@link TurnStatus}.
     */
    private TurnStatus turnStatus;
    /**
     * Stores the current {@link GameStatus}.
     */
    private GameStatus gameStatus;
    /**
     * Stores the amount of players.
     */
    private int playerAmount;
    /**
     * Stores the selected singlePlayerMode.
     */
    private SinglePlayerMode singlePlayerMode;
    /**
     * Stores the gameResult.
     */
    private boolean gameWon = false;
    /**
     * This is the associated attribute of type CountDown for the game.
     */
    private CountDownConsole time;

    /**
     * The constructor initiates the {@link Game}.
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
     * Checks whether multiple players have the same score.
     *
     * @param players whose scores are reviewed
     * @return Checks whether a game was a draw (ture) or not (false)
     */
    public boolean checkForDraw(PlayerList players) {
        return players.getCountOfWinningPlayers() > 1;
    }

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
     * Sets the {@link GameStatus}.
     *
     * @param gameStatus sets the {@link GameStatus}
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
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
     * Gets the {@code playerList}.
     */
    public void resetPlayerList() {
        this.playerList = new PlayerList();
    }

    /**
     * Reveals the first selected {@link Card} of a turn.
     *
     * @param firstRow of the {@link Card}
     * @param firstCol of the {@link Card}
     * @return the current {@link CardStatus} of a {@link Card}
     */
    public CardStatus revealFirstCard(int firstRow, int firstCol) {
        Card firstCard = playingField.getCard(firstRow, firstCol);
        if (hasSameCardStatus(getCardStatus(firstCard), CardStatus.CLOSED)) {
            setCardStatus(firstCard, CardStatus.OPEN);
            setTurnStatus(TurnStatus.ACTIVE);
        }
        return getCardStatus(firstCard);
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

        //If first Card is not uncovered, dont open second
        if (turnStatus != TurnStatus.ACTIVE) {
            return CardStatus.CLOSED;
        }

        Card secondCard = playingField.getCard(secondRow, secondCol);

        if (hasSameCardStatus(getCardStatus(secondCard), CardStatus.OPEN)) {
            setCardStatus(secondCard, CardStatus.AlREADYOPEN);

        } else if (hasSameCardStatus(getCardStatus(secondCard), CardStatus.CLOSED)) {
            setCardStatus(secondCard, CardStatus.OPEN);
            setTurnStatus(TurnStatus.IDLE);
        }
        return getCardStatus(playingField.getCard(secondRow, secondCol));
    }

    /**
     * Checks whether two given cards have the same cardStatus.
     *
     * @param firstStatus  to be checked
     * @param secondStatus to be checked
     * @return whether both cards have the same status
     */
    public boolean hasSameCardStatus(CardStatus firstStatus, CardStatus secondStatus) {
        return firstStatus.equals(secondStatus);
    }

    /**
     * Sets a cardStatus.
     *
     * @param card       card to be altered
     * @param cardStatus to be set
     */
    public void setCardStatus(Card card, CardStatus cardStatus) {
        card.setCardStatus(cardStatus);
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
        Card firstCard = playingField.getCard(rowFirstCard, colFirstCard);
        Card secondCard = playingField.getCard(rowSecondCard, colSecondCard);
        if (getCardValue(firstCard).equals(getCardValue(secondCard))) {
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
    public boolean areAllCardsFound() {
        for (int row = 0; row < playingField.getBoard().length; ++row) {
            for (int col = 0; col < playingField.getBoard()[row].length; ++col) {
                if (!(getCardStatus(playingField.getCard(row, col)).equals(CardStatus.FOUND))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the cardValue of a given card.
     *
     * @param card whose value is returned
     * @return the cards value
     */
    public CardValue getCardValue(Card card) {
        return card.getValue();
    }

    /**
     * Gets the cardStatus of a given card.
     *
     * @param card whose status is returned
     * @return the cardStatus
     */
    public CardStatus getCardStatus(Card card) {
        return card.getCardStatus();
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
        setGameStatus(GameStatus.END);
    }

    /**
     * Resets the {@link Game}.
     *
     * @param players list of all players
     */
    public void resetGame(PlayerList players) {
        setTurnStatus(TurnStatus.IDLE);
        closeAllCards();
        players.resetAllScores();
        View.printBoard(getPlayingField());
    }

    /**
     * Restarts the {@link Game} with repositioned cards.
     *
     * @param players list of all players
     */
    public void restartGame(PlayerList players) {
        setTurnStatus(TurnStatus.IDLE);
        closeAllCards();
        playingField.shuffleBoard();
        players.resetAllScores();
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

    /**
     * Adds a given player to the game.
     *
     * @param playerName to be added
     */
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
        this.time = new CountDownConsole();
    }

    /**
     * Gets the time of the {@link Game}.
     *
     * @return the time of the {@link Game}.
     */
    public CountDownConsole getTime() {
        return time;
    }

    /**
     * Gets the playerAmount.
     *
     * @return the playerAmount
     */
    public int getPlayerAmount() {
        return playerAmount;
    }

    /**
     * Sets the playerAmount.
     *
     * @param playerAmount to be set.
     */
    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    /**
     * Gets the database in use.
     *
     * @return the database of a game
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Gets the chosen singlePlayerMode.
     *
     * @return the chosen SinglePlayerMode
     */
    public SinglePlayerMode getSinglePlayerMode() {
        return singlePlayerMode;
    }

    /**
     * Sets the singlePlayerMode.
     *
     * @param singlePlayerMode to be set
     */
    public void setSinglePlayerMode(SinglePlayerMode singlePlayerMode) {
        this.singlePlayerMode = singlePlayerMode;
    }

    /**
     * Returns whether a game is won.
     *
     * @return status of the game
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets whether a game is won.
     *
     * @param gameResult of a game
     */
    public void setGameResult(boolean gameResult) {
        this.gameWon = gameResult;
    }

    /**
     * Increments the number of gamesPlayed on every player.
     */
    public void updateGamesPlayed() {
        for (int i = 0; i < playerList.size(); i++) {
            updateGamesPlayed(playerList.getPlayer(i));
        }
    }

    /**
     * Increments the number of gamesPlayed.
     *
     * @param player whose gamesPlayed is incremented
     */
    public void updateGamesPlayed(Player player) {
        player.updateGamesPlayed();
    }

    /**
     * Increments the number of won games.
     *
     * @param player whose gamesWon is incremented
     */
    public void updateGamesWon(Player player) {
        player.updateGamesWon();
    }

    /**
     * Checks weather a {@link Player} has earned a new achievement
     *
     * @param player who is being checked
     * @return a players last achievement
     */
    public String checkForAchievementsInGame(Player player) {
        String currentAchievement = "";

        updatePlayerPairCounters(player);
        checkPlayerFoundPairsTotal(player);
        checkPlayerFoundPairsStreak(player);
        checkPlayerHighScore(player);

        //If a new achievement was earned
        if (!(getPlayerAchievement(player).isEmpty())) {
            View.printAchievement(getPlayerAchievement(player), player);
            currentAchievement = getPlayerAchievement(player);
            clearPlayerAchievement(player);
            return currentAchievement;
        }
        return currentAchievement;
    }

    /**
     * Updates the pariCounter of a player.
     *
     * @param player whose counter is updated
     */
    public void updatePlayerPairCounters(Player player) {
        player.updatePairCounters();
    }

    /**
     * Checks whether player has earned a new achievement.
     * pairTotal is checked.
     *
     * @param player to be checked.
     */
    public void checkPlayerFoundPairsTotal(Player player) {
        player.checkFoundPairsTotal();
    }

    /**
     * Checks whether player has earned a new achievement.
     * pairStreak is checked.
     *
     * @param player to be checked.
     */
    public void checkPlayerFoundPairsStreak(Player player) {
        player.checkFoundPairsStreak();
    }

    /**
     * Checks whether player has earned a new achievement.
     * gamesWon is checked.
     *
     * @param player to be checked.
     */
    public void checkPlayerGamesWon(Player player) {
        player.checkGamesWon();
    }

    /**
     * Checks whether player has earned a new achievement.
     * highScore is checked.
     *
     * @param player to be checked.
     */
    public void checkPlayerHighScore(Player player) {
        player.checkHighScore();
    }

    /**
     * Checks weather the winner has earned an achievement.
     *
     * @param players who are being checked
     * @return a players last achievement
     */
    public String checkForAchievementsAfterGame(PlayerList players) {
        String currentAchievement = "";
        Player player;

        for (int i = 0; i < players.size(); i++) {
            int highestScore = players.getHighestScore();
            player = players.getPlayer(i);

            //If a player has won a game
            if (players.getScoreAtPosition(i) == highestScore && !checkForDraw(players)) {
                updateGamesWon(player);
                checkPlayerGamesWon(player);

                //If a player has earned a new achievement
                if (!(getPlayerAchievement(player).isEmpty())) {
                    View.printAchievement(getPlayerAchievement(player), players.getPlayer(i));
                    currentAchievement = currentAchievement +
                            getPlayerName(player) + " has earned: " +
                            getPlayerAchievement(player) + "\n";
                    clearPlayerAchievement(player);
                    break;
                }
            }
        }
        return currentAchievement;
    }

    /**
     * Gets the current achievement of a player.
     *
     * @param player whose achievement is returned
     * @return the achievement of a player
     */
    public String getPlayerAchievement(Player player) {
        return player.getCurrentAchievement();
    }

    /**
     * Gets the name of a player.
     *
     * @param player whose name is returned
     * @return the name of a player
     */
    public String getPlayerName(Player player) {
        return player.getName();
    }

    /**
     * Clears a players current achievement.
     *
     * @param player whose achievement is cleared
     */
    public void clearPlayerAchievement(Player player) {
        player.clearCurrentAchievement();
    }

    /**
     * Gets the timerCount.
     *
     * @return the timerCount
     */
    public int getTimeCount() {
        return time.getCount();
    }

    /**
     * Creates a new {@code INSTANCE} of the {@link Game}.
     */
    private static class InstanceHolder {
        private static final Game INSTANCE = new Game();
    }

    /**
     * This is a inner class for the timer of a {@link Game} in the console.
     * The default time is 120 seconds (2 minutes)
     */
    public class CountDownConsole {
        int count = START_TIME;
        int remainingTime = count;

        /**
         * Initiates the countDown for the console.
         */
        public CountDownConsole() {
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
            timer.schedule(task, 0, ONE_SECOND);
        }

        /**
         * Gets the {@code remainingTime} of a {@link Game}.
         *
         * @return the {@code remainingTime}
         */
        public int getCount() {
            return remainingTime;
        }
    }
}
