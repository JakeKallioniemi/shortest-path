package shortestpath.util;

import shortestpath.util.List;

/**
 * Constants and math functions.
 *
 * @author Jake
 */
public class MathUtil {

    /**
     * Approximate value of square root of 2.
     */
    public static double SQRT_OF_TWO = 1.4142135623730950488016887242097;

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
     * @param values
     * @return average of the values
     */
    public static double average(List<Long> values) {
        long sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i);
        }
        return sum / (double) values.size();
    }

    /**
     * Calculates the absolute value of given value.
     *
     * @param value
     * @return absolute value
     */
    public static int abs(int value) {
        return value > 0 ? value : -value;
    }

    /**
     * Calculates the absolute value of given value.
     *
     * @param value
     * @return absolute value
     */
    public static double abs(double value) {
        return value > 0 ? value : -value;
    }

    /**
     * Calculates the Euclidean norm sqrt(a^2 + b^2)
     *
     * @param a first value
     * @param b second value
     * @return Euclidean norm
     */
    public static double hypot(double a, double b) {
        return Math.hypot(a, b);
    }
}
