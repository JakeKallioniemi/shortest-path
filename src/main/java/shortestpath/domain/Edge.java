package shortestpath.domain;

import java.util.Objects;

/**
 * Represents a graphs edge with endpoint and cost.
 * 
 * @author Jake
 */
public class Edge implements Comparable<Edge> {

    private final Node end;
    private final double cost;

    public Edge(Node end, double cost) {
        this.end = end;
        this.cost = cost;
    }

    public Node getEnd() {
        return end;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.end);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.cost) ^ (Double.doubleToLongBits(this.cost) >>> 32));
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
        final Edge edge = (Edge) other;
        return this.end.equals(edge.end) && this.cost == edge.cost;
    }

    @Override
    public int compareTo(Edge other) {
        return this.cost > other.cost ? 1 : -1;
    }   

}
