package ecosim.common;


import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;


public final class Util {

    private Util() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Return random int in given range (inclusive).
     * 
     * @param min Min. range value
     * @param max Max. range value
     */
    public static int randInt(final int min, final int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
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
     * 
     * @param str The string to substring
     * @param start The start index (inclusive)
     * @param end The end index (exclusive)
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
     *
     * @param str
     * @return OptionalInt containing the parsed integer, or empty if NaN
     */
    public static OptionalInt parseInt(final String str) {
        try {
            return OptionalInt.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

}
