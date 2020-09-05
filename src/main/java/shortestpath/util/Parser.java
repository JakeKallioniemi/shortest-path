package shortestpath.util;

import shortestpath.benchmark.Scenario;
import shortestpath.benchmark.Test;
import shortestpath.datastructures.Coordinate;
import shortestpath.datastructures.Graph;
import shortestpath.datastructures.Grid;
import shortestpath.datastructures.List;
import shortestpath.domain.PathFinder;

/**
 * Parses the contents of a .map and .map.scen files.
 *
 * @author Jake
 */
public class Parser {

    private static final int MAP_START_OFFSET = 4;

    /**
     * Builds an adjacency list graph from the contents of a .map file
     *
     * @param data contents of a .map file
     * @return graph
     */
    public static Graph buildGraph(List<String> data) {
        int height = getValue(data.get(1), 7);
        int width = getValue(data.get(2), 6);
        Graph graph = new Graph(width, height);

        for (int i = MAP_START_OFFSET; i < height + MAP_START_OFFSET; i++) {
            int y = i - MAP_START_OFFSET;
            for (int x = 0; x < width; x++) {
                char startChar = getCharAt(data, x, y);
                if (!(startChar == '.' || startChar == 'G')) {
                    continue;
                }
                for (Direction direction : Direction.values()) {
                    int endX = x + direction.getDx();
                    int endY = y + direction.getDy();
                    if (endX < 0 || endX >= width || endY < 0 || endY >= height) {
                        continue;
                    }
                    char endChar = getCharAt(data, endX, endY);
                    if (!(endChar == '.' || endChar == 'G')) {
                        continue;
                    }
                    Coordinate start = new Coordinate(x, y);
                    Coordinate end = new Coordinate(endX, endY);
                    double cost = 1;
                    if (direction.isDiagonal()) {
                        char a = getCharAt(data, endX, y);
                        char b = getCharAt(data, x, endY);
                        // Makes sure we are not diagonally cutting trough walls
                        if (!((a == '.' || a == 'G') && (b == '.' || b == 'G'))) {
                            continue;
                        }
                        // Using square root of two for cost of diagonal movement
                        cost = MathUtil.SQRT_OF_TWO;
                    }
                    graph.addEdge(start, end, cost);
                }
            }
        }
        return graph;
    }

    /**
     * Builds a grid from the contents of a .map file 
     * 
     * @param data contents of a .map file
     * @return grid
     */
    public static Grid buildGrid(List<String> data) {
        int height = getValue(data.get(1), 7);
        int width = getValue(data.get(2), 6);
        Grid grid = new Grid(width, height);

        for (int i = MAP_START_OFFSET; i < height + MAP_START_OFFSET; i++) {
            int y = i - MAP_START_OFFSET;
            for (int x = 0; x < width; x++) {
                char node = getCharAt(data, x, y);
                boolean blocked = !(node == '.' || node == 'G');
                grid.addNode(x, y, blocked);
            }
        }
        return grid;
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
        
        Scenario scenario = new Scenario(mapData, pathFinder);

        for (int i = 1; i < scenarioData.size(); i++) {
            List<String> values = StringUtil.split(scenarioData.get(i), '\t');
            int startX = StringUtil.toInt(values.get(4));
            int startY = StringUtil.toInt(values.get(5));
            int endX = StringUtil.toInt(values.get(6));
            int endY = StringUtil.toInt(values.get(7));
            double optimalLength = StringUtil.toDouble(values.get(8));
            Coordinate start = new Coordinate(startX, startY);
            Coordinate end = new Coordinate(endX, endY);
            Test test = new Test(start, end, optimalLength);
            scenario.addTest(test);
        }
        return scenario;
    }

    private static int getValue(String line, int index) {
        String substring = StringUtil.substring(line, index, line.length());
        return StringUtil.toInt(substring);
    }

    private static char getCharAt(List<String> grid, int x, int y) {
        return grid.get(y + MAP_START_OFFSET).charAt(x);
    }

}
