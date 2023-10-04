import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    //Welcome to TicTacToe, start with setup

        System.out.println("Welcome to Tic Tac Toe! How do you want to play?");
        System.out.println("1. 1 vs. 1");
        System.out.println("2. You vs. Computer");

        Scanner scanner = new Scanner(System.in);
        int menuChoice = scanner.nextInt();

        if (menuChoice == 1){
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

            //Create board
            Board board = new Board(numberOfBoxes);

            //Play game
            //Take turns. Player 1 starts. Loop.
            boolean isThereAWinner = false;
            while(!isThereAWinner){


                for (Player player : players) {
                    System.out.println(board);

                    System.out.print(player.getplayerName() + ", select a box, by number: ");
                    int markThisBox = scanner.nextInt();

                    board.setBoard(markThisBox, player);
                }

                isThereAWinner = board.checkWinner();
            }

            System.out.println(board);

        }
        else {
            //Run computer
        }
    }
}