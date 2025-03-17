package ecosim.organism.factory;

import ecosim.misc.AnimalDescriptor;
import ecosim.misc.PlantDescriptor;
import ecosim.organism.builder.AnimalBuilder;
import ecosim.organism.builder.DesertAnimalBuilder;
import ecosim.organism.builder.DesertPlantBuilder;
import ecosim.organism.builder.PlantBuilder;

public class DesertOrganismFactory implements BiomeOrganismFactory {

    @Override
    public AnimalBuilder createAnimalBuilder(AnimalDescriptor descriptor) {
        return new DesertAnimalBuilder(descriptor);
    }
    
    @Override
    public PlantBuilder createPlantBuilder(PlantDescriptor descriptor) {
        return new DesertPlantBuilder(descriptor);
    }
    
}
