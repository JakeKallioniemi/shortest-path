package shortestpath.util;

/**
 * Various utility functions for Strings.
 *
 * @author Jake
 */
public class StringUtil {

    /**
     * Gets a substring of the given String.
     *
     * @param string a String
     * @param from start index inclusive
     * @param to end index exclusive
     * @return substring
     */
    public static String substring(String string, int from, int to) {
        char[] chars = string.toCharArray();
        int length = to - from;
        char[] substring = new char[length];
        System.arraycopy(chars, from, substring, 0, length);
        return new String(substring);
    }

    /**
     * Splits String around the provided delimiter.
     *
     * @param string a String
     * @param delimiter used for splitting
     * @return array containing parts of the String
     */
    public static String[] split(String string, char delimiter) {
        List<String> parts = new List<>();
        int start = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == delimiter) {
                if (i - start > 0) {
                    parts.add(substring(string, start, i));
                }
                start = i + 1;
            } else if (i == string.length() - 1) {
                parts.add(substring(string, start, string.length()));
            }
        }
        return parts.toArray(String.class);
    }

    /**
     * Attempts to parse a String to int.
     *
     * @param string integer String
     * @return int parsed from the input String
     */
    public static int toInt(String string) {
        return Integer.parseInt(string);
    }

    /**
     * Attempts to parse a String to double.
     *
     * @param string double String
     * @return double parsed from the input String
     */
    public static double toDouble(String string) {
        return Double.parseDouble(string);
    }
}
