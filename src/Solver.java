import java.util.ArrayList;

public class Solver {

    public static void solve(PuzzleBoard initialBoard) {
        // ArrayList<PuzzleBoard> expandedNode = new ArrayList<PuzzleBoard>();
        ArrayList<Node> aliveNode = new ArrayList<>();
        Node currentNode;

        // Initialize root node with initial board
        currentNode = new Node(initialBoard);

        while (!currentNode.getBoard().isSolved()) {
            // Ambil node yang sudah di expand, dan masukkan ke dalam queue
            if (currentNode.getBoard().getEmptyRowIdx() > 0) {
                aliveNode.add(new Node(currentNode.getBoard().movedUp(), Move.UP, currentNode));
                currentNode.setNextUp(aliveNode.get(aliveNode.size() - 1));
            }
            if (currentNode.getBoard().getEmptyRowIdx() < 3) {
                aliveNode.add(new Node(currentNode.getBoard().movedDown(), Move.DOWN, currentNode));
                currentNode.setNextDown(aliveNode.get(aliveNode.size() - 1));
            }
            if (currentNode.getBoard().getEmptyColIdx() > 0) {
                aliveNode.add(new Node(currentNode.getBoard().movedLeft(), Move.LEFT, currentNode));
                currentNode.setNextLeft(aliveNode.get(aliveNode.size() - 1));
            }
            if (currentNode.getBoard().getEmptyColIdx() < 3) {
                aliveNode.add(new Node(currentNode.getBoard().movedRight(), Move.RIGHT, currentNode));
                currentNode.setNextRight(aliveNode.get(aliveNode.size() - 1));
            }
            System.out.println();

            // Bandingkan cost dari semua node dalam expanded
            // Board dengan cost paling kecil akan dijadikan current node
            int minCost = aliveNode.get(0).getCost();
            int selectedNodeIdx = 0;
            for (int i = 1; i < aliveNode.size(); i++) {
                if (aliveNode.get(i).getCost() < minCost) {
                    minCost = aliveNode.get(i).getCost();
                    selectedNodeIdx = i;
                }
            }

            // Set current node menjadi node yang memiliki cost terkecil
            currentNode = aliveNode.get(selectedNodeIdx);
            aliveNode.remove(selectedNodeIdx);

            // Print board + langkah
            System.out.print("Next move: ");
            System.out.println(currentNode.getMoveFromParent());
            currentNode.getBoard().printBoard();

        }

        // Sudah selesai
        System.out.println("\nSolved!");
        currentNode.getBoard().printBoard();
        System.out.println("Total moves: " + currentNode.getDepth());
        System.out.println("Total expanded nodes: " + aliveNode.size());

    }
}
