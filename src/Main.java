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
        while(!input.equals("1") && !input.equals("2")) {
            System.out.println("Invalid input.");
            System.out.print("> ");
            input = scanner.nextLine();
        }
        if (input.equals("1")) {
            // Generate random puzzle
            board = new PuzzleBoard();
        } else {
            // Import puzzle
            System.out.print("Enter file name (in /test folder, with file extension): ");
            input = scanner.nextLine();
            board = new PuzzleBoard(input);
        }

        if(board.getValueString(0,0) != null){
            // Cetak board
            board.printBoard();

            // Cetak Kurang(i), x, dan isSolvable
            System.out.println("\nNilai Kurang(i)");
            for(int i = 1; i < 16; i+=2) {
                System.out.print("Kurang(" + i + ") = " + board.Kurang(i));
                System.out.println("\tKurang(" + (i+1) + ") = " + board.Kurang(i+1));
            }
            System.out.println("x = " + board.x());
            System.out.printf("ΣKurang + x = %d\n", board.SigmaKurang() + board.x());

            // Solve puzzle
            if (board.isSolvable()) {
                System.out.println("Puzzle Solvable.\n");
                board.solve();
            } else {
                System.out.println("Puzzle not solvable.");
            }
        }
    }
}
