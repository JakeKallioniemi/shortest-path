package shortestpath;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shortestpath.domain.Dijkstra;
import shortestpath.domain.Graph;
import shortestpath.domain.Node;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class DijkstraTest {

    private Graph graph;

    @Before
    public void setUp() throws IOException {
        graph = Parser.buildGraph(FileReader.read(new File("maps/test.map")));
    }

    @Test
    public void findsPath() {
        Dijkstra dijkstra = new Dijkstra();
        int pathLength = dijkstra.search(graph, new Node(1, 1), new Node(3, 1));
        assertEquals(6, pathLength);
    }

    @Test
    public void failsWhenNoPath() {
        Dijkstra dijkstra = new Dijkstra();
        int pathLength = dijkstra.search(graph, new Node(1, 1), new Node(4, 1));
        assertEquals(-1, pathLength);
    }

}
