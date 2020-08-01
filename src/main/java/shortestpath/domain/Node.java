package shortestpath.domain;

/**
 * Represents a node with x and y coordinates.
 * 
 * @author Jake
 */
public class Node {

    private final int x;
    private final int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + x;
        hash = 41 * hash + y;
        return hash;
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
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
}
