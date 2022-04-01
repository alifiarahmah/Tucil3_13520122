import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Main menu
        System.out.println("===========================");
        System.out.println("     15-Puzzle Solver      ");
        System.out.println("===========================");
        System.out.println("[1] Generate random puzzle");
        System.out.println("[2] Import puzzle from /test directory");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        PuzzleBoard board;
        if (input.equals("1")) {
            // Generate random puzzle
            board = new PuzzleBoard();
        } else {
            // Import puzzle
            System.out.print("Enter file name (with extension): ");
            input = scanner.nextLine();
            board = new PuzzleBoard(input);
        }

        // Cetak board
        board.printBoard();

        // Cetak Kurang(i), x, dan isSolvable
        System.out.println("\nNilai Kurang(i)");
        for(int i = 1; i < 16; i+=2) {
            System.out.print("Kurang(" + i + ") = " + board.Kurang(i));
            System.out.println("\tKurang(" + (i+1) + ") = " + board.Kurang(i+1));
        }
        System.out.println("x = " + board.x());
        // Cetak total isSolvable
        System.out.printf("ΣKurang + x = %d", board.SigmaKurang() + board.x());

        System.out.println();

        // Solve puzzle
        if (board.isSolvable()) {

            System.out.println("Puzzle Solvable.\n");
            board.solve();
        } else {
            System.out.println("Puzzle not solvable.");
        }
    }
}
