package shortestpath.domain;

import shortestpath.datastructures.Coordinate;

public interface PathFinder {

    public double search(Coordinate start, Coordinate end);
}
