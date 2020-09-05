package shortestpath;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import shortestpath.datastructures.Coordinate;
import shortestpath.benchmark.Scenario;
import shortestpath.datastructures.Edge;
import shortestpath.domain.AStar;
import shortestpath.datastructures.Graph;
import shortestpath.datastructures.Grid;
import shortestpath.io.FileReader;
import shortestpath.datastructures.List;
import shortestpath.datastructures.Node;
import shortestpath.util.MathUtil;
import shortestpath.util.Parser;

public class ParserTest {

    private static List<String> mapData;
    private static List<String> scenarioData;

    @BeforeAll
    public static void setUp() throws IOException {
        mapData = FileReader.read(new File("maps/test.map"));
        scenarioData = FileReader.read(new File("maps/test.map.scen"));
    }

    @Test
    public void graphHasCorrctSize() {
        Graph graph = Parser.buildGraph(mapData);
        assertEquals(10, graph.getWidth());
        assertEquals(9, graph.getHeight());
    }

    @Test
    public void grapHasCorrectNeighbours() {
        Graph graph = Parser.buildGraph(mapData);
        List<Edge> neighbors = graph.getNeighbors(new Coordinate(7, 6));

        assertAll(
                () -> assertEquals(2, neighbors.size()),
                () -> assertEquals(new Coordinate(7, 5), neighbors.get(0).getEnd()),
                () -> assertEquals(new Coordinate(7, 7), neighbors.get(1).getEnd())
        );
    }

    @Test
    public void gridNodeHasCorrectProperties() {
        Grid grid = Parser.buildGrid(mapData);
        Node node = grid.getNode(0, 0);
        assertTrue(node.isBlocked());
    }

    @Test
    public void scenarioHasCorrectTests() throws IOException {
        Graph graph = Parser.buildGraph(mapData);
        Scenario scen = Parser.buildScenario(scenarioData, mapData, new AStar(graph));
        // Java doesn't support aliasing imports :(
        List<shortestpath.benchmark.Test> tests = scen.getTests();

        assertAll(
                () -> assertEquals(3, tests.size()),
                () -> assertTrue(testEquals(new Coordinate(1, 1), new Coordinate(8, 1), 12.6568542, tests.get(0))),
                () -> assertTrue(testEquals(new Coordinate(1, 1), new Coordinate(1, 4), 3, tests.get(1))),
                () -> assertTrue(testEquals(new Coordinate(1, 1), new Coordinate(2, 1), 1, tests.get(2)))
        );
    }

    private boolean testEquals(Coordinate start, Coordinate end, double length, shortestpath.benchmark.Test test) {
        return test.getStart().equals(start) && test.getEnd().equals(end)
                && MathUtil.equals(length, test.getOptimalLength(), 0.0001);
    }

}
