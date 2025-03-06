package ecosim.organism.animal.factory;

import ecosim.organism.animal.Animal;
import ecosim.organism.animal.Deer;
import ecosim.organism.animal.Fox;
import ecosim.organism.animal.Lion;
import ecosim.organism.animal.Owl;
import ecosim.organism.animal.Rabbit;
import ecosim.attrs.Observable;

/**
 * Concrete factory for grassland animals.
 * 
 * @author jjola00
 */
public class ConcreteGrasslandAnimalFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(String type, int x, int y, Observable observable) {
        switch (type) {
            case "Rabbit":
                return new Rabbit(observable);
            case "Fox":
                return new Fox(observable);
            case "Deer":
                return new Deer(observable);
            case "Lion":
                return new Lion(observable);
            case "Owl":
                return new Owl(observable);
            default:
                return null;
        }
    }
    
}
