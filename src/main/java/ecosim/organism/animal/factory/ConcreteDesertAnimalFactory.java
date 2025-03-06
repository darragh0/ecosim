package ecosim.organism.animal.factory;

import ecosim.organism.animal.Animal;
import ecosim.organism.animal.CactusMouse;
import ecosim.organism.animal.Camel;
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
    public Animal createAnimal(String type, int x, int y) {
        switch (type) {
            case "Snake":
                return new Snake();
            case "CactusMouse":
                return new CactusMouse();
            case "Camel":
                return new Camel();
            case "Lizard":
                return new Lizard();
            case "Eagle":
                return new Eagle();
            default:
                return null;
        }
    }
    
}
