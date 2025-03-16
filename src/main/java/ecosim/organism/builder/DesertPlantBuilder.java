package ecosim.organism.builder;

import ecosim.misc.PlantDescriptor;
import ecosim.misc.SpeciesNumbering;
import ecosim.organism.plant.abs.Plant;
import ecosim.organism.plant.concrete.DesertPlant;

public class DesertPlantBuilder extends AbstractPlantBuilder {
        
    /**
     * Creates a new desert plant builder.
     * 
     * @param descriptor The plant descriptor
     */
    public DesertPlantBuilder(PlantDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected Plant createPlantInstance() {
        return new DesertPlant();
    }
    
}
