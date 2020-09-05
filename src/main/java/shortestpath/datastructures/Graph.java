package shortestpath.datastructures;

/**
 * Weighted adjacency list graph.
 *
 * @author Jake
 */
public class Graph {

    private final List<Edge>[][] graph;
    private final int width;
    private final int height;

    /**
     * Initializes a new graph with width and height. All elements are null in
     * the beginning.
     *
     * @param width width of the graph
     * @param height height of the graph
     */
    public Graph(int width, int height) {
        this.graph = new List[width][height];
        this.width = width;
        this.height = height;
    }

    /**
     * Adds a new edge from start node to end. Duplicate edges are discarded.
     *
     * @param start starting node
     * @param end end node
     * @param cost cost of the edge
     */
    public void addEdge(Coordinate start, Coordinate end, double cost) {
        int x = start.getX();
        int y = start.getY();
        List<Edge> neighbours = graph[x][y];
        if (neighbours == null) {
            neighbours = new List<Edge>();
            graph[x][y] = neighbours;
        }
        neighbours.add(new Edge(end, cost));
    }

    /**
     * Get all edges to neighboring nodes.
     *
     * @param node node whose neighbours are returned
     * @return list of edges
     */
    public List<Edge> getNeighbors(Coordinate node) {
        List<Edge> neighbours = graph[node.getX()][node.getY()];
        return neighbours == null ? new List<>() : neighbours;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
