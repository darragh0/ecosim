package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.plant.abs.Plant;
import ecosim.game_engine.organism.plant.concrete.GrasslandPlant;

public class GrasslandPlantBuilder extends AbstractPlantBuilder {
      
    /**
     * Creates a new grassland plant builder.
     * 
     * @param descriptor The plant descriptor
     */
    public GrasslandPlantBuilder(PlantDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected Plant createPlantInstance() {
        return new GrasslandPlant();
    }
    
}
