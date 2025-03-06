package ecosim.organism.animal.factory;


import ecosim.organism.animal.Animal;
import ecosim.attrs.Observable;

/**
 * Abstract factory interface.
 * 
 * @author jjola00
 */
public interface AnimalFactory {
    Animal createAnimal(String type, int x, int y, Observable observable);
}
