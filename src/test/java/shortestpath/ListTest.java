package shortestpath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.util.List;

public class ListTest {

    @Test
    public void sizeOfTheListIsCorrect() {
        List<Integer> list = new List<>();
        list.add(2);
        list.add(100);
        assertEquals(2, list.size());
    }

    @Test
    public void canInitFromArray() {
        Integer[] a = new Integer[] {1 ,7 , 5, 9999, -50};
        List<Integer> list = new List<>(a);
        assertAll(
                () -> assertEquals(5, list.size()),
                () -> assertEquals(1, list.get(0)),
                () -> assertEquals(7, list.get(1)),
                () -> assertEquals(5, list.get(2)),
                () -> assertEquals(9999, list.get(3)),
                () -> assertEquals(-50, list.get(4))
        );
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

    @Test
    public void canUseListInForEeachLoop() {
        List<Integer> list = new List<>();
        int expectedSum = 0;
        for (int i = 0; i < 11; i++) {
            expectedSum += i;
            list.add(i);
        }
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        assertEquals(expectedSum, sum);
    }
}
