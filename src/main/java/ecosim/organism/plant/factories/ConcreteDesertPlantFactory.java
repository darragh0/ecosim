package ecosim.organism.plant.factories;

import ecosim.enm.Size;
import ecosim.organism.plant.DesertPlant;
import ecosim.organism.plant.Succulent;
import ecosim.organism.plant.Cactus;
import ecosim.organism.plant.Shrub;

public class ConcreteDesertPlantFactory implements PlantFactory {
    @Override
    public DesertPlant createPlant(String plantType, int x, int y) {
        switch (plantType.toLowerCase()) {
            case "cactus":
                return new Cactus(Size.LARGE, x, y);
            case "shrub":
                return new Shrub(Size.MEDIUM, x, y);
            case "succulent":
                return new Succulent(Size.SMALL, x, y);
            default:
                throw new IllegalArgumentException("Unknown desert plant type: " + plantType);
        }
    }
} 