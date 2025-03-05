package ecosim.menu;


import java.util.ArrayList;
import java.util.List;

import static ecosim.common.io.ConsoleIO.intInput;
import static ecosim.common.io.ConsoleIO.prettyPrintln;


@SuppressWarnings("unchecked")
public abstract class Menu<T> {

    protected final String heading;
    protected final List<T> options;

    protected Menu(final String heading, final List<T> options) {
        this.heading = heading;
        this.options = new ArrayList<>(options);
    }

    public final void addOption(final T option) {
        this.options.add(option);
    }

    public final void addOptions(final T... options) {
        this.options.addAll(List.of(options));
    }

    public final void addOptions(final List<T> options) {
        this.options.addAll(options);
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
            prettyPrintln("<b>%d)</b> %s", i + 1, this.options.get(i).toString());
        }
        System.out.println();
    }

    public int getUserNum(final String prompt) {
        return intInput(prompt, 1, this.options.size());
    }

    public int getUserNum() {
        return this.getUserNum("Enter your choice >> ");
    }

    public T getUserChoice(final String prompt) {
        return this.options.get(this.getUserNum(prompt) - 1);
    }

    public T getUserChoice() {
        return this.getUserChoice("Enter your choice >> ");
    }

}
