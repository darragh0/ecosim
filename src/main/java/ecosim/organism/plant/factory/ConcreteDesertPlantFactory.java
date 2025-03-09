package ecosim.organism.plant.factory;


import ecosim.enm.DesertPlantType;
import ecosim.enm.PlantType;
import ecosim.organism.plant.Cactus;
import ecosim.organism.plant.DesertPlant;
import ecosim.organism.plant.Shrub;
import ecosim.organism.plant.Succulent;


/**
 * This class implements the PlantFactory interface for creating desert plants.
 * It provides the logic to create specific types of desert plants based on the provided type.
 *
 * @author MiaBorkoo
 */
public class ConcreteDesertPlantFactory implements PlantFactory {

    @Override
    public DesertPlant createPlant(final PlantType type) {
        return switch (type) {
            case DesertPlantType.CACTUS -> new Cactus();
            case DesertPlantType.SHRUB -> new Shrub();
            case DesertPlantType.SUCCULENT -> new Succulent();
            default -> throw new IllegalArgumentException("Invalid desert plant: " + type);
        };
    }

}
