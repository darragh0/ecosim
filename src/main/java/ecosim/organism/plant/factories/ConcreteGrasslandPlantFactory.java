package ecosim.organism.plant.factories;

import ecosim.organism.plant.Tree;
import ecosim.organism.plant.Bush;
import ecosim.organism.plant.PlantSize;
import ecosim.organism.plant.Wildflower;
import ecosim.organism.plant.Plant;

/*
 * This class implements the PlantFactory interface for creating grassland plants.
 * It provides the logic to create specific types of grassland plants based on the provided type.
 * Author: @MiaBorkoo
 */

public class ConcreteGrasslandPlantFactory implements PlantFactory {
    @Override
    public Plant createPlant(String plantType, int x, int y) {
        switch (plantType.toLowerCase()) {
            case "tree":
                return new Tree(PlantSize.LARGE, x, y);
            case "bush":
                return new Bush(PlantSize.MEDIUM, x, y);
            case "wildflower":
                return new Wildflower(PlantSize.SMALL, x, y);
            default:
                throw new IllegalArgumentException("Unknown grassland plant type: " + plantType);
        }
    }
} 