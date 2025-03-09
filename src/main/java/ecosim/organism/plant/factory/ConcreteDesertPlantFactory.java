package ecosim.organism.plant.factory;


import ecosim.organism.plant.Cactus;
import ecosim.organism.plant.DesertPlant;
import ecosim.organism.plant.Shrub;
import ecosim.organism.plant.Succulent;


/*
 * This class implements the PlantFactory interface for creating desert plants.
 * It provides the logic to create specific types of desert plants based on the provided type.
 * Author: @MiaBorkoo
 */


public class ConcreteDesertPlantFactory implements PlantFactory {
    @Override
    public DesertPlant createPlant(String plantType) {

        switch (plantType.toLowerCase()) {
            case "cactus" -> {
                return new Cactus();
            }
            case "shrub" -> {
                return new Shrub();
            }
            case "succulent" -> {
                return new Succulent();
            }
            default -> throw new IllegalArgumentException("Unknown desert plant type: " + plantType);
        }


    }

}
