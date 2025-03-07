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
    public Animal createAnimal(String type) {
        return switch (type) {
            case "Snake" -> new Snake();
            case "CactusMouse" -> new CactusMouse();
            case "Camel" -> new Camel();
            case "Lizard" -> new Lizard();
            case "Eagle" -> new Eagle();
            default -> null;
        };
    }
    
}
