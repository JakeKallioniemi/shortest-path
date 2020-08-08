package shortestpath;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import shortestpath.domain.AStar;
import shortestpath.domain.Graph;
import shortestpath.domain.Node;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class AStarTest {

    private static double ACCURACY = 0.000001;
    
    private Graph graph;
    
    @Before
    public void setUp() throws IOException {
        graph = Parser.buildGraph(FileReader.read(new File("maps/test.map")));
    }

    @Test
    public void findsPath() {
        AStar aStar = new AStar();
        double pathLength = aStar.search(graph, new Node(1, 1), new Node(3, 1));
        assertEquals(6, pathLength, ACCURACY);
    }

    @Test
    public void failsWhenNoPath() {
        AStar aStar = new AStar();
        double pathLength = aStar.search(graph, new Node(1, 1), new Node(4, 1));
        assertEquals(-1, pathLength, ACCURACY);
    }

}
