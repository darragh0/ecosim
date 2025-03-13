package ecosim.menu;


import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static ecosim.common.io.ConsoleIO.notInRange;
import static ecosim.common.io.ConsoleIO.parseInt;
import static ecosim.common.io.ConsoleIO.prettyPrintln;
import static ecosim.common.io.ConsoleIO.printErr;
import static ecosim.common.io.ConsoleIO.strInput;


@SuppressWarnings("unchecked")
public abstract class Menu<T> {

    protected final String heading;
    protected final List<T> options;
    protected final List<String> optionStrings;

    protected Menu(final String heading, final List<T> options) {
        this.heading = heading;
        this.options = new ArrayList<>(options);
        this.optionStrings = this.options.stream().map(this::optionToString).toList();
    }

    public final void addOption(final T option) {
        this.options.add(option);
        this.optionStrings.add(this.optionToString(option));
    }

    public final void addOptions(final T... options) {
        this.addOptions(List.of(options));
    }

    public final void addOptions(final List<T> options) {
        options.forEach(this::addOption);
    }

    public final String getHeading() {
        return this.heading;
    }

    public final List<T> getOptions() {
        return this.options;
    }

    public void print() {
        prettyPrintln("<B>%s</B>", this.heading);
        System.out.println("=".repeat(this.heading.length() + 10));

        for (int i = 0; i < this.options.size(); i++) {
            prettyPrintln("<b>%d)</b> %s", i + 1, this.optionStrings.get(i));
        }
        System.out.println();
    }

    public String optionToString(final T option) {
        return option.toString();
    }

    public T getUserChoice(final String prompt) {

        while (true) {
            String strIn = strInput(prompt);
            OptionalInt intIn = parseInt(strIn);

            if (intIn.isPresent()) {
                int num = intIn.getAsInt();
                if (notInRange(num, 1, this.options.size())) {
                    printErr("Enter a number between 1-%d", this.options.size());
                    continue;
                }
                return this.options.get(num - 1);
            }

            OptionalInt index = IntStream.range(0, this.optionStrings.size())
                .filter(i -> this.optionStrings.get(i).equalsIgnoreCase(strIn))
                .findFirst();

            if (index.isPresent())
                return this.options.get(index.getAsInt());

            printErr("Invalid input");
        }
    }

    public T getUserChoice() {
        return this.getUserChoice("Enter your choice >> ");
    }

}
