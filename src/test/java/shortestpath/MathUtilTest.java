package shortestpath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.datastructures.List;
import shortestpath.util.MathUtil;

public class MathUtilTest {

    @Test
    public void equalsTrueWhenEqual() {
        boolean isEqual = MathUtil.equals(3.14159, 3.14168, 0.0001);
        assertTrue(isEqual);
    }

    @Test
    public void equalsFalseWhenNotEqual() {
        boolean isEqual = MathUtil.equals(3.14159, 3.12168, 0.0001);
        assertFalse(isEqual);
    }

    @Test
    public void averageCorrect() {
        List<Long> values = new List<>();
        values.add(6L);
        values.add(13L);
        values.add(3L);
        assertEquals(7.333, MathUtil.average(values), 0.001);
    }

    @Test
    public void absoluteValueOfPositiveNumber() {
        assertEquals(100, MathUtil.abs(100));
    }

    @Test
    public void absoluteValueOfNegativeNumber() {
        assertEquals(100, MathUtil.abs(-100));
    }

    @Test
    public void largeValueIsClamped() {
        assertEquals(5, MathUtil.clamp(32874, 0, 5));
    }

    @Test
    public void smallValueIsClamped() {
        assertEquals(-2, MathUtil.clamp(-9812739, -2, 5));
    }

    @Test
    public void valueInRangeIsNotChanged() {
        assertEquals(3, MathUtil.clamp(3, -2, 5));
    }

    @Test
    public void maxValueIsReturnedFromList() {
        List<Long> numbers = new List<>();
        numbers.add(-932472L);
        numbers.add(0L);
        numbers.add(3274L);
        numbers.add(97832449L);
        numbers.add(10L);
        numbers.add(-44L);
        assertEquals(97832449L, MathUtil.max(numbers));
    }

    @Test
    public void minValueIsReturnedFromList() {
        List<Long> numbers = new List<>();
        numbers.add(-932472L);
        numbers.add(0L);
        numbers.add(3274L);
        numbers.add(97832449L);
        numbers.add(10L);
        numbers.add(-44L);
        assertEquals(-932472L, MathUtil.min(numbers));
    }
    
    @Test
    public void minValueIsReturned() {
        assertEquals(3, MathUtil.min(7, 3));
    }
}
