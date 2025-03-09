package ecosim.menu;


import java.util.List;

import ecosim.enm.AnimalType;


public class AnimalMenu extends OrganismMenu<AnimalType> {

    public AnimalMenu(final List<AnimalType> options) {
        super("Animal Selection", options);
    }

    public AnimalMenu(final AnimalType... options) {
        this(List.of(options));
    }

}
