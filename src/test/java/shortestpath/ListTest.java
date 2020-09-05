package shortestpath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.datastructures.List;

public class ListTest {

    @Test
    public void sizeOfTheListIsCorrect() {
        List<Integer> list = new List<>();
        list.add(2);
        list.add(100);
        assertEquals(2, list.size());
    }

    @Test
    public void canGetElementFromList() {
        List<Integer> list = new List<>();
        list.add(2);
        list.add(100);
        int value = list.get(1);
        assertEquals(100, value);
    }

    @Test
    public void throwErrorWhenIndexTooLarge() {
        List<Integer> list = new List<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
    }

    @Test
    public void throwErrorWhenIndexNegative() {
        List<Integer> list = new List<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
    }

    @Test
    public void listGrowsWhenReachingCapacity() {
        List<Integer> list = new List<>();
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }
        assertEquals(11, list.size());
    }

}
