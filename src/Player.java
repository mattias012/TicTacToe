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

        int markThisBox = Main.checkInputIsANumber(scanner);

        int availableNumbersToSelect = board.size();

        while (markThisBox > availableNumbersToSelect || markThisBox < 1){
            System.out.println("Outside board. Select another one.");
            markThisBox = Main.checkInputIsANumber(scanner);
        }

        //Check that the box is not already taken by any player.
        while (board.get(markThisBox).equals("X") || board.get(markThisBox).equals("O")) {

            System.out.println("Position already marked or outside board. Select another one.");
            markThisBox = Main.checkInputIsANumber(scanner);

        }
        return markThisBox;
    }

    public void talk(){

    }
}
