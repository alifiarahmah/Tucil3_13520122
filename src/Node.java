enum Move { LEFT, RIGHT, UP, DOWN }

class Node {
    private static int idCounter = 1;
    private final PuzzleBoard board;
    private final int cost;
    private final int depth;
    private final Move moveFromParent;
    private final Node parent;

    // Konstruktor
    public Node(PuzzleBoard board) {
        // konstruktor root node
        this.board = board;
        this.cost = 0;
        this.depth = 0;
        this.moveFromParent = null;
        this.parent = null;
    }
    public Node(PuzzleBoard board, Move moveFromParent, Node parent) {
        this.board = board;
        // c(i) = f(i) + g(i)
        // c(i) -> cost
        // f(i) -> cost from start to this node (cost parent) --> asumsi cost = depth parent
        // g(i) -> countNotInPlace
        this.cost = parent.getDepth() + board.countNotInPlace();
        this.depth = parent.getDepth() + 1;
        this.moveFromParent = moveFromParent;
        this.parent = parent;
    }

    // Getter
    public PuzzleBoard getBoard() {
        return board;
    }
    public int getCost() {
        return cost;
    }
    public int getDepth() {
        return depth;
    }
    public Move getMoveFromParent() {
        return moveFromParent;
    }
    public Node getParent() {
        return parent;
    }
}