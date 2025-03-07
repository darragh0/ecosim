package ecosim.organism.animal.factory;

import ecosim.organism.animal.Animal;
import ecosim.organism.animal.Deer;
import ecosim.organism.animal.Fox;
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
    public Animal createAnimal(String type) {
        return switch (type) {
            case "Rabbit" -> new Rabbit();
            case "Fox" -> new Fox();
            case "Deer" -> new Deer();
            case "Lion" -> new Lion();
            case "Owl" -> new Owl();
            default -> null;
        };
    }
    
}
