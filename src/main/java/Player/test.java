package Player;

public class test {
    public static void main(String[] args){
        playerList list = new playerList(null,null);
        list.addPlayer("A");
        list.getPlayer(0).setScore(3);
        list.addPlayer("B");
        list.getPlayer(1).setScore(1);
        list.addPlayer("C");
        list.getPlayer(2).setScore(2);
        list.addPlayer("D");
        list.getPlayer(3).setScore(0);
        list.outputTheStatusofAllPlayers();

        System.out.println(list.getThePositionOfPlayer(list.getPlayer(3)));

        list.deletePlayer(2);
        playerList copy = list.copy();
        System.out.println("Copy:");
        copy.outputTheStatusofAllPlayers();
        System.out.println(copy.getCount());

        playerList sort = list.sortTheListAccordingToScore();
        sort.outputTheStatusofAllPlayers();
        System.out.println(sort.getCount());
        list.outputPlayersAccordingToScore();



    }
}
