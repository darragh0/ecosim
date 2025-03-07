package ecosim.organism.plant.factories;

import ecosim.organism.plant.Bush;
import ecosim.organism.plant.Plant;
import ecosim.organism.plant.Tree;
import ecosim.organism.plant.Wildflower;


/*
 * This class implements the PlantFactory interface for creating grassland plants.
 * It provides the logic to create specific types of grassland plants based on the provided type.
 * Author: @MiaBorkoo
 */

public class ConcreteGrasslandPlantFactory implements PlantFactory {
    @Override
    public Plant createPlant(String plantType) {
        
        switch (plantType.toLowerCase()) {
            case "tree" -> {
                return new Tree();
            }
            case "bush" -> {
                return new Bush();
            }
            case "wildflower" -> {
                return new Wildflower();
            }
            default -> throw new IllegalArgumentException("Unknown grassland plant type: " + plantType);
        }
    }
} 