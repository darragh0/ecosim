package ecosim.menu;


import java.util.List;

import ecosim.organism.animal.Animal;


public class AnimalMenu extends OrganismMenu<Animal> {

    public AnimalMenu(final List<Class<? extends Animal>> options) {
        super("Select the Animals", options);
    }

    @SafeVarargs
    public AnimalMenu(final Class<? extends Animal>... options) {
        this(List.of(options));
    }

}
