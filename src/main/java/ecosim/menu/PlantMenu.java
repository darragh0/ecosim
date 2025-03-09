package ecosim.menu;


import java.util.List;

import ecosim.enm.PlantType;


public class PlantMenu extends OrganismMenu<PlantType> {

    public PlantMenu(final List<PlantType> options) {
        super("Plant Selection", options);
    }

    public PlantMenu(final PlantType... options) {
        this(List.of(options));
    }

}
