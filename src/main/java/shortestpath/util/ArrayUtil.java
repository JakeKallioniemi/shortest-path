package shortestpath.util;

import java.lang.reflect.Array;

public class ArrayUtil {

    public static <T> void copy(T[] src, int srcStart, T[] dest, int destStart, int length) {
        System.arraycopy(src, srcStart, dest, destStart, length);
    }

    public static <T> void copy2(T[] src, int srcStart, T[] dest, int destStart, int length) {
        for (int i = 0; i < length; i++) {
            dest[destStart] = src[srcStart];
            destStart++;
            srcStart++;
        }
    }

    public static <T> T[] newArray(Class type, int size) {
        return (T[]) Array.newInstance(type, size);
    }
}
