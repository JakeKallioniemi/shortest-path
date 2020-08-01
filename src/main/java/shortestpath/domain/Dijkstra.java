package shortestpath.domain;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Implementation of Dijkstra's algorithm.
 * 
 * @author Jake
 */
public class Dijkstra {

    /**
     * Finds the shortest path from start to end using Dijkstra's algorithm.
     * 
     * @param graph graph used for searching
     * @param start starting point of the path
     * @param end end point of the path
     * @return shortest path from start to end
     */
    public int search(Graph graph, Node start, Node end) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(10);
        HashMap<Node, Boolean> visited = new HashMap<>();
        HashMap<Node, Integer> distance = new HashMap<>();
        
        distance.put(start, 0);
        queue.add(new Edge(start, 0));
        
        while (!queue.isEmpty()) {
            Node node = queue.poll().getEnd();
            if (visited.getOrDefault(node, false)) {
                continue;
            }
            visited.put(node, true);
            for (Edge edge : graph.getNeighbors(node)) {
                int currentDistance = distance.getOrDefault(edge.getEnd(), 10000);
                int newDistance = distance.get(node) + edge.getWeight();
                if (newDistance < currentDistance) {
                    distance.put(edge.getEnd(), newDistance);
                    queue.add(new Edge(edge.getEnd(), newDistance));
                }
            }
        }
        return distance.getOrDefault(end, -1);
    }
}
