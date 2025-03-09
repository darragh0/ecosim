package ecosim.organism.plant.factory;


import ecosim.enm.GrasslandPlantType;
import ecosim.enm.PlantType;
import ecosim.organism.plant.Bush;
import ecosim.organism.plant.GrasslandPlant;
import ecosim.organism.plant.Tree;
import ecosim.organism.plant.Wildflower;


/**
 * This class implements the PlantFactory interface for creating grassland plants.
 * It provides the logic to create specific types of grassland plants based on the provided type.
 *
 * @author MiaBorkoo
 */
public class ConcreteGrasslandPlantFactory implements PlantFactory {

    @Override
    public GrasslandPlant createPlant(final PlantType type) {
        return switch (type) {
            case GrasslandPlantType.TREE -> new Tree();
            case GrasslandPlantType.BUSH -> new Bush();
            case GrasslandPlantType.WILDFLOWER -> new Wildflower();
            default -> throw new IllegalArgumentException("Invalid grassland plant: " + type);
        };
    }

}
