package ecosim.organism.animal.factory;

import ecosim.organism.animal.Animal;
import ecosim.organism.animal.CactusMouse;
import ecosim.organism.animal.Camel;
import ecosim.organism.animal.Eagle;
import ecosim.organism.animal.Lizard;
import ecosim.organism.animal.Snake;

public class ConcreteDesertAnimalFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(String type, int x, int y) {
        switch (type) {
            case "Snake":
                return new Snake(x, y);
            case "CactusMouse":
                return new CactusMouse(x, y);
            case "Camel":
                return new Camel(x, y);
            case "Lizard":
                return new Lizard(x, y);
            case "Eagle":
                return new Eagle(x, y);
            default:
                return null;
        }
    }
    
}
