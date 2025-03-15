package ecosim.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ecosim.enm.Biome;
import ecosim.menu.AnimalMenu;
import ecosim.menu.BiomeMenu;
import ecosim.menu.OrganismMenu;
import ecosim.menu.PlantMenu;
import ecosim.misc.AnimalDescriptor;
import ecosim.misc.PlantDescriptor;
import ecosim.organism.Organism;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.plant.abs.Plant;

public class InputPromptView {
     public Biome promptBiomeSelection() {
        final BiomeMenu menu = new BiomeMenu(Biome.values());
        menu.print();

        final Biome choice = menu.getUserChoice();
        System.out.println();

        return choice;
    }

    public <T extends Organism> List<Class<? extends T>> promptOrganismSelection(OrganismMenu<T> menu, int num) {
        final List<Class<? extends T>> chosen = new ArrayList<>();
        menu.print();

        for (int i = 0; i < num; i++) {
            Class<? extends T> choice = menu.getUserChoice("Enter your choice (%d) >> ".formatted(i + 1));
            chosen.add(choice);
        }
        System.out.println();

        return chosen;
    }

    public List<AnimalDescriptor> promptAnimalSelection(List<AnimalDescriptor> animals, int num) {
        final List<Class<? extends Animal>> classes = animals.stream()
            .map(AnimalDescriptor::animalClass)
            .collect(Collectors.toList());

        final List<Class<? extends Animal>> chosen = promptOrganismSelection(new AnimalMenu(classes), num);

        final List<AnimalDescriptor> descriptors = new ArrayList<>();
        for (Class<? extends Animal> cls : chosen) {
            AnimalDescriptor descriptor = animals.stream()
                .filter(a -> a.animalClass() == cls)
                .findFirst()
                .orElse(null);
            descriptors.add(descriptor);
        }

        return descriptors;
    }

    public List<PlantDescriptor> promptPlantSelection(List<PlantDescriptor> plants, int num) {
        final List<Class<? extends Plant>> classes = plants.stream()
            .map(PlantDescriptor::plantClass)
            .collect(Collectors.toList());

        final List<Class<? extends Plant>> chosen = promptOrganismSelection(new PlantMenu(classes), num);

        final List<PlantDescriptor> descriptors = new ArrayList<>();
        for (Class<? extends Plant> cls : chosen) {
            PlantDescriptor descriptor = plants.stream()
                .filter(p -> p.plantClass() == cls)
                .findFirst()
                .orElse(null);
            descriptors.add(descriptor);
        }

        return descriptors;
    }
}
