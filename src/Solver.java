import java.util.Comparator;
import java.util.PriorityQueue;

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getCost(), o2.getCost());
    }
}

public class Solver {

    public static void solve(PuzzleBoard initialBoard) {
        // ArrayList<PuzzleBoard> expandedNode = new ArrayList<PuzzleBoard>();
        PriorityQueue<Node> aliveNode = new PriorityQueue<>(new NodeComparator());
        Node currentNode;

        // Initialize root node with initial board
        currentNode = new Node(initialBoard);

        while (!currentNode.getBoard().isSolved()) {
            // Ambil node yang sudah di expand, dan masukkan ke dalam queue
            if (currentNode.getBoard().getEmptyRowIdx() > 0) {
                aliveNode.add(new Node(currentNode.getBoard().movedUp(), Move.UP, currentNode));
                // currentNode.setNextUp(aliveNode.get(aliveNode.size() - 1));
            }
            if (currentNode.getBoard().getEmptyRowIdx() < 3) {
                aliveNode.add(new Node(currentNode.getBoard().movedDown(), Move.DOWN, currentNode));
                // currentNode.setNextDown(aliveNode.get(aliveNode.size() - 1));
            }
            if (currentNode.getBoard().getEmptyColIdx() > 0) {
                aliveNode.add(new Node(currentNode.getBoard().movedLeft(), Move.LEFT, currentNode));
                // currentNode.setNextLeft(aliveNode.get(aliveNode.size() - 1));
            }
            if (currentNode.getBoard().getEmptyColIdx() < 3) {
                aliveNode.add(new Node(currentNode.getBoard().movedRight(), Move.RIGHT, currentNode));
                // currentNode.setNextRight(aliveNode.get(aliveNode.size() - 1));
            }
            System.out.println();

            currentNode = aliveNode.poll();
            // currentNode.getBoard().printBoard();
            // System.out.println("Move: " + currentNode.getMoveFromParent());
            // System.out.println("Cost: " + currentNode.getCost());

            // Print board + langkah
            assert currentNode != null;
            System.out.println("Next move: " + currentNode.getMoveFromParent());
            currentNode.getBoard().printBoard();

        }

        // Sudah selesai
        System.out.println("\nSolved!");
        currentNode.getBoard().printBoard();
        System.out.println("Total moves: " + currentNode.getDepth());
        System.out.println("Total expanded nodes: " + aliveNode.size());

    }
}
