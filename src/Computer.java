import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Computer extends Player {

    private final Random random = new Random();

    public Computer(String playerName, String playerType) {
        super(playerName, playerType);
    }

    @Override
    public int makeMove(HashMap<Integer, String> board){

        int markThisBoxFromComputer = 0;
        ArrayList<Integer> availableBoxes = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : board.entrySet()) {
            String value = entry.getValue();
            if (value.matches("\\d+")) { //Check if the value contains a digit + means more than 1, if it does it is free and left to play.
                availableBoxes.add(entry.getKey()); //add key to list
            }
        }

        int myRandomNumber = random.nextInt(availableBoxes.size());
        markThisBoxFromComputer = availableBoxes.get(myRandomNumber);

        return markThisBoxFromComputer;

    }
}
