import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

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
    public int makeMove(HashMap<Integer, String> board){


        Scanner scanner = new Scanner(System.in);

        int markThisBox = Integer.parseInt(scanner.nextLine());

        //Check that the box is not already taken by any player.
        while (board.get(markThisBox).equals("X") || board.get(markThisBox).equals("O")) {

            System.out.println("Position already marked. Select another one.");
            markThisBox = Integer.parseInt(scanner.nextLine());

        }
        return markThisBox;
    }

    public void talk(){

    }
}
