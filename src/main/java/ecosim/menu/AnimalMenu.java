package ecosim.menu;

import java.util.List;

/**
 * Menu for selecting animal species by name.
 */
public class AnimalMenu extends OrganismMenu<String> {
    
    public AnimalMenu(List<String> animalNames) {
        super(animalNames);
    }
    
    @Override
    public void print() {
        System.out.println("Available Animals:");
        printOptions();
    }
}