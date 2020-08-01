package shortestpath.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Weighted graph. Can be used as bidirectional by adding edges in both
 * directions.
 *
 * @author Jake
 */
public class Graph {

    private final Map<Node, Set<Edge>> edges;

    public Graph() {
        this.edges = new HashMap<>();
    }

    /**
     * Adds a new edge from start node to end. Duplicate edges are discarded.
     *
     * @param start starting node
     * @param end end node
     * @param weight weight of the edge
     */
    public void addEdge(Node start, Node end, int weight) {
        if (!edges.containsKey(start)) {
            edges.put(start, new HashSet<>());
        }
        Set<Edge> neighbors = edges.get(start);
        Edge edge = new Edge(end, weight);
        neighbors.add(edge);
    }

    /**
     * Get all edges to neighboring nodes.
     *
     * @param node
     * @return Set of edges
     */
    public Set<Edge> getNeighbors(Node node) {
        return edges.getOrDefault(node, Collections.emptySet());
    }

    @Override
    public String toString() {
        String output = "";
        for (Node node : edges.keySet()) {
            output += node + " -> ";
            for (Edge edge : edges.get(node)) {
                Node end = edge.getEnd();
                output += end + " ";
            }
            output += "\n";
        }
        return output;
    }

}
