package shortestpath.datastructures;

/**
 * Represents a node for JPS.
 *
 * @author Jake
 */
public class Node implements Comparable<Node> {

    private final int x;
    private final int y;
    private boolean blocked;
    private boolean open;
    private double fScore;
    private double gScore;
    private Node parent;
    private boolean visited;

    /**
     * Creates a new node.
     *
     * @param x horizontal coordinate of the node
     * @param y vertical coordinate of the node
     * @param blocked true if node is blocked, otherwise false
     */
    public Node(int x, int y, boolean blocked) {
        this.x = x;
        this.y = y;
        this.blocked = blocked;
    }

    /**
     * Resets a node to its starting state.
     */
    public void reset() {
        open = false;
        fScore = 0;
        gScore = 0;
        parent = null;
        visited = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public double getFScore() {
        return fScore;
    }

    public void setFScore(double fScore) {
        this.fScore = fScore;
    }

    public double getGScore() {
        return gScore;
    }

    public void setGScore(double gScore) {
        this.gScore = gScore;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean visited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Node node = (Node) other;
        return this.x == node.x && this.y == node.y;
    }

    @Override
    public int compareTo(Node other) {
        return this.fScore > other.fScore ? 1 : -1;
    }

}
