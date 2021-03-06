package shortestpath;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import shortestpath.datastructures.Coordinate;
import shortestpath.domain.Dijkstra;
import shortestpath.datastructures.Graph;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class DijkstraTest {

    private static double ACCURACY = 0.000001;
    
    private static Graph graph;
    
    @BeforeAll
    public static void setUp() throws IOException {
        graph = Parser.buildGraph(FileReader.read(new File("maps/test.map")));
    }

    @Test
    public void findsPath() {
        Dijkstra dijkstra = new Dijkstra(graph);
        double pathLength = dijkstra.search(new Coordinate(1, 1), new Coordinate(8, 1));
        assertEquals(12.6568542, pathLength, ACCURACY);
    }

    @Test
    public void failsWhenNoPath() {
        Dijkstra dijkstra = new Dijkstra(graph);
        double pathLength = dijkstra.search(new Coordinate(1, 1), new Coordinate(1, 7));
        assertEquals(-1, pathLength, ACCURACY);
    }

}
