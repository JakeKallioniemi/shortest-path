package shortestpath.util;

import java.lang.reflect.Array;

/**
 * Utility functions for arrays.
 *
 * @author Jake
 */
public class ArrayUtil {

    /**
     * Copy contents of an array to another.
     *
     * @param src source array
     * @param srcStart starting position in source array
     * @param dest destination array
     * @param destStart starting position in destination array
     * @param length length of the copy
     */
    public static <T> void copy(T[] src, int srcStart, T[] dest, int destStart, int length) {
        System.arraycopy(src, srcStart, dest, destStart, length);
    }

    /**
     * Copy contents of an array to another.
     *
     * @param src source array
     * @param srcStart starting position in source array
     * @param dest destination array
     * @param destStart starting position in destination array
     * @param length length of the copy
     */
    public static void copy(char[] src, int srcStart, char[] dest, int destStart, int length) {
        // Overloaded because generics don't work on primitives
        System.arraycopy(src, srcStart, dest, destStart, length);
    }

    /**
     * Copy contents of an array to another.
     *
     * @param src source array
     * @param srcStart starting position in source array
     * @param dest destination array
     * @param destStart starting position in destination array
     * @param length length of the copy
     */
    public static <T> void copy2(T[] src, int srcStart, T[] dest, int destStart, int length) {
        // TODO
        // benchmark this method
        for (int i = 0; i < length; i++) {
            dest[destStart] = src[srcStart];
            destStart++;
            srcStart++;
        }
    }

    /**
     * Create a new array of specified type and size.
     *
     * @param type type of the array
     * @param size size of the array
     * @return new array
     */
    public static <T> T[] newArray(Class type, int size) {
        return (T[]) Array.newInstance(type, size);
    }
}
