package ecosim.organism.builder;

import ecosim.misc.AnimalDescriptor;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.animal.concrete.GrasslandAnimal;

/**
 * Concrete builder for grassland animals.
 */
public class GrasslandAnimalBuilder extends AbstractAnimalBuilder {
    
    public GrasslandAnimalBuilder(AnimalDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected Animal createAnimalInstance() {
        return new GrasslandAnimal();
    }

    
    

}