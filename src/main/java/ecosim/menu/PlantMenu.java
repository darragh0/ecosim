package ecosim.menu;


import java.util.List;


/**
 * Menu for selecting plant species.
 * 
 * @author darragh0
 */
public class PlantMenu extends OrganismMenu<String> {

    public PlantMenu(final List<String> plantNames) {
        super("Available Plants", plantNames);
    }

}
