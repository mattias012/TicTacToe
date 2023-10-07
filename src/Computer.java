import java.util.*;

public class Computer extends Player {

    private final Random random = new Random();
    private int level;

    private Board board;

    public Computer(String playerName, String playerType, int level) {
        super(playerName, playerType);
        this.level = level;

    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public int makeMove(HashMap<Integer, String> currentBoard) {

        int markThisBoxFromComputer;
        ArrayList<Integer> availableBoxes = new ArrayList<>();

        //Loop through hashmap to get all available boxes
        for (Map.Entry<Integer, String> entry : currentBoard.entrySet()) {
            String value = entry.getValue();
            if (value.matches("\\d+")) { //Check if the value contains a digit + means more than 1, if it does it is free and left to play.
                availableBoxes.add(entry.getKey()); //add key to list
            }
        }

        int myRandomNumber = random.nextInt(availableBoxes.size());
        markThisBoxFromComputer = availableBoxes.get(myRandomNumber);

        //If not random level (1) is selected, lets go for the tactical one
        if (this.level != 1) {

            //Check for winning moves first
            for (Integer box : availableBoxes) {
                // Copy current board to a temporary map, as this is just hypothetical
                HashMap<Integer, String> tempBoard = new HashMap<>(currentBoard);
                // Make a hypothetical move for the computer
                tempBoard.put(box, "O");
                // Check if this move will make the computer win
                if (checkIfThisWillMakeMeWin("O", tempBoard)) {
                    //If this make the computer win, of course return it
                    return box;
                }
            }
            //Check for blocking moves if the computer cannot win. In its own loop rather than else in previous for-loop
            //Otherwise if it does find a win, it will start directly with blocking
            for (Integer box : availableBoxes) {
                // Copy current board to a temporary map, as this is just hypothetical
                HashMap<Integer, String> tempBoard = new HashMap<>(currentBoard);
                // Make a hypothetical move for the human player
                tempBoard.put(box, "X");
                // Check if this move will make the human player win
                if (checkIfThisWillMakeMeWin("X", tempBoard)) {
                    //If this move makes the human player win, then return it to block the human player
                    return box;
                }
            }
        }
        // If no win or no block is possible, select a random number.
        return markThisBoxFromComputer;
    }

    public boolean checkIfThisWillMakeMeWin(String type, HashMap<Integer, String> tempBoard) {

        //Almost a copy of checkWinner() in Board class.
        boolean foundWin = false;

        //Get the winning combinations from this computer object
        //make sure it is THIS board and not board as in the parameter (which is the current board object)
        List<List<Integer>> winCombinations = this.board.getWinCombinations();

        //Loop through each combination list
        for (List<Integer> combination : winCombinations) {
            if (!combination.isEmpty()) {
                //Get first value in the combination
                String firstValue = tempBoard.get(combination.get(0));
                boolean allSame = true;

                //Loop through/get each value that this combination list has, if they are not equal, return false
                for (int i = 1; i < combination.size(); i++) {
                    if (!tempBoard.get(combination.get(i)).equals(firstValue)) {
                        allSame = false;
                        break;
                    }
                }

                if (allSame) {
                    // Yeah, allsame variable is still true, so we have a winner
                    //set foundwin to true
                    foundWin = true;
                    break; //Stop loop
                }
            }
        }

        return foundWin;
    }

    @Override
    public void talk() {

        //List to store words
        ArrayList<String> motivation = new ArrayList<>();
        ArrayList<String> randomWords = new ArrayList<>();

        //Motivation quotes
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
        String phraseSeven = "It could be that your purpose in life is to serve as a warning to others.";
        motivation.add(phraseSeven);
        String phraseEight = "Never put off until tomorrow what you can do the day after tomorrow.";
        motivation.add(phraseEight);
        String phraseNine = "The best things in life are actually really expensive.";
        motivation.add(phraseNine);
        String phraseTen = "The road to success is dotted with many tempting parking spaces.";
        motivation.add(phraseTen);

        //Motivation quotes
        String randomPhraseOne = "OK, I've made a move, not sure if it is the best one... now it's your turn.";
        randomWords.add(randomPhraseOne);
        String randomPhrasetwo = "I may encounter many defeats but I must not be defeated. Lets go!";
        randomWords.add(randomPhrasetwo);
        String randomPhraseThree = "I feel that the simplicity of life is just being yourself.";
        randomWords.add(randomPhraseThree);
        String randomPhraseFour = "I focus on the step in front of you, not the whole staircase..";
        randomWords.add(randomPhraseFour);
        String randomPhraseFive = "You have survived everything you've gone through up until this point..";
        randomWords.add(randomPhraseFive);
        String randomPhraseSix = "I stay positive. I will conquer this board.";
        randomWords.add(randomPhraseSix);
        String randomPhraseSeven = "This is an interesting game for sure.. I think I made this move to quick. Or did I?";
        randomWords.add(randomPhraseSeven);
        String randomPhraseEight = "Wait.. what? I blame the developer.";
        randomWords.add(randomPhraseEight);
        String randomPhraseNine = ". Is cereal soup?";
        randomWords.add(randomPhraseNine);
        String randomPhraseTen = "*Mic drop*";
        randomWords.add(randomPhraseTen);

        int selectThis = random.nextInt(motivation.size());
        int selectThisWords = random.nextInt(randomWords.size());

        System.out.println(this.playerName + " says:\n");
        System.out.println(randomWords.get(selectThisWords));
        System.out.println("Btw, here is a inspiring quote to keep pushing:");
        System.out.println("\n- " + motivation.get(selectThis) + "\n");

    }
}
