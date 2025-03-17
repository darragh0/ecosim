package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.organism.animal.abs.Animal;
import ecosim.game_engine.organism.animal.concrete.DesertAnimal;

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

