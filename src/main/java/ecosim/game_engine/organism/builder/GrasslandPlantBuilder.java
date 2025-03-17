package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.plant.abs.Plant;
import ecosim.game_engine.organism.plant.concrete.GrasslandPlant;

/**
 * Concrete builder for grassland plants.
 * Creates plants adapted to grassland environment conditions.
 * @author Kabidoye-17, MiaBorkoo
 */
public class GrasslandPlantBuilder extends AbstractPlantBuilder {
      
    /**
     * Creates a new grassland plant builder.
     * 
     * @param descriptor The plant descriptor with specifications
     */
    public GrasslandPlantBuilder(PlantDescriptor descriptor) {
        super(descriptor);
    }

    /**
     * Creates a new grassland plant instance.
     * 
     * @return A grassland-specific plant instance
     */
    @Override
    protected Plant createPlantInstance() {
        return new GrasslandPlant();
    }
}
