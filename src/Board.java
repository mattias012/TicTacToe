import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    //Board class, how will it look?

    int numberOfboxes;
    HashMap<Integer, String> board = new HashMap<>();

    //Construct the board object
    Board(int numberOfboxes) {
        this.numberOfboxes = numberOfboxes;

        int position[] = new int[numberOfboxes * numberOfboxes];

        for (int i = 0; i < position.length; i++) {
            position[i] = i;
        }

        for (int j = 0; j < position.length; j++) {
            this.board.put(position[j], "" + j);
        }
    }

    //Update board
    public void setBoard(int position, Player player) {

        //Change the board accordingly..
        this.board.put(position, player.getPlayerType());

    }

    public boolean checkWinner(){

        //Check if anyone has 3-5 connecting boxes, depending on layout

        for (int i = 0; i <= 2; i++) {
            String firstPosition = board.get(i * numberOfboxes);
            String secondPosition = board.get(i * numberOfboxes + 1);
            String thirdPosition = board.get(i * numberOfboxes + 2);

            if (firstPosition != null && firstPosition.equals(secondPosition) && firstPosition.equals(thirdPosition)) {
                return true;
            }
        }
        return false;
    }


    public String toString() {

        //Print board

        //Length of gameboard
        int length = numberOfboxes * numberOfboxes;

        //Alize thought it would be better to use Stringbuilder instead of String as stringbuilder object is the same.
        StringBuilder gameBoard = new StringBuilder();

        //Create gameboard
        for (int i = 0; i < length; i++) {

            gameBoard.append("[ ").append(this.board.get(i)).append(" ]");

            //create line break
            if (i == numberOfboxes - 1 || i == (numberOfboxes * 2 - 1)) {
                gameBoard.append("\n");
            }
        }

        return gameBoard.toString();
    }
}
