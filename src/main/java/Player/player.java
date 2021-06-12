package Player;

/**
 * This class represents the player.
 */

public class player {
    /**
     * the attribute is the name(ID) of a player
     */
    String name;
    /**
     * the attribute is the score that the player got in the game.
     */
    int score;
    /**
     * this attribute is for connecting the other player.
     */
    player next;

    /**
     * constructs a new player.
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
     * sets a name of player
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void addScore(){ // +1
        this.score ++;
    }
    public int getScore(){
        return score;
    }
    public void sstNext(player next){
        this.next = next;
    }
    public player getNext(){
        return next;
    }
    public void outputStatusOfPlayer(){
        System.out.println("name:" + this.name + ", and the score: " + this.score + "." );
    }
}
