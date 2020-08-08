package shortestpath.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
     * Rounds a number to given amount of decimal places.
     * 
     * @param value to be rounded
     * @param decimalPlaces amount of decimal places
     * @return rounded value
     */
    public static double round(double value, int decimalPlaces) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    /**
     * Checks if two numbers are equal to given amount of decimal places.
     * 
     * @param a first number
     * @param b seconds number
     * @param decimalPlaces amount of decimal places
     * @return true if equal to given amount of decimal places otherwise false
     */
    public static boolean equals(double a, double b, int decimalPlaces) {
        BigDecimal bdA = BigDecimal.valueOf(a);
        bdA = bdA.setScale(decimalPlaces, RoundingMode.FLOOR);      
        BigDecimal bdB = BigDecimal.valueOf(b);
        bdB = bdB.setScale(decimalPlaces, RoundingMode.FLOOR);      
        return bdA.equals(bdB);
    }
    
    /**
     * Calculate the average (arithmetic mean) of the values in the list.
     * 
     * @param values 
     * @return average of the values
     */
    public static double average(List<Long> values) {
        long sum = 0;
        for (Long value : values) {
            sum += value;
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
