package Model;

import Model.Enums.GameStatus;
import Model.Enums.TurnStatus;
import View.View;

/**
 * This class is the control part of the MVC-architecture.
 */
public class Game {

    /**
     * Current {@link TurnStatus}
     */
    private TurnStatus turnStatus;

    /**
     * Current {@link GameStatus}
     */
    private GameStatus gameStatus;

    /**
     * Active {@link PlayingField}
     */
    private PlayingField playingField;

    private PlayerList playerList;

    /**
     * The Constructor initiates the game, the turn and creates a new {@link PlayingField}
     */
    public Game() {
        this.turnStatus = TurnStatus.IDLE;
        this.gameStatus = GameStatus.MENU;
        this.playerList = new PlayerList();
        this.playingField = new PlayingField();
    }

    /**
     * Getter for {@code playingField}
     *
     * @return the {@code playingField}
     */
    public Card[][] getBoard() {
        return playingField.getBoard();
    }

    /**
     * @return
     */
    public PlayingField getPlayingField() {
        return playingField;
    }

    /**
     * Setter for {@code playingField}
     */
    public void setPlayingField(PlayingField playingField) {
        this.playingField = playingField;
    }

    /**
     * Getter of {@link TurnStatus}
     *
     * @return the {@link TurnStatus}
     */
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }

    /**
     * Setter of the {@link TurnStatus}
     *
     * @param turnStatus sets the {@link TurnStatus}
     */
    public void setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
    }

    /**
     * Getter of {@link GameStatus}
     *
     * @return the {@link GameStatus}
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public void setPlayerList(PlayerList playerList) {
        this.playerList = playerList;
    }

    /**
     * Setter of the {@link GameStatus}
     *
     * @param gameStatus sets the {@link GameStatus}
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * Returns a specified card.
     *
     * @param row Row position of the selected card
     * @param col Column position of the selected card
     * @return the selected card
     */
    public Card getCard(int row, int col) {
        return playingField.getBoard()[row][col];
    }

    /**
     * Reveals the first selected {@link Card} of a turn
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
     * This method used to proof additional information about the {@link CardStatus}
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
     * Checks weather two selected {@link Card} have the same image and therefore form a pair
     *
     * @param rowFirstCard  : Row of the first {@link Card}
     * @param colFirstCard  : Column of the first {@link Card}
     * @param rowSecondCard : Row of the second {@link Card}
     * @param colSecondCard : Column of the second {@link Card}
     * @return if a pair was found (true) or not (false)
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
     * Removes a {@link Card} pair from the {@code board}.
     *
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected card
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected card
     */
    public void removeCards(int row1, int col1, int row2, int col2) {
        playingField.getBoard()[row1][col1].setCardStatus(CardStatus.FOUND);
        playingField.getBoard()[row2][col2].setCardStatus(CardStatus.FOUND);
    }

    /**
     * Closes a {@link Card} pair from the {@code board}.
     *
     * @param row1 Row position of the first selected card
     * @param col1 Column position of the first selected card
     * @param row2 Row position of the second selected card
     * @param col2 Column position of the second selected card
     */
    public void closeCards(int row1, int col1, int row2, int col2) {
        playingField.getBoard()[row1][col1].setCardStatus(CardStatus.CLOSED);
        playingField.getBoard()[row2][col2].setCardStatus(CardStatus.CLOSED);
    }

    /**
     * Closes all {@link Card} from the {@code board}.
     */
    public void closeAllCards() {
        for (Card[] cards : playingField.getBoard()) {
            for (Card card : cards) {
                card.setCardStatus((CardStatus.CLOSED));
            }
        }
    }

    /**
     * Tests whether the transferred input was correct.
     *
     * @param tokens Passed input
     * @return Returns true if the passed input(two coordinates) was correct.
     */
    //TODO vielleicht fällt jemanden etwas einfacheres ein
    public boolean correctInput(String[] tokens) {
        //Checks whether the input contained two parameters
        if (tokens.length < 2) {
            View.error("Have not received enough parameters");
            return false;
        } else {
            if (tokens.length > 2) {
                View.error("Received too many parameters");
                return false;
            } else {
                boolean[] cache = new boolean[2];
                for (int i = 0; i < 2; i++) {
                    if (tokens[i].length() == 1) {
                        if (tokens[i].matches("\\d")) {
                            if (Integer.parseInt(tokens[i]) < getBoard().length) {
                                cache[i] = true;
                            } else {
                                if (i == 0) {
                                    View.error("First entry was out of range");
                                } else {
                                    View.error("Second entry was out of range");
                                }
                            }
                        } else {
                            if (i == 0) {
                                View.error("First entry was not a valid number");
                            } else {
                                View.error("Second entry was not a valid number");
                            }
                        }
                    } else {
                        if (i == 0) {
                            View.error("First entry was not a valid number");
                        } else {
                            View.error("Second entry was not a valid number");
                        }
                    }
                }
                return cache[0] && cache[1];
            }
        }
    }

    /**
     * Brings you back to main menu
     *
     * @param players list of all players
     */
    public void returnToMenu(PlayerList players) {
        players.deleteAllPlayers();
        setGameStatus(GameStatus.MENU);
        setTurnStatus(TurnStatus.IDLE);
    }

    /**
     * Command to quit a running game
     */
    public void quitGame() {
        setGameStatus(GameStatus.END);
    }


    /**
     * Resets the Model.Enums.Game and restarts it
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
     * Restarts the Model.Enums.Game with repositioned cards
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
     * This method implements a list of Players for the Model.Enums.Game.
     *
     * @param input number of players selected
     * @return true if no error appeared
     */
    public boolean selectPlayerMode(String input) {
        if (input.length() == 1) {
            if (input.matches("\\d")) {
                int num = Integer.parseInt(input);
                if (num > 4) {
                    View.error("Only a maximum of 4 players can take part");
                    return false;
                } else {
                    if (num < 1) {
                        View.error("At least 1 player must take part");
                        return false;
                    } else {
                        for (int i = 1; i <= num; ++i) {
                            playerList.addPlayer("Spieler " + i);
                        }
                        return true;
                    }
                }
            }
        }
        View.error("Entry was not a valid number");
        return false;
    }
}