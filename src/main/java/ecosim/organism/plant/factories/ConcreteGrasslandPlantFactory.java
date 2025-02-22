package ecosim.organism.plant.factories;


import ecosim.organism.plant.*;

public class ConcreteGrasslandPlantFactory implements GrasslandPlantFactory {
    @Override
    public GrasslandPlant createTree(int x, int y) {
        return new Tree(PlantSize.LARGE, x, y);
    }

    @Override
    public GrasslandPlant createBush(int x, int y) {
        return new Bush(PlantSize.MEDIUM, x, y);
    }

    @Override
    public GrasslandPlant createWildflower(int x, int y) {
        return new Wildflower(PlantSize.SMALL, x, y);
    }
} 