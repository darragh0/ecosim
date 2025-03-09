package ecosim.organism.animal.factory;


import ecosim.enm.AnimalType;
import ecosim.enm.GrasslandAnimalType;
import ecosim.organism.animal.Deer;
import ecosim.organism.animal.Fox;
import ecosim.organism.animal.GrasslandAnimal;
import ecosim.organism.animal.Lion;
import ecosim.organism.animal.Owl;
import ecosim.organism.animal.Rabbit;


/**
 * Concrete factory for grassland animals.
 * 
 * @author jjola00
 */
public class ConcreteGrasslandAnimalFactory implements AnimalFactory {

    @Override
    public GrasslandAnimal createAnimal(final AnimalType type) {
        return switch (type) {
            case GrasslandAnimalType.RABBIT -> new Rabbit();
            case GrasslandAnimalType.FOX -> new Fox();
            case GrasslandAnimalType.DEER -> new Deer();
            case GrasslandAnimalType.LION -> new Lion();
            case GrasslandAnimalType.OWL -> new Owl();
            default -> throw new IllegalArgumentException("Invalid grassland animal: " + type);
        };
    }

}
