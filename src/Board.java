import java.util.*;

public class Board {

    //Board class, how will it look?

    private int numberOfboxes;

    private HashMap<Integer, String> board = new HashMap<>();

    protected List<List<Integer>> winCombinations;

    //Construct the board object
    Board(int numberOfboxes) {

        this.numberOfboxes = numberOfboxes;

        //Create an array depending on selected size of board
        //This array is used to put numbers in hashmap keys.
        int position[] = new int[numberOfboxes * numberOfboxes];

        for (int i = 0; i < position.length; i++) {
            position[i] = i + 1;
        }

        for (int j = 0; j < position.length; j++) {
            this.board.put(position[j], "" + (j + 1));
        }

        //set winning combinations for this board by using the method getWinCombinations()
        this.winCombinations = getWinCombinations();
    }

    public HashMap<Integer, String> getBoard() {
        return this.board;
    }

    //Update board
    public void setBoard(int position, Player player) {

        //Change the board accordingly.
        this.board.put(position, player.getPlayerType());

    }

    public List<List<Integer>> getWinCombinations(){
        //List possible winning combinations
        //First create a list variable to store 4 lists
        List<List<Integer>> winCombinations = new ArrayList<>();

        int size = numberOfboxes;  //size of winning combinations is number of boxes

        //First create winning list for rows
        for (int i = 0; i < size; i++) {
            //Create list
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                //Add combination to row list
                row.add(i * size + j + 1);
            }
            //Add row list to the winning combination list
            winCombinations.add(row);
        }

        //Create a winning list for columns
        for (int i = 0; i < size; i++) {
            //Create list
            List<Integer> column = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                //add combination to column list
                column.add(i + j * size + 1);
            }
            //add column list to the winning combination list
            winCombinations.add(column);
        }

        //For diagonals, create to separate lists
        List<Integer> diagonal1 = new ArrayList<>();
        List<Integer> diagonal2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            //add combinations to each list
            diagonal1.add(i * size + i + 1);
            diagonal2.add((i + 1) * size - i);
        }
        //Add these two combinations to the winning list
        winCombinations.add(diagonal1);
        winCombinations.add(diagonal2);

        //For checking the combinations, un-comment to view
        /*
        for (List<Integer> row : winCombinations) {
            System.out.println(row);
        }
         */
        return winCombinations;
    }

    public boolean checkWinner() {

        boolean foundWinner = false;

        //Loop through each combination list
        for (List<Integer> combination : winCombinations) {
            if (!combination.isEmpty()) {
                //Get first value in the combination
                String firstValue = board.get(combination.get(0));
                boolean allSame = true;

                //Loop through/get each value that this combination list has, if they are not equal, return false
                for (int i = 1; i < combination.size(); i++) {
                    if (!board.get(combination.get(i)).equals(firstValue)) {
                        allSame = false;
                        break;
                    }
                }

                if (allSame) {
                    // Yeah, allsame variable is still true, so we have a winner
                    //set foundwinner to true
                    foundWinner = true;
                    break; //Stop loop
                }
            }
        }

        return foundWinner;
    }

    public int checkNumberOfBoxesLeft() {

        //Count number of boxes left, used to see if game is tied.
        int boxesLeft = 0;
        for (Map.Entry<Integer, String> entry : board.entrySet()) {
            String value = entry.getValue();
            if (value.matches("\\d+")) { //Check if the value contains a digit, if it does it is free and left to play.
                boxesLeft = boxesLeft + 1;
            }
        }
        return boxesLeft;
    }

    public String toString() {

        //Print board
        //just format, 8 lines per box will make it look nicer in the terminal. I make it a constant here.
        final int LINES_PER_SQUARE = 8;

        //Length of gameboard
        int length = numberOfboxes * numberOfboxes;

        //Alize thought it would be better to use Stringbuilder instead of String as stringbuilder object is the same.
        StringBuilder gameBoard = new StringBuilder();

        //Create gameboard
        gameBoard.append("-".repeat(LINES_PER_SQUARE * numberOfboxes));
        gameBoard.append("\n");
        for (int i = 1; i <= length; i++) {

            //different spacing depending on number (to align).
            if (i >= 10) {
                gameBoard.append("[  ").append(this.board.get(i)).append("  ]");
            } else {
                gameBoard.append("[  ").append(this.board.get(i)).append("   ]");
            }
            //create line breaks
            if (i % numberOfboxes == 0) {
                gameBoard.append("\n");
                gameBoard.append("-".repeat(LINES_PER_SQUARE * numberOfboxes));
                gameBoard.append("\n");
            }
        }

        return gameBoard.toString();
    }
}
