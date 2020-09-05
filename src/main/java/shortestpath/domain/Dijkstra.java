package shortestpath.domain;

import shortestpath.datastructures.BinaryHeap;
import shortestpath.datastructures.Coordinate;
import shortestpath.datastructures.Edge;
import shortestpath.datastructures.Graph;
import shortestpath.datastructures.List;

/**
 * Implementation of Dijkstra's algorithm.
 *
 * @author Jake
 */
public class Dijkstra implements PathFinder {

    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    /**
     * Finds the shortest path from start to end using Dijkstra's algorithm.
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

                double currentDistance = distance[next.getX()][next.getY()];
                if (currentDistance == 0) {
                    currentDistance = 10000;
                }
                double newDistance = distance[current.getX()][current.getY()] + edge.getCost();
                if (newDistance < currentDistance) {
                    distance[next.getX()][next.getY()] = newDistance;
                    queue.add(new Edge(next, newDistance));
                }
            }
        }
        return -1;
    }
}
