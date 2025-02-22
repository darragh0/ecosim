package ecosim.organism.plant.factories;

import ecosim.organism.plant.DesertPlant;

public interface DesertPlantFactory {
    DesertPlant createCactus(int x, int y);
    DesertPlant createSucculent(int x, int y);
    DesertPlant createShrub(int x, int y);
}
