import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Player {

    //Player class

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

        //new scanner object
        Scanner scanner = new Scanner(System.in);

        //first make sure user puts a number
        int markThisBox = Main.checkInputIsANumber(scanner);

        //Set max numbers
        int availableNumbersToSelect = board.size();

        //Make sure the number chosen is a valid position to avoid NullPointerException.
        while (markThisBox > availableNumbersToSelect || markThisBox < 1 || board.get(markThisBox).equals("X") || board.get(markThisBox).equals("O")) {
            System.out.println("Invalid position. Select another one.");
            markThisBox = Main.checkInputIsANumber(scanner);
        }
        //Return the number
        return markThisBox;
    }

    public void talk(){
            //Human players don't talk.
    }
}
