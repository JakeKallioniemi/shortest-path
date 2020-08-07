package shortestpath.util;

import java.util.List;
import shortestpath.domain.Graph;
import shortestpath.domain.Node;

/**
 * Parses the contents of a .map file.
 * 
 * @author Jake
 */
public class Parser {

    private static final int MAP_START_OFFSET = 4;
    private static final double diagonalCost = 1.4142135623730950488016887242097;

    /**
     * Builds a graph from the contents of a .map file
     * 
     * @param grid contents of a .map file
     * @return graph
     */
    public static Graph buildGraph(List<String> grid) {
        int height = getValue(grid.get(1), 7);
        int width = getValue(grid.get(2), 6);
        Graph graph = new Graph();

        for (int i = MAP_START_OFFSET; i < height + MAP_START_OFFSET; i++) {
            int y = i - MAP_START_OFFSET;
            for (int x = 0; x < width; x++) {
                char startChar = getCharAt(grid, x, y);
                if (startChar == 'T') {
                    continue;
                }
                for (Direction direction : Direction.values()) {
                    int endX = x + direction.xDirection;
                    int endY = y + direction.yDirection;
                    if (endX < 0 || endX >= width || endY < 0 || endY >= height) {
                        continue;
                    }
                    char endChar = getCharAt(grid, endX, endY);
                    if (endChar == 'T' || endChar == '@') {
                        continue;
                    }
                    Node start = new Node(x, y);
                    Node end = new Node(endX, endY);
                    double cost = 1;
                    if (direction.isDiagonal) {
                        cost = diagonalCost;
                    }
                    graph.addEdge(start, end, cost);
                    graph.addEdge(end, start, cost);
                }
            }
        }
        return graph;
    }

    private static int getValue(String line, int index) {
        String substring = line.substring(index, line.length());
        return Integer.parseInt(substring);
    }

    private static char getCharAt(List<String> grid, int x, int y) {
        return grid.get(y + MAP_START_OFFSET).charAt(x);
    }
    
    enum Direction {
        UP(0, -1, false),
        DOWN(0, 1, false),
        LEFT(-1, 0, false),
        RIGHT(1, 0, false),
        TOP_RIGHT(1, -1, true),
        DOWN_RIGHT(1, 1, true),
        DOWN_LEFT(-1, 1, true),
        TOP_LEFT(-1, -1, true);
        
        
        private int xDirection;
        private int yDirection;
        private boolean isDiagonal;

        private Direction(int xDirection, int yDirection, boolean isDiagonal) {
            this.xDirection = xDirection;
            this.yDirection = yDirection;
            this.isDiagonal = isDiagonal;
        }

        public int getxDirection() {
            return xDirection;
        }

        public int getyDirection() {
            return yDirection;
        }

        public boolean isIsDiagonal() {
            return isDiagonal;
        }   
        
    }
}
