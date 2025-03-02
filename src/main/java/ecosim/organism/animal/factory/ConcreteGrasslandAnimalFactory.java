package ecosim.organism.animal.factory;

import ecosim.organism.animal.Animal;
import ecosim.organism.animal.Deer;
import ecosim.organism.animal.Fox;
import ecosim.organism.animal.Lion;
import ecosim.organism.animal.Owl;
import ecosim.organism.animal.Rabbit;

public class ConcreteGrasslandAnimalFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(String type, int x, int y) {
        switch (type) {
            case "Rabbit":
                return new Rabbit(x, y);
            case "Fox":
                return new Fox(x, y);
            case "Deer":
                return new Deer(x, y);
            case "Lion":
                return new Lion(x, y);
            case "Owl":
                return new Owl(x, y);
            default:
                return null;
        }
    }
    
}
