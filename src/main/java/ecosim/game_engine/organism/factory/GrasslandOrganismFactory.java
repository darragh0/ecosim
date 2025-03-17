package ecosim.organism.factory;

import ecosim.misc.AnimalDescriptor;
import ecosim.misc.PlantDescriptor;
import ecosim.organism.builder.AnimalBuilder;
import ecosim.organism.builder.GrasslandAnimalBuilder;
import ecosim.organism.builder.GrasslandPlantBuilder;
import ecosim.organism.builder.PlantBuilder;

public class GrasslandOrganismFactory implements BiomeOrganismFactory {
    
    @Override
    public AnimalBuilder createAnimalBuilder(AnimalDescriptor descriptor) {
        return new GrasslandAnimalBuilder(descriptor);
    }
    
    @Override
    public PlantBuilder createPlantBuilder(PlantDescriptor descriptor) {
        return new GrasslandPlantBuilder(descriptor);
    }
}