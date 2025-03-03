package ecosim.common.io;


import java.util.Scanner;
import java.util.Stack;
import java.util.function.Consumer;

import ecosim.common.io.enm.Color;
import ecosim.common.io.enm.TextStyle;


public final class ConsoleIO {

    private static final String ANSI_REGEX = "\u001B\\[[;\\d]*m";
    private static final Scanner scanner = new Scanner(System.in);
    private static final int COLS;
    private static final int LINES;

    static {
        COLS = Integer.parseInt(System.getenv().getOrDefault("COLUMNS", "0"));
        LINES = Integer.parseInt(System.getenv().getOrDefault("LINES", "0"));
    }

    private ConsoleIO() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
    }

    public static void toggleCursor(boolean show) {
        System.out.print(show ? "\033[?25h" : "\033[?25l");
    }

    public static void moveCursorUp(int lines) {
        System.out.printf("\033[%dA", lines);
    }

    public static int getTermCols() {
        return COLS;
    }

    public static int getTermLines() {
        return LINES;
    }

    public static void closeConsoleInputSource() {
        scanner.close();
    }

    public static String strInput(final String prompt) {
        return strInput(prompt, false);
    }

    public static String strInput(final boolean allowEmpty) {
        return strInput("", allowEmpty);
    }

    public static int intInput(final String prompt, final int min, final int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min cannot be greater than max");
        }

        int number;
        while (true) {
            prettyPrint(prompt);

            try {
                number = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                printErr("Enter a number");
                continue;
            }

            if (number < min || number > max)
                printErr("Enter a number between %d-%d", min, max);
            else
                return number;
        }
    }

    public static void prettyPrint(String format, final Object... args) {
        System.out.print(prettify(format, args));
    }

    public static void prettyPrintln(String format, final Object... args) {
        System.out.println(prettify(format, args));
    }

    public static void prettyPrintCenter(String str, final Object... args) {
        str = prettify(str, args);
        final String lines[] = str.split("\n");
        Consumer<String> print = lines.length > 1 ? System.out::println : System.out::print;

        for (String line : lines) {
            final String noAnsi = line.replaceAll(ANSI_REGEX, "");
            final int pad = (COLS - noAnsi.length()) / 2;
            final String out = pad < 0 ? line : " ".repeat(pad) + line;
            print.accept(out);
        }
    }

    public static void printErr(String str, final Object... formatArgs) {
        System.err.println(prettify("<r>%s</r>".formatted(str), formatArgs));
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

    private static String strInput(final String prompt, final boolean allowEmpty) {
        while (true) {
            prettyPrint(prompt);
            String in = scanner.nextLine().trim();

            if (in.isEmpty() && !allowEmpty)
                printErr("Input cannot be empty");
            else
                return in;
        }
    }

    private static String getTextStyleOrColor(char ch) {
        return Character.isUpperCase(ch)
            ? TextStyle.from(ch).getValue()
            : Color.from(ch).getValue();
    }

}
