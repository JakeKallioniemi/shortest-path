package shortestpath.datastructures;

/**
 * Two dimensional grid map.
 *
 * @author Jake
 */
public class Grid {

    private final int width;
    private final int height;
    private final Node[][] nodes;

    /**
     * Initializes a new grid with width and height. All elements are null in
     * the beginning.
     *
     * @param width width of the graph
     * @param height height of the graph
     */
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.nodes = new Node[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
 
    /**
     * Adds a new node.
     *
     * @param x horizontal coordinate of the node
     * @param y vertical coordinate of the node
     * @param blocked is the node blocked or not
     */
    public void addNode(int x, int y, boolean blocked) {
        nodes[x][y] = new Node(x, y, blocked);
    }

    /**
     * Gets a node from the grid. Coordinates outside of the grids bounds will
     * return null
     *
     * @param x horizontal coordinate of the node
     * @param y vertical coordinate of the node
     * @return the node or null if out of bounds
     */
    public Node getNode(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return null;
        }
        return nodes[x][y];
    }

    /**
     * Checks if it is possible to travel to node or not. Nodes out of bounds or
     * blocked will return false.
     *
     * @param x horizontal coordinate of the node
     * @param y vertical coordinate of the node
     * @return true if traversable or false if not
     */
    public boolean traversable(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }
        return !nodes[x][y].isBlocked();
    }

    /**
     * Resets all nodes in the grid to their starting state.
     */
    public void resetNodes() {
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[0].length; y++) {
                nodes[x][y].reset();;
            }
        }
    }
}
