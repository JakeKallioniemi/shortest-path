package shortestpath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import shortestpath.datastructures.Grid;
import shortestpath.datastructures.Node;

public class GridTest {

    private Grid grid;

    @BeforeEach
    public void setUp() {
        grid = new Grid(9, 8);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                grid.addNode(i, j, i == j);
            }
        }
    }

    @Test
    public void canGetNode() {
        Node node = grid.getNode(4, 2);
        assertEquals(4, node.getX());
        assertEquals(2, node.getY());
        assertFalse(node.isBlocked());
    }

    @Test
    public void nodeIsBlocked() {
        Node node = grid.getNode(2, 2);
        assertTrue(node.isBlocked());
    }

    @Test
    public void cantGetNonExistentNode() {
        assertAll(
                () -> assertNull(grid.getNode(-1, 3)),
                () -> assertNull(grid.getNode(5, -1)),
                () -> assertNull(grid.getNode(9, 5)),
                () -> assertNull(grid.getNode(7, 8)),
                () -> assertNull(grid.getNode(-1, 8))
        );
    }

    @Test
    public void validNodeTraversable() {
        assertAll(
                () -> assertTrue(grid.traversable(0, 1)),
                () -> assertTrue(grid.traversable(3, 2)),
                () -> assertTrue(grid.traversable(4, 1)),
                () -> assertTrue(grid.traversable(8, 7))
        );
    }

    @Test
    public void blockedNodeNotTracersable() {
        assertAll(
                () -> assertFalse(grid.traversable(0, 0)),
                () -> assertFalse(grid.traversable(3, 3)),
                () -> assertFalse(grid.traversable(6, 6)),
                () -> assertFalse(grid.traversable(7, 7))
        );
    }

    @Test
    public void nonExistentNodeNotTraversable() {
        assertAll(
                () -> assertFalse(grid.traversable(-1, 3)),
                () -> assertFalse(grid.traversable(5, -1)),
                () -> assertFalse(grid.traversable(9, 5)),
                () -> assertFalse(grid.traversable(7, 8)),
                () -> assertFalse(grid.traversable(-1, 8))
        );
    }

    @Test
    public void nodesAreResetted() {
        Node node = grid.getNode(3, 2);
        node.setOpen(true);
        node.setParent(node);
        node.setFScore(42);
        node.setGScore(3);
        node.setVisited(true);
        grid.resetNodes();

        Node resettedNode = grid.getNode(3, 2);
        assertAll(
                () -> assertFalse(resettedNode.isOpen()),
                () -> assertNull(resettedNode.getParent()),
                () -> assertEquals(0, resettedNode.getFScore()),
                () -> assertEquals(0, resettedNode.getGScore()),
                () -> assertFalse(resettedNode.visited())
        );
    }
}
