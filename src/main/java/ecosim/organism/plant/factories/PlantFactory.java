package ecosim.organism.plant.factories;

import ecosim.organism.plant.Plant;


public interface PlantFactory {
    Plant createPlant(String plantType, int x, int y);
}
