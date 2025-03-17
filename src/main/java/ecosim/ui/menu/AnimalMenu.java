package ecosim.ui.menu;


import java.util.List;


/**
 * Menu for selecting animal species.
 * 
 * @author darragh0
 */
public class AnimalMenu extends OrganismMenu<String> {

    public AnimalMenu(final List<String> animalNames) {
        super("Available Animals", animalNames);
    }

}
