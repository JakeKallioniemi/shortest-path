package shortestpath.util;

import shortestpath.datastructures.List;

/**
 * Constants and math functions.
 *
 * @author Jake
 */
public class MathUtil {

    /**
     * Approximate value of square root of 2.
     */
    public static double SQRT_OF_TWO = 1.4142135623730951;

    /**
     * Checks if two numbers are equal to given amount of accuracy.
     *
     * @param a first number
     * @param b seconds number
     * @param accuracy accuracy
     * @return true if equal otherwise false
     */
    public static boolean equals(double a, double b, double accuracy) {
        double difference = abs(a - b);
        return difference < accuracy;
    }

    /**
     * Calculate the average (arithmetic mean) of the values in the list.
     *
     * @param values list with values
     * @return average of the values
     * @throws IllegalArgumentException if list is empty
     */
    public static double average(List<Long> values) {
        if (values.size() == 0) {
            throw new IllegalArgumentException();
        }
        long sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i);
        }
        return sum / (double) values.size();
    }

    /**
     * Returns the smallest value in given list.
     *
     * @param values list with values
     * @return min value
     * @throws IllegalArgumentException if list is empty
     */
    public static long min(List<Long> values) {
        if (values.size() == 0) {
            throw new IllegalArgumentException();
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < values.size(); i++) {
            long value = values.get(i);
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    /**
     * Returns the smallest of the two values.
     *
     * @param a first value
     * @param b second value
     * @return min value
     */
    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * Returns the largest value in given list.
     *
     * @param values list with values
     * @return max value
     * @throws IllegalArgumentException if list is empty
     */
    public static long max(List<Long> values) {
        if (values.size() == 0) {
            throw new IllegalArgumentException();
        }
        long max = Long.MIN_VALUE;
        for (int i = 0; i < values.size(); i++) {
            long value = values.get(i);
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Calculates the absolute value of given value.
     *
     * @param value integer value
     * @return absolute value
     */
    public static int abs(int value) {
        return value > 0 ? value : -value;
    }

    /**
     * Calculates the absolute value of given value.
     *
     * @param value double value
     * @return absolute value
     */
    public static double abs(double value) {
        return value > 0 ? value : -value;
    }

    /**
     * Clamps the value between min and max so that any value larger than max
     * will return max and any value smaller than min returns min.
     *
     * @param value value to be clamped
     * @param min smallest value
     * @param max largest value
     * @return clamped value
     */
    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        }
        return value;
    }
}
