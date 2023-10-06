import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Welcome to TicTacToe, start with setup

        System.out.println("Welcome to Tic Tac Toe! How do you want to play?");
        System.out.println("1. 1 vs. 1");
        System.out.println("2. You vs. Computer");

        Scanner scanner = new Scanner(System.in);
        int menuChoice = scanner.nextInt();

        if (menuChoice == 1) {
            scanner.nextLine(); //clear scanner

            System.out.print("Name player 1: ");
            String namePlayerOne = scanner.nextLine();
            System.out.print("Name player 2: ");
            String namePlayerTwo = scanner.nextLine();

            //Create players
            Player playerOne = new Player(namePlayerOne, "X");
            Player playerTwo = new Player(namePlayerTwo, "O");

            //Store players
            ArrayList<Player> players = new ArrayList<>();
            players.add(playerOne);
            players.add(playerTwo);


            //How large battlefield?
            System.out.println("How many large gameboard do you want? 3, 4 or 5?");
            int numberOfBoxes = scanner.nextInt();

            //Clear scanner
            scanner.nextLine();

            //Play game
            //Take turns
            boolean isThereAWinner = false;
            boolean stopGame = false;

            //Run game until someone says stop.
            while (!stopGame) {

                //Create board each time a new game starts
                Board board = new Board(numberOfBoxes);

                //shuffle players each turn. It needs to be fair.
                Collections.shuffle(players);

                System.out.println("NEW GAME - LET'S GO!");

                while (!isThereAWinner) {

                        for (Player player : players) {
                            System.out.println(board);

                            //Check that it is possible to continue to play.
                            int numberOfBoxesLeft = board.checkNumberOfBoxesLeft();
                            if (numberOfBoxesLeft == 0) {
                                System.out.println("\nTied! Better luck next time guys.");
                                System.out.println(board);
                                isThereAWinner = true;
                                break;
                            }

                            System.out.print(player.getplayerName() + ", select a box by number: ");
                            int markThisBox = Integer.parseInt(scanner.nextLine());

                            while (board.getBoard().get(markThisBox).equals("X") || board.getBoard().get(markThisBox).equals("O")) {

                                System.out.println("Position already marked. Select another.");
                                markThisBox = Integer.parseInt(scanner.nextLine());

                            }

                            //Update board
                            board.setBoard(markThisBox, player);

                            //Check if there's a winner
                            isThereAWinner = board.checkWinner();

                            if (isThereAWinner) {
                                System.out.println("\nWe have a winner! Congratulations " + player.getplayerName() + "!");
                                player.setPoints();
                                System.out.println(board);
                                break;
                            }
                        }
                }

                //Sort arraylist from highest to lowest using comparator and collections.
                Comparator<Player> compareByPoints = (Player p1, Player p2) -> p1.getPoints().compareTo(p2.getPoints());
                Collections.sort(players, Collections.reverseOrder(compareByPoints));

                //Print scoreboard with highlighted number 1
                System.out.println("--- Scoreboard ---");
                for (int i = 0; i < players.size(); i++) {
                    if (i == 0) {
                        System.out.println("1.  *** " + players.get(0).getplayerName() + " " + players.get(0).getPoints() + " ***");
                    } else {
                        System.out.println(i + 1 + ".  " + players.get(0).getplayerName() + " " + players.get(i).getPoints());
                    }
                }
                System.out.println("------------------");

                //Do you want to continue?
                System.out.println("\nStop playing? y/n");
                String stopOrContinue = scanner.nextLine().toLowerCase();
                if (stopOrContinue.equals("y")) {
                    stopGame = true;
                }
                else {
                    isThereAWinner = false;
                }
            }
            //System.out.println(board);

        } else {
            //Run computer code
        }
    }

    static boolean checkIfnumber(String input) {

        //Check if string is only digits
        if (!input.matches("\\d*")) {
            return false;
        } else {
            return true;
        }
    }
}