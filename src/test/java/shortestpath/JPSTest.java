package shortestpath;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.datastructures.Coordinate;
import shortestpath.datastructures.Grid;
import shortestpath.domain.JPS;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class JPSTest {

    private static double ACCURACY = 0.000001;
    
    private Grid grid;
    
    @BeforeEach
    public void setUp() throws IOException {
        grid = Parser.buildGrid(FileReader.read(new File("maps/test.map")));
    }

    @Test
    public void findsPath() {
        JPS jps = new JPS(grid);
        double pathLength = jps.search(new Coordinate(1, 1), new Coordinate(8, 1));
        assertEquals(12.6568542, pathLength, ACCURACY);
    }

    @Test
    public void failsWhenNoPath() {
        JPS jps = new JPS(grid);
        double pathLength = jps.search(new Coordinate(1, 1), new Coordinate(1, 7));
        assertEquals(-1, pathLength, ACCURACY);
    }

}
