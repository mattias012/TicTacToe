import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Board {

    //Board class, how will it look?

    int numberOfboxes;
    int numberOfRows;
    HashMap<Integer, String> board = new HashMap<>();

    //Construct the board object
    Board(int numberOfboxes) {
        this.numberOfboxes = numberOfboxes;
        this.numberOfRows = numberOfboxes;

        int position[] = new int[numberOfboxes * numberOfboxes];

        for (int i = 0; i < position.length; i++) {
            position[i] = i+1;
        }

        for (int j = 0; j < position.length; j++) {
            this.board.put(position[j], "" + (j+1));
        }
    }

    //Update board
    public void setBoard(int position, Player player) {

        //Change the board accordingly..
        this.board.put(position, player.getPlayerType());

    }

    public boolean checkWinner() {

        boolean foundWinner = false;

        List<List<Integer>> winCombinations = Arrays.asList(
                Arrays.asList(1, 2, 3),  // Rad 1
                Arrays.asList(4, 5, 6),  // Rad 2
                Arrays.asList(7, 8, 9),  // Rad 3
                Arrays.asList(1, 4, 7),  // Kolumn 1
                Arrays.asList(2, 5, 8),  // Kolumn 2
                Arrays.asList(3, 6, 9),  // Kolumn 3
                Arrays.asList(1, 5, 9),  // Diagonal 1
                Arrays.asList(3, 5, 7)   // Diagonal 2
        );

        for (List<Integer> combination : winCombinations) {
            // Hämta värdena på de tre positionerna från din HashMap
            String value1 = board.get(combination.get(0));
            String value2 = board.get(combination.get(1));
            String value3 = board.get(combination.get(2));

            // Kontrollera om alla värden är samma och inte tomma
            if (value1.equals(value2) && value2.equals(value3) && !value1.isEmpty()) {
                // Det är en vinst!
                foundWinner = true;
                break;  // Om vi har hittat en vinst, behöver vi inte kontrollera resten av kombinationerna
            }
        }




    return foundWinner;

    }

    public String toString() {

        //Print board

        //Length of gameboard
        int length = numberOfboxes * numberOfboxes;

        //Alize thought it would be better to use Stringbuilder instead of String as stringbuilder object is the same.
        StringBuilder gameBoard = new StringBuilder();

        //Create gameboard

        gameBoard.append("\n--------------------\n");
        for (int i = 1; i <= length; i++) {

            gameBoard.append("[ ").append(this.board.get(i)).append(" ]");

            //create line breaks
            if (i == numberOfboxes || i == (numberOfboxes * 2) || i == (numberOfboxes * 3) || i == (numberOfboxes * 4)) {
                gameBoard.append("\n");
            }
        }
        gameBoard.append("--------------------\n");

        return gameBoard.toString();
    }
}
