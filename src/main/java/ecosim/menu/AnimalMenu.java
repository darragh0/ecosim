package ecosim.menu;


import java.util.List;


public class AnimalMenu extends OrganismMenu {

    public AnimalMenu(final List<String> options) {
        super("Animal Selection", options);
    }

    public AnimalMenu(final String... options) {
        this(List.of(options));
    }

}
