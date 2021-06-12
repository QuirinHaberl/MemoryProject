package Player;

/**
 * this class implements the player of the game.
 */
public class player {
    /**
     * the attribute name is the name of a player
     */
    String name;
    /**
     * the attribute score is the score that a player got in the game
     */
    int score;
    /**
     * the attribute player next is used for connecting to the next player
     */
    player next;

    /**
     * constructs a new player
     * @param name
     * @param score
     * @param next
     */
    public player(String name, int score, player next){
        this.name = name;
        this.score = score;
        this.next = next;
    }

    /**
     * set a name for player
     * @param name of a player
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @return the name of a {@link player}
     */
    public String getName(){
        return name;
    }

    /**
     * set the score for a player
     * @param score of a player
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * if a player found two same card, then the score of the player will added 1.
     */
    public void addScore(){ // +1
        this.score ++;
    }

    /**
     *
     * @return the score of a {@link player}
     */
    public int getScore(){
        return score;
    }

    /**
     * set the next player
     * @param next of the {@link player}
     */
    public void setNext(player next){
        this.next = next;
    }

    /**
     *
     * @return the next {@link player}
     */
    public player getNext(){
        return next;
    }

    /**
     * outputs the name and the score of a player on the console
     */
    public void outputStatusOfPlayer(){
        System.out.println("name:" + this.name + ", and the score: " + this.score + "." );
    }
}
