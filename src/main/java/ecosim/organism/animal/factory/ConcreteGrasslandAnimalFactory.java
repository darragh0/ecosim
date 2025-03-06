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
    public Animal createAnimal(String type, int x, int y) {
        switch (type) {
            case "Rabbit":
                return new Rabbit();
            case "Fox":
                return new Fox();
            case "Deer":
                return new Deer();
            case "Lion":
                return new Lion();
            case "Owl":
                return new Owl();
            default:
                return null;
        }
    }
    
}
