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
        // Cetak Kurang(i)
        System.out.println("\nNilai Kurang(i)");
        for(int i = 1; i < 16; i++) {
            int[] loc = board.getValueIdx(i);
            System.out.printf("%d %d\n", loc[0], loc[1]);
            // System.out.printf("Kurang(%d): %d\n", i, board.Kurang(loc[0], loc[1]));
        }
        // Cetak total isSolvable
        System.out.printf("\nΣKurang + x = %d\n", board.SigmaKurang() + board.x());

        // Solve puzzle
        long startTime = System.currentTimeMillis();
        if (board.isSolvable()) {
            System.out.println("Puzzle Solvable.");
            board.solve();
        } else {
            System.out.println("Puzzle not solvable.");
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time Spent: " + duration + "ms");
    }
}
