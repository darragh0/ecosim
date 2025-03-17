package ecosim.common;


import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class providing common helper methods for the ecosystem simulation.
 * Contains methods for random number generation, string manipulation, and parsing.
 * @author darragh0
 */
public final class Util {

    /**
     * Private constructor to prevent instantiation.
     * This class should only be used statically.
     */
    private Util() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Return random int in given range (inclusive).
     * 
     * @param min Min. range value
     * @param max Max. range value
     * @return Random integer between min and max (inclusive)
     */
    public static int randInt(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Return random float in given range (inclusive).
     * 
     * @param min Min. range value
     * @param max Max. range value
     * @return Random float between min and max (inclusive)
     */
    public static float randFloat(final float min, final float max) {
        return ThreadLocalRandom.current().nextFloat(min, max + 1.0f);
    }

    /**
     * Convert a string to title case (e.g. "hello there" -> "Hello There").
     *  
     * @param str String to convert
     * @return The input string with the first letter of each word capitalized
     */
    public static String title(final String str) {
        if (str == null || str.isBlank() || str.isEmpty()) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : str.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capNext = true;
                sb.append(c);
            } else if (capNext) {
                sb.append(Character.toUpperCase(c));
                capNext = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }

        return sb.toString();
    }

    /**
     * Substring a string from start to end index (Can use negative indices).
     * Negative indices count from the end of the string.
     * 
     * @param str The string to substring
     * @param start The start index (inclusive)
     * @param end The end index (exclusive)
     * @return The substring between start and end
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    public static String sub(final String str, final int start, final int end) {
        final int len = str.length();
        final int s = start < 0 ? len + start : start;
        final int e = end < 0 ? len + end : end;

        if (s < 0 || e > len || s > e) {
            throw new IndexOutOfBoundsException("Invalid start/end indices");
        }

        return str.substring(s, e);
    }

    /**
     * Parse a string to an integer.
     * Returns an empty OptionalInt if the string is not a valid integer.
     *
     * @param str The string to parse
     * @return OptionalInt containing the parsed integer, or empty if not a number
     */
    public static OptionalInt parseInt(final String str) {
        try {
            return OptionalInt.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

}
