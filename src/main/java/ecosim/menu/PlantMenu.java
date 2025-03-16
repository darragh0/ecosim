package ecosim.menu;

import java.util.List;

/**
 * Menu for selecting plant species by name.
 */
public class PlantMenu extends OrganismMenu<String> {
    
    public PlantMenu(List<String> plantNames) {
        super(plantNames);
    }
    
    @Override
    public void print() {
        System.out.println("Available Plants:");
        printOptions();
    }
}
