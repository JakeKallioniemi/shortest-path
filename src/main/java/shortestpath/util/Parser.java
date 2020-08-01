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
    private static final int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

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
                for (int[] direction : directions) {
                    int endX = x + direction[0];
                    int endY = y + direction[1];
                    if (endX < 0 || endX >= width || endY < 0 || endY >= height) {
                        continue;
                    }
                    char endChar = getCharAt(grid, endX, endY);
                    if (endChar == 'T') {
                        continue;
                    }
                    Node start = new Node(x, y);
                    Node end = new Node(endX, endY);
                    graph.addEdge(start, end, 1);
                    graph.addEdge(end, start, 1);
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

}
