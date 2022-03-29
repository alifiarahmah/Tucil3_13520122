import java.util.Scanner;

public class Main {
    public static void mainMenu(){
        System.out.println("15-Puzzle Solver");
        System.out.println("[1] Generate random puzzle");
        System.out.println("[2] Import puzzle (.txt in /test directory)");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        FifteenPuzzle board;
        if (input.equals("1")) {
            // Generate random puzzle
            board = new FifteenPuzzle();
        } else {
            // Import puzzle
            System.out.print("Enter file name: ");
            input = scanner.nextLine();
            board = new FifteenPuzzle(input);
        }
    }

    public static void main(String[] args) {
        FifteenPuzzle board = new FifteenPuzzle("tc1.txt");
        board.printBoard();
        if (board.isSolvable()) {
            System.out.println("Solvable");

            // Solve puzzle
            // ? Buat export ke file biar niat banget kalo ada waktu
        } else {
            System.out.println("Not solvable");
        }
    }
}
