package ecosim.common.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


/**
 * Utility class for console input/output operations in the ecosystem simulation.
 * Provides methods for terminal control, formatted printing, and user input handling.
 * @author Kabidoye-17, darragh0
 */
public final class ConsoleIO {

    private static final String ANSI_REGEX = "\u001B\\[[;\\d]*m";
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int COLS;
    private static final int LINES;

    // Static initializer to get terminal dimensions
    static {
        COLS = Integer.parseInt(System.getenv().getOrDefault("COLUMNS", "0"));
        LINES = Integer.parseInt(System.getenv().getOrDefault("LINES", "0"));
    }

    /**
     * Private constructor to prevent instantiation.
     * This class should only be used statically.
     */
    private ConsoleIO() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Clears the terminal screen.
     * Uses ANSI escape codes to reset cursor position and clear display.
     */
    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
    }

    /**
     * Shows or hides the terminal cursor.
     * 
     * @param show True to show cursor, false to hide it
     */
    public static void toggleCursor(boolean show) {
        System.out.print(show ? "\033[?25h" : "\033[?25l");
    }

    /**
     * Moves the cursor up by the specified number of lines.
     * 
     * @param lines Number of lines to move up
     */
    public static void cursorUp(int lines) {
        System.out.printf("\033[%dA", lines);
    }

    /**
     * Gets the width of the terminal in columns.
     * 
     * @return Number of columns in the terminal
     */
    public static int getTermCols() {
        return COLS;
    }

    /**
     * Gets the height of the terminal in lines.
     * 
     * @return Number of lines in the terminal
     */
    public static int getTermLines() {
        return LINES;
    }

    /**
     * Gets a line of input from the user with a prompt.
     * 
     * @param prompt The prompt to display
     * @param allowEmpty Whether to allow empty input
     * @return The user's input as a string
     */
    public static String strInput(final String prompt, final boolean allowEmpty) {
        while (true) {
            pprint(prompt);
            String in;

            try {
                in = reader.readLine();
            } catch (IOException e) {
                return "";
            }

            if (in.isEmpty() && !allowEmpty)
                eprint("Input cannot be empty");
            else
                return in;
        }
    }

    /**
     * Gets a line of input from the user with a prompt.
     * 
     * @param prompt The prompt to display to the user
     * @return The user's input as a string
     */
    public static String strInput(final String prompt) {
        return strInput(prompt, false);
    }

    /**
     * Gets a line of input from the user with option to allow empty input.
     * 
     * @param allowEmpty Whether to allow empty input
     * @return The user's input as a string
     */
    public static String strInput(final boolean allowEmpty) {
        return strInput("", allowEmpty);
    }

    /**
     * Checks if a number is outside a specified range.
     * 
     * @param num The number to check
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     * @return True if number is outside the range, false otherwise
     * @throws IllegalArgumentException if min > max
     */
    public static boolean ynInput(final String prompt) {
        while (true) {
            final String in = strInput(prompt).toLowerCase();
            final Boolean result = switch (in) {
                case "y", "yes" -> true;
                case "n", "no" -> false;
                default -> {
                    eprint("Invalid input");
                    yield null;
                }
            };
            if (result != null)
                return result;
        }
    }

    public static boolean notInRange(int num, int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("Min cannot be greater than max");
        return num < min || num > max;
    }

    /**
     * Formats a string with text styling and colors.
     * 
     * @param str The string to prettify
     * @param formatArgs Format arguments (if str is a format string)
     * @return Formatted string with ANSI color codes and styling
     */
    public static String prettify(String str, final Object... formatArgs) {
        return TextPrettifier.prettify(str, formatArgs);
    }


    /**
     * BiConsumer for appending prettified strings to a StringBuilder.
     * Useful for building complex formatted output.
     */
    public static BiConsumer<StringBuilder, String> add =

        (builder, str) -> builder.append(prettify(str)).append("\n");

    /**
     * Prints a formatted string without a newline.
     * 
     * @param format The format string
     * @param args Format arguments
     */
    public static void pprint(String format, final Object... args) {
        System.out.print(prettify(format, args));
    }

    /**
     * Prints a formatted string with a newline.
     * 
     * @param format The format string
     * @param args Format arguments
     */
    public static void pprintln(String format, final Object... args) {
        System.out.println(prettify(format, args));
    }

    /**
     * Prints a formatted string centered in the terminal.
     * 
     * @param str The string to center and print
     * @param args Format arguments
     */
    public static void pprintCenter(String str, final Object... args) {
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

    /**
     * Prints an error message to stderr.
     * 
     * @param str The error message
     * @param formatArgs Format arguments
     */
    public static void eprint(String str, final Object... formatArgs) {
        System.err.println(prettify("[flr:%s]".formatted(str), formatArgs));
    }

}
