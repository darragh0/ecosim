package ecosim.menu;


import java.util.List;

import ecosim.organism.Organism;


public abstract class OrganismMenu<T extends Organism> extends Menu<Class<? extends T>> {

    public OrganismMenu(final String heading, final List<Class<? extends T>> options) {
        super(heading, options);
    }

    @Override
    public String getOptionString(final int index) {
        return this.options.get(index).getSimpleName();
    }

}
