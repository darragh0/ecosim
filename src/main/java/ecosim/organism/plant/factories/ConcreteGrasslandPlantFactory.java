package ecosim.organism.plant.factories;

import ecosim.enm.Size;
import ecosim.organism.plant.Tree;
import ecosim.organism.plant.Bush;
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
        // TimeOfDayManager timeOfDayManager = new TimeOfDayManager(null);
        // WeatherManager weatherManager = new WeatherManager(null);
        
        switch (plantType.toLowerCase()) {
            case "tree":
                return new Tree(Size.LARGE, x, y);
            case "bush":
                return new Bush(Size.MEDIUM, x, y);
            case "wildflower":
                return new Wildflower(Size.SMALL, x, y);
            default:
                throw new IllegalArgumentException("Unknown grassland plant type: " + plantType);
        }
    }
} 