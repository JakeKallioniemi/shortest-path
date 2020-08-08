package shortestpath.domain;

import java.util.HashMap;
import java.util.PriorityQueue;
import shortestpath.util.MathUtil;

/**
 * Implementation of A* using Euclidean distance as heuristic.
 *
 * @author Jake
 */
public class AStar implements PathFinder {

    /**
     * Finds the shortest path from start to end using A*.
     *
     * @param graph graph used for searching
     * @param start starting point of the path
     * @param end end point of the path
     * @return shortest path from start to end
     */
    @Override
    public double search(Graph graph, Node start, Node end) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(10);
        HashMap<Node, Boolean> visited = new HashMap<>();
        HashMap<Node, Double> distance = new HashMap<>();

        distance.put(start, 0.0);
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll().getEnd();
            if (current.equals(end)) {
                break;
            }
            if (visited.getOrDefault(current, false)) {
                continue;
            }
            visited.put(current, true);
            for (Edge edge : graph.getNeighbors(current)) {
                Node next = edge.getEnd();
                double currentDistance = distance.getOrDefault(next, 10000.0);
                double newDistance = distance.get(current) + edge.getCost();
                if (newDistance < currentDistance) {
                    distance.put(next, newDistance);
                    queue.add(new Edge(next, newDistance + heuristic(end, next)));
                }
            }
        }
        return distance.getOrDefault(end, -1.0);
    }

    private double heuristic(Node a, Node b) {
        // Euclidean distance
        double ac = MathUtil.abs(a.getY() - b.getY());
        double cb = MathUtil.abs(a.getX() - b.getX());
        return MathUtil.hypot(ac, cb);
    }
}
