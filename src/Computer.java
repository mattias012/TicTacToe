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
    @Override
    public void talk(){

        ArrayList<String> motivation = new ArrayList<>();

        String phraseOne = "When tempted to fight fire with fire, remember that the Fire Department usually uses water.";
        motivation.add(phraseOne);
        String phraseTwo = "Age is of no importance unless you’re a cheese.";
        motivation.add(phraseTwo);
        String phraseThree = "Change is not a four letter word… but often your reaction to it is!";
        motivation.add(phraseThree);
        String phraseFour = "Every tattoo is temporary, because we’re all slowly dying.";
        motivation.add(phraseFour);
        String phraseFive = "I am an early bird and a night owl… so I am wise and I have worms.";
        motivation.add(phraseFive);
        String phraseSix = "I used to think I was indecisive, but now I’m not so sure.";
        motivation.add(phraseSix);
        String phraseSeven = "t could be that your purpose in life is to serve as a warning to others.";
        motivation.add(phraseSeven);
        String phraseEight = "Never put off until tomorrow what you can do the day after tomorrow.";
        motivation.add(phraseEight);
        String phraseNine = "The best things in life are actually really expensive.";
        motivation.add(phraseNine);
        String phraseTen = "The road to success is dotted with many tempting parking spaces.";
        motivation.add(phraseTen);

        int selectThis = random.nextInt(motivation.size());

        System.out.println("\n" + motivation.get(selectThis) + "\n");

    }
}
