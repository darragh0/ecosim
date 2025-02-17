package ecosim.util.io;


import java.util.Scanner;
import java.util.Stack;

import ecosim.util.enm.Color;
import ecosim.util.enm.TextStyle;


public final class ConsoleIO {

    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleIO() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static void closeConsoleInputSource() {
        scanner.close();
    }

    /**
     * Apply `prettify` to the given format string and arguments, 
     * then print the result to stdout.
     * 
     * @see #prettify(String, Object...)
     */
    public static void prettyPrint(String format, Object... args) {
        System.out.print(prettify(format, args));
    }

    /** 
     * Same as `prettyPrint`, but adds a newline.
     * 
     * @see #prettyPrint(String, Object...)
     */
    public static void prettyPrintln(String format, Object... args) {
        System.out.println(prettify(format, args));
    }

    /** Print red text to stderr. */
    public static void printErr(String str, final Object... formatArgs) {
        System.err.println(prettify("<r>%s</r>".formatted(str), formatArgs));
    }

    /** @see #printErr(String, Object...) */
    public static void printErr(Throwable e) {
        printErr(e.getMessage());
    }

    /**
     * Apply color & text style (bold, italics, etc.) to the given text.
     *
     * Paired HTML-like tags are used to apply color & style. The tags
     * must be a single character (the first letter of the color/style).
     * Combine them to apply multiple styles.
     * 
     * Color tags are lowercase, style tags are uppercase.
     *     
     * Color tags:          Text style tags:
     *     - Red: r             - Bold: B
     *     - Green: g           - Italics: I
     *     - Yellow: y          - Underline: U
     *     - Blue: b            - Reversed: R
     *     - Magenta: m         - Strikethrough: S
     *     
     * For example: 
     *     - `prettify("<r>This is red</r>")
     *     - `prettify("<B>This is bold</B>")`
     *     - `prettify("<B><g>This is bold & green</g></B>")`    * 
     *  
     * @param str           Text to prettify
     * @parem formatArgs    Optional arguments to format the text
     * 
     * @return text with color and text style applied
     */
    public static String prettify(String str, final Object... formatArgs) {

        if (formatArgs.length > 0) {
            str = String.format(str, formatArgs);
        }

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        boolean inTag = false;
        boolean inClosingTag = false;
        int tagChars = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (inClosingTag) {

                if (stack.isEmpty()) {
                    throw new IllegalArgumentException(
                        "Closing tag '</%s>' has no matching opening tag (index=%d)".formatted(c, i - 2));
                }

                if (stack.peek() != c) {
                    throw new IllegalArgumentException(
                        "Closing tag mismatch: '</%s>' expected, but '</%s>' found (index=%d) (last opened must be first closed)"
                            .formatted(stack.peek(), c, i - 2));
                }

                inClosingTag = false;
                stack.pop();
                sb.append(TextStyle.NONE.getValue());

                if (!stack.isEmpty()) {
                    char last = stack.peek();
                    sb.append(getTextStyleOrColor(last));
                }

            } else if (inTag) {

                switch (c) {
                    case '>' -> inTag = false;
                    case '/' -> inClosingTag = true;
                    default -> {
                        if (++tagChars > 1) {
                            throw new IllegalArgumentException(
                                "Tags must be a single character (index=%d)".formatted(i - 1));
                        }
                        stack.push(c);
                        sb.append(getTextStyleOrColor(c));
                    }
                }
            } else if (c == '<') {
                inTag = true;
                tagChars = 0;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private static String getTextStyleOrColor(char ch) {
        return Character.isUpperCase(ch)
            ? TextStyle.from(ch).getValue()
            : Color.from(ch).getValue();
    }

}
