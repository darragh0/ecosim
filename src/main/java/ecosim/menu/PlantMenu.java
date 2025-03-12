package ecosim.menu;


import java.util.List;

import ecosim.organism.plant.Plant;


public class PlantMenu extends OrganismMenu<Plant> {

    public PlantMenu(final List<Class<? extends Plant>> options) {
        super("Plant Selection", options);
    }

    @SafeVarargs
    public PlantMenu(final Class<? extends Plant>... options) {
        this(List.of(options));
    }

}
