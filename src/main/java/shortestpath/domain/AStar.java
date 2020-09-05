package shortestpath.domain;

import shortestpath.datastructures.BinaryHeap;
import shortestpath.datastructures.Coordinate;
import shortestpath.datastructures.Edge;
import shortestpath.datastructures.Graph;
import shortestpath.datastructures.List;
import shortestpath.util.MathUtil;

/**
 * Implementation of A* using octile distance as heuristic.
 *
 * @author Jake
 */
public class AStar implements PathFinder {

    private Graph graph;

    public AStar(Graph graph) {
        this.graph = graph;
    }

    /**
     * Finds the shortest path from start to end using A*.
     *
     * @param start starting point of the path
     * @param end end point of the path
     * @return shortest path from start to end
     */
    @Override
    public double search(Coordinate start, Coordinate end) {
        BinaryHeap<Edge> queue = new BinaryHeap<>();
        boolean[][] visited = new boolean[graph.getWidth()][graph.getHeight()];
        double[][] distance = new double[graph.getWidth()][graph.getHeight()];

        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll().getEnd();
            if (current.equals(end)) {
                return distance[end.getX()][end.getY()];
            }
            if (visited[current.getX()][current.getY()]) {
                continue;
            }
            visited[current.getX()][current.getY()] = true;
            List<Edge> neighbors = graph.getNeighbors(current);
            for (int i = 0; i < neighbors.size(); i++) {
                Edge edge = neighbors.get(i);
                Coordinate next = edge.getEnd();

                double gScore = distance[next.getX()][next.getY()];
                if (gScore == 0) {
                    gScore = 10000;
                }
                double tentativeGScore = distance[current.getX()][current.getY()] + edge.getCost();
                if (tentativeGScore < gScore) {
                    distance[next.getX()][next.getY()] = tentativeGScore;
                    queue.add(new Edge(next, tentativeGScore + heuristic(next, end)));
                }
            }
        }
        return -1;
    }

    private double heuristic(Coordinate a, Coordinate b) {
        // Octile distance
        int dx = MathUtil.abs(a.getX() - b.getX());
        int dy = MathUtil.abs(a.getY() - b.getY());
        return (dx + dy) + (MathUtil.SQRT_OF_TWO - 2) * MathUtil.min(dx, dy);
    }
}
