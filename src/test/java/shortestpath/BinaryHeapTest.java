package shortestpath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.util.BinaryHeap;

public class BinaryHeapTest {

    @Test
    public void heapEmptyInTheBeginning() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void heapSizeCorrect() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(3);
        heap.add(1);
        assertEquals(2, heap.size());
    }

    @Test
    public void minElementIsReturned() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(3);
        heap.add(1);
        heap.add(-5);
        heap.add(5);
        assertEquals(-5, heap.peek());
    }

    @Test
    public void minElementIsReturnedAndRemoved() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(3);
        heap.add(1);
        heap.add(-5);
        heap.add(5);
        assertAll(
                () -> assertEquals(-5, heap.poll()),
                () -> assertEquals(3, heap.size()),
                () -> assertEquals(1, heap.peek())
        );
    }
    
    @Test
    public void minElementIsReturnedAndRemoved2() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for (int i = 100; i >= 0; i--) {
            heap.add(i);
        }
        for (int i = 0; i <= 100; i++) {
            assertEquals(i, heap.poll());
        }
        assertTrue(heap.isEmpty());
    }
}
