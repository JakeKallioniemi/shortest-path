package shortestpath.util;

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
     * @param dest destination array
     */
    public static <T> void copy(T[] src, int srcStart, T[] dest, int destStart, int length) {
        for (int i = 0; i < length; i++) {
            dest[destStart++] = src[srcStart++];
        }
    }

    /**
     * Copy contents of an array to another.
     *
     * @param src source array
     * @param dest destination array
     */
    public static void copy(char[] src, int srcStart, char[] dest, int destStart, int length) {
        // Overloaded because generics don't work with primitives
        for (int i = 0; i < length; i++) {
            dest[destStart++] = src[srcStart++];
        }
    }
}
