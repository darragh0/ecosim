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
     * Apply `prettify` to the given string & print it to stdout.
     * 
     * @see #prettify(String, Object...)
     */
    public static void print(String str, final Object... formatArgs) {
        System.out.print(prettify(str, formatArgs));
    }

    /** 
     * Same as `print` but w/ a newline. 
     * 
     * @see #print(String, Object...)
     */
    public static void println(String str, final Object... formatArgs) {
        System.out.println(prettify(str, formatArgs));
    }

    public static void println() {
        System.out.println();
    }

    /** Print red text to stderr. */
    public static void printErr(String str, final Object... formatArgs) {
        System.err.println(prettify("<r>%s</r>".formatted(str), formatArgs));
    }

    /** @see #printErr(String, Object...) */
    public static void printErr(Throwable e) {
        printErr(e.getMessage());
    }

    /** Prompt user for a string input & return it. */
    public static String strInput(String prompt) {
        print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Prompt user for an integer input.
     * 
     * @param prompt    Message to display to user
     * @param min       Min. allowed integer
     * @param max       Max. allowed integer
     *
     * @return input
     */
    public static int intInput(String prompt, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min cannot be greater than max");
        }

        int number;
        while (true) {
            print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                printErr("Enter a number");
                continue;
            }

            if (number < min || number > max) {
                printErr("Enter a number between %d-%d", min, max);
            } else {
                return number;
            }
        }
    }


    /**
     * @see #intInput(String, int, int)
     */
    public static int intInput(String prompt) {
        return intInput(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
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
