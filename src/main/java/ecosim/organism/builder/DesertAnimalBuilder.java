package ecosim.organism.builder;

import ecosim.misc.AnimalDescriptor;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.animal.concrete.DesertAnimal;

/**
 * Concrete builder for desert animals.
 */
public class DesertAnimalBuilder extends AbstractAnimalBuilder {
    
    public DesertAnimalBuilder(AnimalDescriptor descriptor) {
        super(descriptor);
    }
    
    @Override
    protected Animal createAnimalInstance() {
    
        return new DesertAnimal();
    }
}

