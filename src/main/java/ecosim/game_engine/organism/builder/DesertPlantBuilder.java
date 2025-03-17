package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.plant.abs.Plant;
import ecosim.game_engine.organism.plant.concrete.DesertPlant;

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
