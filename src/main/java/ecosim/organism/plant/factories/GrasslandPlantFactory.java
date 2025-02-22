package ecosim.organism.plant.factories;

import ecosim.organism.plant.GrasslandPlant;

public interface GrasslandPlantFactory {
    GrasslandPlant createTree(int x, int y);
    GrasslandPlant createBush(int x, int y);
    GrasslandPlant createWildflower(int x, int y);
}

