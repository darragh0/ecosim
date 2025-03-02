package ecosim.organism.plant.factories;

import ecosim.organism.plant.DesertPlant;
import ecosim.organism.plant.PlantSize;
import ecosim.organism.plant.Succulent;
import ecosim.organism.plant.Cactus;
import ecosim.organism.plant.Shrub;

/*
 * This class implements the PlantFactory interface for creating desert plants.
 * It provides the logic to create specific types of desert plants based on the provided type.
 * Author: @MiaBorkoo
 */

public class ConcreteDesertPlantFactory implements PlantFactory {
    @Override
    public DesertPlant createPlant(String plantType, int x, int y) {
        switch (plantType.toLowerCase()) {
            case "cactus":
                return new Cactus(PlantSize.LARGE, x, y);
            case "shrub":
                return new Shrub(PlantSize.MEDIUM, x, y);
            case "succulent":
                return new Succulent(PlantSize.SMALL, x, y);
            default:
                throw new IllegalArgumentException("Unknown desert plant type: " + plantType);
        }
    }
} 