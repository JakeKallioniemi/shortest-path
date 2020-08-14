package shortestpath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shortestpath.util.StringUtil;

public class StringUtilTest {

    @Test
    public void substringWholeString() {
        String testString = "Hello World!";
        String substring = StringUtil.substring(testString, 0, testString.length());
        assertEquals(testString, substring);
    }

    @Test
    public void canGetSubstring() {
        String testString = "Hello World!";
        String expected = "World";
        String substring = StringUtil.substring(testString, 6, 11);
        assertEquals(expected, substring);
    }

    @Test
    public void canParseInt() {
        String string = "42";
        int value = StringUtil.toInt(string);
        assertEquals(42, value);
    }

    @Test
    public void canParseDouble() {
        String string = "3.14";
        double value = StringUtil.toDouble(string);
        assertEquals(3.14, value);
    }

    @Test
    public void splitWithTab() {
        String string = "ab\tc\t123";
        String[] parts = StringUtil.split(string, '\t');
        assertAll(
                () -> assertEquals(3, parts.length),
                () -> assertEquals("ab", parts[0]),
                () -> assertEquals("c", parts[1]),
                () -> assertEquals("123", parts[2])
        );
    }

    @Test
    public void onePartWhenNoDelimiterFound() {
        String string = "abc123";
        String[] parts = StringUtil.split(string, '\t');
        assertAll(
                () -> assertEquals(1, parts.length),
                () -> assertEquals("abc123", parts[0])
        );
    }

    @Test
    public void onePartWhenDelimiterIsLast() {
        String string = "abc123";
        String[] parts = StringUtil.split(string, '3');
        assertAll(
                () -> assertEquals(1, parts.length),
                () -> assertEquals("abc12", parts[0])
        );
    }

    @Test
    public void splitsCorrectlyWhenDelimiterIsFirst() {
        String string = " ab c123";
        String[] parts = StringUtil.split(string, ' ');
        assertAll(
                () -> assertEquals(2, parts.length),
                () -> assertEquals("ab", parts[0]),
                () -> assertEquals("c123", parts[1])
        );
    }
}
