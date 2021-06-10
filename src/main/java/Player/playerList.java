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
            if(count == 1){
                front = null;
                rear = null;
                count--;
            }else{
                front = front.next;
                count--;
            }
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

    public player getFront(){
        return front;
    }

    public player getRear(){
        return rear;
    }

    public void addScoreForPlayer(int position){
        this.getPlayer(position).addScore();
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
        getPlayer(position).outputStatusOfPlayer();
    }

    public void outputTheStatusofAllPlayers(){
        if(front == null){
            throw new IllegalArgumentException("There is no player now!");
        }
        for(int i = 0; i < count; i++){
            outputTheStatusOfPlayer(i);
        }
    }

    public int getThePositionOfPlayer(player player){  //helping methode，uses for sort of players.
        player current = front;
        int position = 0;
        for(int i = 0; i < count; i++){
            if(current.name == player.name && current.score == player.score){
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
        getThePlayerWithHighestScore().outputStatusOfPlayer();
    }


    public void outputPlayersAccordingToScore(){
        playerList sort = this.sort();
        sort.outputTheStatusofAllPlayers();
    }

    public playerList sort(){
        if(count == 0){
            throw new IllegalArgumentException("The playList is empty, can't be sorted!");
        }
        for(int i = 0; i < count; i ++){
            int min = i;
            for(int j = i+1; j < count; j++){
                if(this.getPlayer(j).score < this.getPlayer(min).score){
                    min = j;
                }
            }

            String name = this.getPlayer(i).name;
            int score = this.getPlayer(i).score;
            this.getPlayer(i).name = this.getPlayer(min).name;
            this.getPlayer(i).score = this.getPlayer(min).score;

            this.getPlayer(min).name = name;
            this.getPlayer(min).score = score;


        }
        return this;
    }

}
