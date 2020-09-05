package shortestpath.datastructures;

import shortestpath.util.ArrayUtil;

public class BinaryHeap<E extends Comparable> {

    private Comparable[] elements;
    private int index;

    /**
     * Creates an empty binary heap.
     */
    public BinaryHeap() {
        this.elements = new Comparable[11];
        this.index = 1;
    }

    /**
     * Adds element to heap.
     *
     * @param element new element
     */
    public void add(E element) {
        if (index == elements.length) {
            grow();
        }
        elements[index] = element;
        heapify();
        index++;
    }

    /**
     * Retrieves but does not remove the smallest element in the heap.
     *
     * @return smallest element in the heap.
     */
    public E peek() {
        return (E) elements[1];
    }

    /**
     * Retrieves and removes the smallest element in the heap.
     *
     * @return smallest element in the heap.
     */
    public E poll() {
        E minValue = (E) elements[1];
        index--;
        elements[1] = elements[index];
        heapify(1);
        return minValue;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if empty otherwise false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the amount of elements in the heap.
     *
     * @return amount of elements
     */
    public int size() {
        return index - 1;
    }

    private void swap(int a, int b) {
        Comparable tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    private void grow() {
        int newCapacity = (elements.length * 3) / 2 + 1;
        Comparable[] newArray = new Comparable[newCapacity];
        ArrayUtil.copy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void heapify() {
        int i = index;
        int parent = i / 2;
        while (parent > 0 && elements[i].compareTo(elements[parent]) < 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }

    private void heapify(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int smallest = i;
        
        if (left < index && elements[left].compareTo(elements[smallest]) < 0) {
            smallest = left;
        }
        if (right < index && elements[right].compareTo(elements[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

}
