package ecosim.game_engine.organism.factory;

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.builder.AnimalBuilder;
import ecosim.game_engine.organism.builder.GrasslandAnimalBuilder;
import ecosim.game_engine.organism.builder.GrasslandPlantBuilder;
import ecosim.game_engine.organism.builder.PlantBuilder;

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