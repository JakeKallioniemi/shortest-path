package shortestpath.domain;

/**
 * Represents a graphs edge with endpoint and weight.
 * 
 * @author Jake
 */
public class Edge implements Comparable<Edge> {

    private final Node end;
    private final int weight;

    public Edge(Node end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public Node getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + end.hashCode();
        hash = 41 * hash + weight;
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
        return this.end.equals(edge.end) && this.weight == edge.weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }

}
