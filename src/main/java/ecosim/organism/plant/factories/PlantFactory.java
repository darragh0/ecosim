package ecosim.organism.plant.factories;

/*
 * This interface defines the factory for creating plant objects.
 * It declares the method for creating plants based on type, position, and other parameters.
 * Author: @MiaBorkoo
 */

import ecosim.organism.plant.Plant;

public interface PlantFactory {
    Plant createPlant(String plantType);
}
