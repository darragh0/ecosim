package ecosim.organism.plant.factories;

import ecosim.organism.plant.*;

public class ConcreteDesertPlantFactory implements DesertPlantFactory {
    @Override
    public DesertPlant createCactus(int x, int y) {
        return new Cactus(PlantSize.SMALL, x, y);
    }

    @Override
    public DesertPlant createSucculent(int x, int y) {
        return new Succulent(PlantSize.MEDIUM, x, y);
    }

    @Override
    public DesertPlant createShrub(int x, int y) {
        return new Shrub(PlantSize.LARGE, x, y);
    }
} 