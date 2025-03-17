package ecosim.ui.menu;


import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import ecosim.common.Util;
import static ecosim.common.io.ConsoleIO.eprint;
import static ecosim.common.io.ConsoleIO.notInRange;
import static ecosim.common.io.ConsoleIO.pprintln;
import static ecosim.common.io.ConsoleIO.strInput;


/**
 * Base class for all menus in the Ecosim application.
 * Provides functionality for displaying menu options and handling user input.
 * 
 * @param <T> The type of options in the menu (e.g. `String`)
 * @author darragh0
 */
public abstract class Menu<T> {

    protected final String heading;
    protected final List<T> options;
    protected final List<String> optionStrings;

    /**
     * Constructor for creating a menu with a heading and options.
     * 
     * @param heading The title of the menu
     * @param options List of selectable options
     */
    protected Menu(final String heading, final List<T> options) {
        this.heading = heading;
        this.options = new ArrayList<>(options);
        this.optionStrings = this.options.stream().map(this::optionToString).toList();
    }

    /**
     * Displays the menu with numbered options.
     */
    public void print() {
        pprintln("[flg:%s]", this.heading);
        System.out.println("=".repeat(this.heading.length() + 2));

        for (int i = 0; i < this.options.size(); i++) {
            pprintln("([fly:%d]) [flc:%s]", i + 1, this.optionStrings.get(i));
        }
        System.out.println();
    }

    /**
     * Converts an option to its string representation.
     * 
     * @param option The option to convert
     * @return String representation of the option
     */
    protected String optionToString(final T option) {
        return option.toString();
    }

    /**
     * Gets user selection from the menu with a custom prompt.
     * Handles both numeric and text input.
     * 
     * @param prompt The prompt to display to the user
     * @return The selected option
     */
    public T getUserChoice(final String prompt) {

        while (true) {
            String strIn = strInput(prompt);
            OptionalInt intIn = Util.parseInt(strIn);

            if (intIn.isPresent()) {
                int num = intIn.getAsInt();
                if (notInRange(num, 1, this.options.size())) {
                    eprint("Enter a number between 1-%d", this.options.size());
                    continue;
                }
                return this.options.get(num - 1);
            }

            OptionalInt index = IntStream.range(0, this.optionStrings.size())
                .filter(i -> this.optionStrings.get(i).equalsIgnoreCase(strIn))
                .findFirst();

            if (index.isPresent())
                return this.options.get(index.getAsInt());

            eprint("Invalid input");
        }
    }

    /**
     * Gets user selection from the menu with default prompt.
     * 
     * @return The selected option
     */
    public T getUserChoice() {
        return this.getUserChoice("Enter your choice >> ");
    }

}
