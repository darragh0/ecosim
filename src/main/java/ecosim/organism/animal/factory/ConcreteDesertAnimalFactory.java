package ecosim.organism.animal.factory;

import ecosim.attrs.Observable;
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
    public Animal createAnimal(String type, int x, int y, Observable observable) {
        switch (type) {
            case "Snake":
                return new Snake(observable);
            case "CactusMouse":
                return new CactusMouse(observable);
            case "Camel":
                return new Camel(observable);
            case "Lizard":
                return new Lizard(observable);
            case "Eagle":
                return new Eagle(observable);
            default:
                return null;
        }
    }
    
}
