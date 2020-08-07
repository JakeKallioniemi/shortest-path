package shortestpath.benchmark;

import shortestpath.domain.Node;

/**
 * Holds information required to run a single test.
 *
 * @author Jake
 */
public class Test {

    private final Node start;
    private final Node end;
    private final double optimalLength;
    private double length;

    /**
     * Defines a test run.
     * 
     * @param start start point of the path finding
     * @param end end point of the path finding
     * @param optimalLength  shortest possible path from start to end
     */
    public Test(Node start, Node end, double optimalLength) {
        this.start = start;
        this.end = end;
        this.optimalLength = optimalLength;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public double getOptimalLength() {
        return optimalLength;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return start + " -> " + end + " expected: " + optimalLength
                + " actual: " + length;
    }
}
