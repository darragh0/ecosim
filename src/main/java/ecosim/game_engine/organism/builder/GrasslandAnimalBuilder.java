package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.organism.animal.abs.Animal;
import ecosim.game_engine.organism.animal.concrete.GrasslandAnimal;

/**
 * Concrete builder for grassland animals.
 * Creates animals adapted to grassland environment conditions.
 * @author Kabidoye-17, jjola00
 */
public class GrasslandAnimalBuilder extends AbstractAnimalBuilder {
    
    /**
     * Creates a grassland animal builder with the provided descriptor.
     * 
     * @param descriptor The descriptor containing animal specifications
     */
    public GrasslandAnimalBuilder(AnimalDescriptor descriptor) {
        super(descriptor);
    }

    /**
     * Creates a new grassland animal instance.
     * 
     * @return A grassland-specific animal instance
     */
    @Override
    protected Animal createAnimalInstance() {
        return new GrasslandAnimal();
    }

    
    

}