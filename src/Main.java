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
        int menuChoice = checkMenuChoice(scanner);


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
        int numberOfBoxes = checkInputIsANumber(scanner);

        //Play game
        //Take turns
        boolean isThereAWinner = false;
        boolean stopGame = false;
        final int LINES_PER_SQUARE = 8;

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
                    StringBuilder header = new StringBuilder();

                    //Create a nice header
                    header.append("-".repeat(LINES_PER_SQUARE * numberOfBoxes));
                    header.append("\n");
                    header.append(" ".repeat((numberOfBoxes * numberOfBoxes)-2));
                    header.append("Turn " + turn + "\n");
                    header.append(" ".repeat((numberOfBoxes * numberOfBoxes)-2));
                    header.append(player.getplayerName());
                    System.out.println(header);

                    //Print board
                    System.out.println(board);

                    //Check that it is possible to continue to play.
                    int numberOfBoxesLeft = board.checkNumberOfBoxesLeft();
                    if (numberOfBoxesLeft == 0) {
                        System.out.println("\nTied! Better luck next time guys.\n");
                        System.out.println(board);
                        isThereAWinner = true;
                        break;
                    }

                    if (!player.getplayerName().equals(nameComputer)) {
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
                turn++; //Increase turn variable
            }

            //Scoreboard
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
        System.out.println("Game over");
    }

    public static int checkMenuChoice(Scanner scanner) {

        while (true) {
            try {
                String inputFromUser = scanner.nextLine().trim();
                int menuChoice = Integer.parseInt(inputFromUser);

                if (menuChoice == 1 || menuChoice == 2) {
                    return menuChoice;
                } else {
                    System.out.println("Wrong selection, please select 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Wrong selection, please select 1 or 2.");
            }
        }
    }
    public static int checkInputIsANumber(Scanner scanner) {

        while (true) {
            try {
                String inputFromUser = scanner.nextLine().trim();
                int input = Integer.parseInt(inputFromUser);
                return input;
            } catch (Exception e) {
                System.out.println("Only numbers please, try again.");
            }
        }
    }
}