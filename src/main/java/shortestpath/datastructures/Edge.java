package shortestpath.datastructures;

/**
 * Represents a graphs edge with endpoint and cost. Used in adjacency list.
 *
 * @author Jake
 */
public class Edge implements Comparable<Edge> {

    private final Coordinate end;
    private final double cost;

    public Edge(Coordinate end, double cost) {
        this.end = end;
        this.cost = cost;
    }

    public Coordinate getEnd() {
        return end;
    }

    public double getCost() {
        return cost;
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
