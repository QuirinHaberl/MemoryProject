package Player;

public class player {
    String name;
    int score;
    player next;

    public player(String name, int score, player next){
        this.name = name;
        this.score = score;
        this.next = next;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void addScore(){
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
    public void getStatusOfPlayer(){
        System.out.println("name:" + this.name + ", and the score: " + this.score + "." );
    }
}
