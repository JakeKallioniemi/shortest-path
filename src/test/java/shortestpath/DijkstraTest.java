package shortestpath;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.domain.Dijkstra;
import shortestpath.domain.Graph;
import shortestpath.domain.Node;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class DijkstraTest {

    private static double ACCURACY = 0.000001;
    
    private Graph graph;
    
    @BeforeEach
    public void setUp() throws IOException {
        graph = Parser.buildGraph(FileReader.read(new File("maps/test.map")));
    }

    @Test
    public void findsPath() {
        Dijkstra dijkstra = new Dijkstra();
        double pathLength = dijkstra.search(graph, new Node(1, 1), new Node(3, 1));
        assertEquals(6, pathLength, ACCURACY);
    }

    @Test
    public void failsWhenNoPath() {
        Dijkstra dijkstra = new Dijkstra();
        double pathLength = dijkstra.search(graph, new Node(1, 1), new Node(4, 1));
        assertEquals(-1, pathLength, ACCURACY);
    }

}
