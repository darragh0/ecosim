package ecosim.common.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public final class ConsoleIO {

    private static final String ANSI_REGEX = "\u001B\\[[;\\d]*m";
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

    public static void cursorUp(int lines) {
        System.out.printf("\033[%dA", lines);
    }

    public static int getTermCols() {
        return COLS;
    }

    public static int getTermLines() {
        return LINES;
    }

    public static String strInput(final String prompt) {
        return strInput(prompt, false);
    }

    public static String strInput(final boolean allowEmpty) {
        return strInput("", allowEmpty);
    }

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

    public static String prettify(String str, final Object... formatArgs) {
        return TextPrettifier.prettify(str, formatArgs);
    }

    public static BiConsumer<StringBuilder, String> add =
        (builder, str) -> builder.append(prettify(str)).append("\n");

    public static void pprint(String format, final Object... args) {
        System.out.print(prettify(format, args));
    }

    public static void pprintln(String format, final Object... args) {
        System.out.println(prettify(format, args));
    }

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

    public static void eprint(String str, final Object... formatArgs) {
        System.err.println(prettify("[flr:%s]".formatted(str), formatArgs));
    }

    private static String strInput(final String prompt, final boolean allowEmpty) {
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

}
