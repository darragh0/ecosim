package ecosim.organism.builder;

import ecosim.misc.PlantDescriptor;
import ecosim.organism.plant.abs.Plant;
import ecosim.organism.plant.concrete.GrasslandPlant;

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
