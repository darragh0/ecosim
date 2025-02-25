package ecosim.menu;


import java.util.List;


public class PlantMenu extends OrganismMenu {

    public PlantMenu(final List<String> options) {
        super("Plant Selection", options);
    }

    public PlantMenu(final String... options) {
        this(List.of(options));
    }

}
