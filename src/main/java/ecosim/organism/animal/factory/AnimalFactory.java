package ecosim.organism.animal.factory;


import ecosim.enm.AnimalType;
import ecosim.organism.animal.Animal;


/**
 * Abstract factory interface.
 * 
 * @author jjola00
 */
public interface AnimalFactory {

    Animal createAnimal(final AnimalType type);

}
