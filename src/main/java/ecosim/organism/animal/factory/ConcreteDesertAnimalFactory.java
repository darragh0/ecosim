package ecosim.organism.animal.factory;


import ecosim.enm.AnimalType;
import ecosim.enm.DesertAnimalType;
import ecosim.organism.animal.CactusMouse;
import ecosim.organism.animal.Camel;
import ecosim.organism.animal.DesertAnimal;
import ecosim.organism.animal.Eagle;
import ecosim.organism.animal.Lizard;
import ecosim.organism.animal.Snake;


/**
 * Concrete factory for desert animals.
 * 
 * @author jjola00
 */
public class ConcreteDesertAnimalFactory implements AnimalFactory {

    @Override
    public DesertAnimal createAnimal(final AnimalType type) {
        return switch (type) {
            case DesertAnimalType.SNAKE -> new Snake();
            case DesertAnimalType.CACTUS_MOUSE -> new CactusMouse();
            case DesertAnimalType.CAMEL -> new Camel();
            case DesertAnimalType.LIZARD -> new Lizard();
            case DesertAnimalType.EAGLE -> new Eagle();
            default -> throw new IllegalArgumentException("Invalid desert animal: " + type);
        };
    }

}
