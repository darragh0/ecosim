package ecosim.organism.plant.factories;

import ecosim.enm.Size;
import ecosim.organism.plant.DesertPlant;
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
        // For the factory, you can decide whether to keep creating plants with managers or not
        // Option 1: Continue using weather and time managers
        // TimeOfDayManager timeOfDayManager = new TimeOfDayManager(null);
        // WeatherManager weatherManager = new WeatherManager(null);
        
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