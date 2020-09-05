package shortestpath.benchmark;

import shortestpath.datastructures.Coordinate;

/**
 * Holds information required to run a single test.
 *
 * @author Jake
 */
public class Test {

    private final Coordinate start;
    private final Coordinate end;
    private final double optimalLength;
    private double length;

    /**
     * Defines a test run.
     * 
     * @param start start point of the path finding
     * @param end end point of the path finding
     * @param optimalLength  shortest possible path from start to end
     */
    public Test(Coordinate start, Coordinate end, double optimalLength) {
        this.start = start;
        this.end = end;
        this.optimalLength = optimalLength;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
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
    
}
