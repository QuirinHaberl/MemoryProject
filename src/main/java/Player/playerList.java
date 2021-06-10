package Player;

public class playerList {
    player front;
    player rear;
    int count;

    public playerList(player front, player rear){
        this.front = null;
        this.rear = null;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public boolean ifNameEffective(String name){
        boolean effective = true;
        player current = front;
        for(int i = 0; i < count; i ++){
            if(current.name == name){
                effective = false;
            }
            current = current.next;
        }
        return effective;
    }

    public void addPlayer(String name){
        if(ifNameEffective(name) == false){
            throw new IllegalArgumentException("This name exists already! You should use another name!");
        }
        player player = new player(name,0,null);
        if(front == null){
            front = player;
            rear = player;
            count ++;
        }else{
            rear.sstNext(player);
            rear = player;
            count ++;
        }
    }

    public void deletePlayer(int position){

        if(position == 0){
            front = front.next;
            count--;
        }else if(position == count-1){
            player current = getPlayer(count-2);
            current.next = null;
            rear = current;
            count--;
        }else{
            player current = front;
            for(int i = 0; i < position - 1; i ++) {
                current = current.next;
            }
            player temp = current.next;
            current.next = temp.next;
            temp.next = null;
            count--;
        }
    }

    public player getPlayer(int position){

        if(front == null){
            throw new IllegalArgumentException("There is no Player now!");
        }else if(position >= count || position < 0){
            throw new IllegalArgumentException("Position is bigger than count or smaller than 0!");
        }
        player current = front;
        for(int i = 0; i < position; i ++){
            current = current.getNext();
        }
        return current;
    }

    public String getNameOfPlayer(int position){
        return getPlayer(position).getName();
    }

    public int getScoreOfPlayer(int position){
        return getPlayer(position).getScore();
    }

    public void addScore(player player){
        player.score ++;
    }

    public void outputTheStatusOfPlayer(int position){
        getPlayer(position).getStatusOfPlayer();
    }

    public void outputTheStatusofAllPlayers(){
        if(front == null){
            throw new IllegalArgumentException("There is no player now!");
        }
        for(int i = 0; i < count; i++){
            outputTheStatusOfPlayer(i);
        }
    }

    public int getThePositionOfPlayer(player player){  //helping methode，uses for sort of players
        player current = front;
        int position = 0;
        for(int i = 0; i < count; i++){
            if(current == player){
                break;
            }
            current = current.next;
            position++;
        }
        return position;
    }

    public playerList copy(){          //辅助方法
        playerList list = new playerList(null,null);
        for(int i = 0; i < count; i++){
            list.addPlayer(this.getPlayer(i).getName());
            list.getPlayer(i).setScore(this.getPlayer(i).getScore());
        }
        return list;
    }

    public player getThePlayerWithHighestScore(){
        if(front == null){
            throw new IllegalArgumentException("There is no player now!");
        }
        player current = front;
        player playerWithHighestScore = front;
        for(int i = 0; i < count; i++){
            if(current == rear){
                break;
            }
            if(current.next.getScore() >= playerWithHighestScore.getScore()){
                playerWithHighestScore = current.next;
            }
            current = current.next;
        }
        return playerWithHighestScore;
    }

    public void outputStatusOfPlayerWithHighestScore(){
        getThePlayerWithHighestScore().getStatusOfPlayer();
    }

    public playerList sortTheListAccordingToScore(){  //player with higher score is ranked first
        playerList list = this.copy();
        if(count == 0){
            throw new IllegalArgumentException("The playList is empty, can't be sorted!");
        }
        playerList sort = new playerList(null,null);
        int length = count;
        for(int i = 0; i < length; i++){
            player temp = list.getThePlayerWithHighestScore();
            sort.addPlayer(temp.getName());
            sort.getPlayer(i).setScore(temp.getScore());
            list.deletePlayer(getThePositionOfPlayer(getThePlayerWithHighestScore()));
        }
        return sort;
    }

    public void outputPlayersAccordingToScore(){
        playerList sort = sortTheListAccordingToScore();
        outputTheStatusofAllPlayers();
    }
}
