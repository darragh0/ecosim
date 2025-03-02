package ecosim.organism.plant.factories;

import ecosim.organism.plant.Tree;
import ecosim.organism.plant.Bush;
import ecosim.organism.plant.Wildflower;
import ecosim.organism.plant.PlantSize;
import ecosim.organism.plant.Plant;
import ecosim.organism.plant.GrowthStrategy;
import ecosim.organism.plant.GrasslandGrowthStrategy;
import ecosim.TimeOfDayManager;
import ecosim.WeatherManager;

/*
 * This class implements the PlantFactory interface for creating grassland plants.
 * It provides the logic to create specific types of grassland plants based on the provided type.
 * Author: @MiaBorkoo
 */

public class ConcreteGrasslandPlantFactory implements PlantFactory {
    @Override
    public Plant createPlant(String plantType, int x, int y) {
        GrowthStrategy growthStrategy = new GrasslandGrowthStrategy(); // Create a default growth strategy
        switch (plantType.toLowerCase()) {
            case "tree":
                return new Tree(PlantSize.LARGE, x, y, new TimeOfDayManager(null), new WeatherManager(null), growthStrategy);
            case "bush":
                return new Bush(PlantSize.MEDIUM, x, y, new TimeOfDayManager(null), new WeatherManager(null), growthStrategy);
            case "wildflower":
                return new Wildflower(PlantSize.SMALL, x, y, new TimeOfDayManager(null), new WeatherManager(null), growthStrategy);
            default:
                throw new IllegalArgumentException("Unknown grassland plant type: " + plantType);
        }
    }
} 