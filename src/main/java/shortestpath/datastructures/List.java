package shortestpath.datastructures;

import shortestpath.util.ArrayUtil;

/**
 * Simple list data structure.
 *
 * @author Jake
 * @param <E> type of elements inserted to list
 */
public class List<E> {

    private E[] elements;
    private int size;
    
    /**
     * Creates an empty list with initial capacity of 10.
     */
    public List() {
        this.elements = (E[]) new Object[10];
        this.size = 0;
    }
    
    /**
     * Adds a new element to list.
     *
     * @param element element to be added.
     */
    public void add(E element) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = element;
        size++;
    }

    private void grow() {
        int newCapacity = (elements.length * 3) / 2 + 1;
        E[] newArray = (E[]) new Object[newCapacity];
        ArrayUtil.copy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    /**
     * Gets element from specified index.
     *
     * @param index index of element
     * @return element from list
     */
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    /**
     * Amount of elements currently in the list.
     *
     * @return size
     */
    public int size() {
        return size;
    }
    
}
