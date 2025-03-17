package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.organism.animal.abs.Animal;
import ecosim.game_engine.organism.animal.concrete.DesertAnimal;

/**
 * Concrete builder for desert animals.
 * Creates animals adapted to desert environment conditions.
 * @author Kabidoye-17, jjola00
 */
public class DesertAnimalBuilder extends AnimalBuilder {
    
    /**
     * Creates a desert animal builder with the provided descriptor.
     * 
     * @param descriptor The descriptor containing animal specifications
     */
    public DesertAnimalBuilder(AnimalDescriptor descriptor) {
        super(descriptor);
    }
    
    /**
     * Creates a new desert animal instance.
     * 
     * @return A desert-specific animal instance
     */
    @Override
    protected Animal createAnimalInstance() {
        return new DesertAnimal();
    }
}

