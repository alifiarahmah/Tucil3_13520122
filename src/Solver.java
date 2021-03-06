import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getCost(), o2.getCost());
    }
}

public class Solver {

    public static void solve(PuzzleBoard initialBoard) {
        ArrayList<Node> solution = getSolution(initialBoard);
        if (solution.size() > 0) {
            printSolution(solution);
        }
    }

    public static ArrayList<Node> getSolution(PuzzleBoard initialBoard) {
        PriorityQueue<Node> aliveNode = new PriorityQueue<>(new NodeComparator()); // Queue semua hasil pembangkian
        ArrayList<Node> solution = new ArrayList<>();
        Node currentNode; // Node yang sedang dibangkitkan

        // Initialize root node with initial board
        currentNode = new Node(initialBoard);

        // Mulai timer
        long startTime = System.currentTimeMillis();

        // Selagi currentNode belum mencapai goal state dan belum sampai 5 detik
        while (!Objects.requireNonNull(currentNode).getBoard().isSolved()
                && System.currentTimeMillis() - startTime < 5000) {
            // Ambil node yang sudah di expand, dan masukkan ke dalam queue
            if (currentNode.getBoard().getEmptyRowIdx() > 0) {
                aliveNode.add(new Node(currentNode.getBoard().movedUp(), Move.UP, currentNode));
            }
            if (currentNode.getBoard().getEmptyRowIdx() < 3) {
                aliveNode.add(new Node(currentNode.getBoard().movedDown(), Move.DOWN, currentNode));
            }
            if (currentNode.getBoard().getEmptyColIdx() > 0) {
                aliveNode.add(new Node(currentNode.getBoard().movedLeft(), Move.LEFT, currentNode));
            }
            if (currentNode.getBoard().getEmptyColIdx() < 3) {
                aliveNode.add(new Node(currentNode.getBoard().movedRight(), Move.RIGHT, currentNode));
            }
            currentNode = aliveNode.poll();
        }

        if (Objects.requireNonNull(currentNode).getBoard().isSolved()) {
            // Hitung durasi
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // Output selesai
            System.out.println("Solved!");
            System.out.println("Total moves: " + currentNode.getDepth());
            System.out.println("Total expanded nodes: " + aliveNode.size());
            System.out.println("Time spent: " + duration + " ms\n");

            // Masukkan langkah solusi terurut ke dalam array solusi
            while (currentNode.getParent() != null) {
                solution.add(0, currentNode);
                currentNode = currentNode.getParent();
            }
            solution.add(0, currentNode);

        } else {
            System.out.println("Puzzle solving has taken a very long time (>5 s). Stop.");
        }
        return solution;
    }

    public static void printSolution(ArrayList<Node> solution) {
        System.out.println("Initial board: ");
        solution.get(0).getBoard().printBoard();
        System.out.println();
        for(int i = 1; i < solution.size(); i++) {
            System.out.println(i + ". Move: " + solution.get(i).getMoveFromParent());
            solution.get(i).getBoard().printBoard();
            System.out.println();
        }
        System.out.print("Moves: ");
        for(int i = 1; i < solution.size(); i++) {
            System.out.print(solution.get(i).getMoveFromParent());
            if(i != solution.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
