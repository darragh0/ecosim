package ecosim.ui.view;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ecosim.enm.Biome;
import ecosim.misc.AnimalDescriptor;
import ecosim.misc.PlantDescriptor;
import ecosim.ui.menu.AnimalMenu;
import ecosim.ui.menu.BiomeMenu;
import ecosim.ui.menu.PlantMenu;


public class InputPromptView {
    /**
     * Prompts the user to select a biome from the available options.
     * 
     * @return The selected biome
     */
    public Biome promptBiomeSelection() {
        final BiomeMenu menu = new BiomeMenu(Biome.values());
        menu.print();

        final Biome choice = menu.getUserChoice();
        System.out.println();

        return choice;
    }

    /**
     * Prompts the user to select a specified number of animal species from the available options.
     * 
     * @param animals The list of available animal descriptors
     * @param num The number of animals to select
     * @return List of selected animal descriptors
     */
    public List<AnimalDescriptor> promptAnimalSelection(List<AnimalDescriptor> animals, int num) {
        // Extract just the names for the menu
        final List<String> animalNames = animals.stream()
            .map(AnimalDescriptor::name)
            .collect(Collectors.toList());

        // Create a menu with animal names
        final AnimalMenu menu = new AnimalMenu(animalNames);
        menu.print();

        final List<AnimalDescriptor> selectedDescriptors = new ArrayList<>();

        // Get user selections
        for (int i = 0; i < num; i++) {
            String chosenName = menu.getUserChoice("Enter your choice (%d) >> ".formatted(i + 1));

            // Find the descriptor with the matching name
            AnimalDescriptor descriptor = animals.stream()
                .filter(a -> a.name().equals(chosenName))
                .findFirst()
                .orElse(null);

            if (descriptor != null) {
                selectedDescriptors.add(descriptor);
            }
        }
        System.out.println();

        return selectedDescriptors;
    }

    /**
     * Prompts the user to select a specified number of plant species from the available options.
     * 
     * @param plants The list of available plant descriptors
     * @param num The number of plants to select
     * @return List of selected plant descriptors
     */
    public List<PlantDescriptor> promptPlantSelection(List<PlantDescriptor> plants, int num) {
        // Extract just the names for the menu
        final List<String> plantNames = plants.stream()
            .map(PlantDescriptor::name)
            .collect(Collectors.toList());

        // Create a menu with plant names
        final PlantMenu menu = new PlantMenu(plantNames);
        menu.print();

        final List<PlantDescriptor> selectedDescriptors = new ArrayList<>();

        // Get user selections
        for (int i = 0; i < num; i++) {
            String chosenName = menu.getUserChoice("Enter your choice (%d) >> ".formatted(i + 1));

            // Find the descriptor with the matching name
            PlantDescriptor descriptor = plants.stream()
                .filter(p -> p.name().equals(chosenName))
                .findFirst()
                .orElse(null);

            if (descriptor != null) {
                selectedDescriptors.add(descriptor);
            }
        }
        System.out.println();

        return selectedDescriptors;
    }

}
