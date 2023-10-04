public class Player {

    //Playe class

    private String playerName;
    private String playerType;
    private int points;

    Player(String playerName, String playerType){
        this.playerName = playerName;
        this.playerType = playerType;
        this.points = 0;

    }

    public String getplayerName(){
        return this.playerName;
    }

    public String getPlayerType(){
        return this.playerType;
    }
}
