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
        int menuChoice = Integer.parseInt(scanner.nextLine());

        //Store players in a players list
        ArrayList<Player> players = new ArrayList<>();

        //Create players
        System.out.print("Name player 1: ");
        String namePlayerOne = scanner.nextLine();

        Player playerOne = new Player(namePlayerOne, "X");

        //Add playerone to the players list
        players.add(playerOne);

        String nameComputer = "Super Good Computer Player";

        if (menuChoice == 1) {
            //If 1.vs.1 is selected, add second player.
            System.out.print("Name player 2: ");
            String namePlayerTwo = scanner.nextLine();

            //Create and add player 2.
            Player playerTwo = new Player(namePlayerTwo, "O");
            players.add(playerTwo);

        } else {
            //Create computer player.

            Player playerTwo = new Computer(nameComputer, "O");
            players.add(playerTwo);
        }

        //How large battlefield?
        System.out.println("How many large gameboard do you want? 3, 4 or 5 (or more if you want..)?");
        int numberOfBoxes = Integer.parseInt(scanner.nextLine());

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
            int turn = 1;
            while (!isThereAWinner) {

                for (Player player : players) {

                    //Print board prior to every turn
                    //Print turn as well
                    System.out.println("Turn " + turn + "\n");
                    System.out.println(board);

                    //Check that it is possible to continue to play.
                    int numberOfBoxesLeft = board.checkNumberOfBoxesLeft();
                    if (numberOfBoxesLeft == 0) {
                        System.out.println("\nTied! Better luck next time guys.");
                        System.out.println(board);
                        isThereAWinner = true;
                        break;
                    }

                    if (!player.getplayerName().equals(nameComputer)) {
                        System.out.println(nameComputer + " already made a move, now it's your turn ");
                        System.out.print(player.getplayerName() + ", select a box by number: ");
                    }
                    //Make move
                    int markThisBox = player.makeMove(board.getBoard());

                    //Motivation
                    player.talk();

                    //Update board
                    board.setBoard(markThisBox, player);

                    //Check if there's a winner
                    isThereAWinner = board.checkWinner();

                    if (isThereAWinner) {
                        System.out.println(board);
                        System.out.println("\nWe have a winner! Congratulations " + player.getplayerName() + "!");
                        player.setPoints();
                        break;
                    }
                }
                turn++;
            }

            //Sort arraylist from highest to lowest using comparator and collections.
            Comparator<Player> compareByPoints = (Player p1, Player p2) -> p1.getPoints().compareTo(p2.getPoints());

            //reverse the order of the list so the highest point is first
            Collections.sort(players, Collections.reverseOrder(compareByPoints));

            //Print scoreboard with highlighted number 1
            System.out.println("--- Scoreboard ---");
            for (int i = 0; i < players.size(); i++) {
                if (i == 0) {
                    System.out.println("1.  *** " + players.get(0).getplayerName() + " " + players.get(0).getPoints() + " ***");
                } else {
                    System.out.println(i + 1 + ".  " + players.get(i).getplayerName() + " " + players.get(i).getPoints());
                }
            }
            System.out.println("------------------");

            //Do you want to continue?
            System.out.println("\nStop playing? y/n");
            String stopOrContinue = scanner.nextLine().toLowerCase();
            if (stopOrContinue.equals("y")) {
                stopGame = true;
            } else {
                isThereAWinner = false;
            }
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