package shortestpath.domain;

import java.util.HashMap;
import shortestpath.util.BinaryHeap;

/**
 * Implementation of Dijkstra's algorithm.
 * 
 * @author Jake
 */
public class Dijkstra implements PathFinder {

    /**
     * Finds the shortest path from start to end using Dijkstra's algorithm.
     * 
     * @param graph graph used for searching
     * @param start starting point of the path
     * @param end end point of the path
     * @return shortest path from start to end
     */
    @Override
    public double search(Graph graph, Node start, Node end) {
        BinaryHeap<Edge> queue = new BinaryHeap<>();
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
                    queue.add(new Edge(next, newDistance));
                }
            }
        }
        return distance.getOrDefault(end, -1.0);
    }
}
