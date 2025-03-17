package ecosim.ui.menu;


import java.util.List;


/**
 * Menu for selecting animal species.
 * Displays a list of available animal types for the user to choose from.
 * 
 * @author darragh0
 */
public class AnimalMenu extends OrganismMenu<String> {

    /**
     * Creates a menu for animal selection.
     * 
     * @param animalNames List of available animal names
     */
    public AnimalMenu(final List<String> animalNames) {
        super("Available Animals", animalNames);
    }

}
