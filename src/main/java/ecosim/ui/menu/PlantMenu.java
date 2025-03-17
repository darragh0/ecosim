package ecosim.ui.menu;


import java.util.List;


/**
 * Menu for selecting plant species.
 * Displays a list of available plant types for the user to choose from.
 * 
 * @author darragh0
 */
public class PlantMenu extends OrganismMenu<String> {

    /**
     * Creates a menu for plant selection.
     * 
     * @param plantNames List of available plant names
     */
    public PlantMenu(final List<String> plantNames) {
        super("Available Plants", plantNames);
    }

}
