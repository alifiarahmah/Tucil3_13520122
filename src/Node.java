enum Move { LEFT, RIGHT, UP, DOWN }

class Node {
    private static int idCounter = 1;
    private int id;
    private PuzzleBoard board;
    private int cost;
    private int depth;
    private Move moveFromParent;
    private Node parent;
    private Node nextUp;
    private Node nextDown;
    private Node nextLeft;
    private Node nextRight;

    // Konstruktor
    public Node(PuzzleBoard board) {
        // konstruktor root node
        this.board = board;
        this.cost = 0;
        this.depth = 0;
        this.moveFromParent = null;
        this.parent = null;
        this.nextUp = null;
        this.nextDown = null;
        this.nextLeft = null;
        this.nextRight = null;
        this.id = idCounter++;
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
        this.parent = null;
        this.nextUp = null;
        this.nextDown = null;
        this.nextLeft = null;
        this.nextRight = null;
        this.id = idCounter++;
    }

    // Getter
    public int getId() {
        return this.id;
    }
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
    public Node getNextUp() {
        return nextUp;
    }
    public Node getNextDown() {
        return nextDown;
    }
    public Node getNextLeft() {
        return nextLeft;
    }
    public Node getNextRight() {
        return nextRight;
    }

    // Setter
    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setMoveFromParent(Move moveFromParent) {
        this.moveFromParent = moveFromParent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setNextUp(Node nextUp) {
        this.nextUp = nextUp;
    }
    public void setNextDown(Node nextDown) {
        this.nextDown = nextDown;
    }
    public void setNextLeft(Node nextLeft) {
        this.nextLeft = nextLeft;
    }
    public void setNextRight(Node nextRight) {
        this.nextRight = nextRight;
    }
}