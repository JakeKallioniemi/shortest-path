package shortestpath;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shortestpath.benchmark.Result;
import shortestpath.benchmark.Scenario;
import shortestpath.datastructures.Coordinate;
import shortestpath.datastructures.Graph;
import shortestpath.datastructures.List;
import shortestpath.domain.AStar;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class ScenarioTest {

    private static List<String> mapData;
    private static Graph graph;
    private Scenario scenario;

    @BeforeAll
    public static void setUp() throws IOException {
        mapData = FileReader.read(new File("maps/test.map"));
        graph = Parser.buildGraph(mapData);
    }

    @BeforeEach
    public void buildScenario() {
        scenario = new Scenario(mapData, new AStar(graph));
    }

    @Test
    public void testIsAdded() {
        shortestpath.benchmark.Test test = new shortestpath.benchmark.Test(
                new Coordinate(1, 1),
                new Coordinate(8, 3),
                12.6568542
        );
        scenario.addTest(test);
        List<shortestpath.benchmark.Test> tests = scenario.getTests();
        assertEquals(test, tests.get(0));
    }

    @Test
    public void testIsRan() {
        shortestpath.benchmark.Test test = new shortestpath.benchmark.Test(
                new Coordinate(1, 1),
                new Coordinate(8, 3),
                12.6568542
        );
        scenario.addTest(test);
        Result result = scenario.runScenario(1, false);
        assertAll(
                () -> assertEquals(1, result.getTestCount()),
                () -> assertTrue(result.getAvgRunTime() > 0),
                () -> assertTrue(result.getAvgSetupTime() > 0),
                () -> assertTrue(result.getMinRunTime() > 0),
                () -> assertTrue(result.getMaxRunTime() > 0)
        );
    }

}
