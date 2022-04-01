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
        PriorityQueue<Node> aliveNode = new PriorityQueue<>(new NodeComparator());
        Node currentNode;

        // Initialize root node with initial board
        currentNode = new Node(initialBoard);

        long startTime = System.currentTimeMillis();

        // Selagi currentNode belum mencapai goal state
        while (!Objects.requireNonNull(currentNode).getBoard().isSolved()) {
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
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Sudah selesai
        System.out.println("Solved!");
        System.out.println("Total moves: " + currentNode.getDepth());
        System.out.println("Total expanded nodes: " + aliveNode.size());
        System.out.println("Time spent: " + duration + " ms\n");

        // Print solution
        ArrayList<Node> solution = new ArrayList<>();
        while (currentNode.getParent() != null) {
            solution.add(0, currentNode);
            currentNode = currentNode.getParent();
        }
        solution.add(0, currentNode);
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


    }
}
