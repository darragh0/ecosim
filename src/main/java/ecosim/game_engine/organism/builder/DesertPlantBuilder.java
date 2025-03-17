package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.plant.abs.Plant;
import ecosim.game_engine.organism.plant.concrete.DesertPlant;

/**
 * Concrete builder for desert plants.
 * Creates plants adapted to desert environment conditions.
 * @author Kabidoye-17, MiaBorkoo
 */
public class DesertPlantBuilder extends AbstractPlantBuilder {
        
    /**
     * Creates a new desert plant builder.
     * 
     * @param descriptor The plant descriptor with specifications
     */
    public DesertPlantBuilder(PlantDescriptor descriptor) {
        super(descriptor);
    }

    /**
     * Creates a new desert plant instance.
     * 
     * @return A desert-specific plant instance
     */
    @Override
    protected Plant createPlantInstance() {
        return new DesertPlant();
    }
}
