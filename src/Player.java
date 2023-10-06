import java.util.ArrayList;
import java.util.Collections;

public class Player {

    //Playe class

    protected String playerName;
    protected String playerType;
    protected int points;

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

    public void setPoints() {
        this.points = this.points + 1;
    }
    public String getPoints(){
        return this.points + "p";
    }

}
