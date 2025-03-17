package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.organism.animal.abs.Animal;
import ecosim.game_engine.organism.animal.concrete.GrasslandAnimal;

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