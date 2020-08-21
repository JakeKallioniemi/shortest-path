package shortestpath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.util.List;
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
    public void calculatesHypotCorrectly() {
        assertEquals(5.8309, MathUtil.hypot(3, 5), 0.0001);
    }
}
