package shortestpath.util;

import java.util.List;
import shortestpath.benchmark.Scenario;
import shortestpath.benchmark.Test;
import shortestpath.domain.Graph;
import shortestpath.domain.Node;
import shortestpath.domain.PathFinder;

/**
 * Parses the contents of a .map and .map.scen files.
 *
 * @author Jake
 */
public class Parser {

    private static final int MAP_START_OFFSET = 4;

    /**
     * Builds a graph from the contents of a .map file
     *
     * @param data contents of a .map file
     * @return graph
     */
    public static Graph buildGraph(List<String> data) {
        int height = getValue(data.get(1), 7);
        int width = getValue(data.get(2), 6);
        Graph graph = new Graph();

        for (int i = MAP_START_OFFSET; i < height + MAP_START_OFFSET; i++) {
            int y = i - MAP_START_OFFSET;
            for (int x = 0; x < width; x++) {
                char startChar = getCharAt(data, x, y);
                if (startChar != '.') {
                    continue;
                }
                for (Direction direction : Direction.values()) {
                    int endX = x + direction.xDirection;
                    int endY = y + direction.yDirection;
                    if (endX < 0 || endX >= width || endY < 0 || endY >= height) {
                        continue;
                    }
                    char endChar = getCharAt(data, endX, endY);
                    if (endChar != '.') {
                        continue;
                    }
                    Node start = new Node(x, y);
                    Node end = new Node(endX, endY);
                    double cost = 1;
                    if (direction.diagonal) {
                        char a = getCharAt(data, endX, y);
                        char b = getCharAt(data, x, endY);
                        // Makes sure we are not diagonally cutting trough walls
                        if (a != '.' || b != '.') {
                            continue;
                        }
                        // Using square root of two for cost of diagonal movement
                        cost = MathUtil.SQRT_OF_TWO;
                    }
                    graph.addEdge(start, end, cost);
                    graph.addEdge(end, start, cost);
                }
            }
        }
        return graph;
    }

    /**
     * Builds a scenario for benchmarking from contents of .map.scen file
     *
     * @param scenarioData contents of a .map.scen file
     * @param mapData contents of a .map file
     * @return scenario that can be used to run benchmarks
     */
    public static Scenario buildScenario(List<String> scenarioData,
            List<String> mapData, PathFinder pathFinder) {
        
        if (scenarioData.size() < 2) {
            return null;
        }
        String mapName = scenarioData.get(1).split("\t")[1];
        Scenario scenario = new Scenario(mapName, mapData, pathFinder);

        for (int i = 1; i < scenarioData.size(); i++) {
            String[] values = scenarioData.get(i).split("\t");
            int startX = Integer.parseInt(values[4]);
            int startY = Integer.parseInt(values[5]);
            int endX = Integer.parseInt(values[6]);
            int endY = Integer.parseInt(values[7]);
            double optimalLength = Double.parseDouble(values[8]);
            Node start = new Node(startX, startY);
            Node end = new Node(endX, endY);
            Test test = new Test(start, end, optimalLength);
            scenario.addTest(test);
        }
        return scenario;
    }

    private static int getValue(String line, int index) {
        String substring = line.substring(index, line.length());
        return Integer.parseInt(substring);
    }

    private static char getCharAt(List<String> grid, int x, int y) {
        return grid.get(y + MAP_START_OFFSET).charAt(x);
    }

    private enum Direction {
        UP(0, -1, false),
        DOWN(0, 1, false),
        LEFT(-1, 0, false),
        RIGHT(1, 0, false),
        UP_RIGHT(1, -1, true),
        DOWN_RIGHT(1, 1, true),
        DOWN_LEFT(-1, 1, true),
        UP_LEFT(-1, -1, true);

        private int xDirection;
        private int yDirection;
        private boolean diagonal;

        private Direction(int xDirection, int yDirection, boolean diagonal) {
            this.xDirection = xDirection;
            this.yDirection = yDirection;
            this.diagonal = diagonal;
        }

        public int getxDirection() {
            return xDirection;
        }

        public int getyDirection() {
            return yDirection;
        }

        public boolean isDiagonal() {
            return diagonal;
        }

    }
}
