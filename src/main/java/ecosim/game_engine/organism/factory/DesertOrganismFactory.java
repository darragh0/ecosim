package ecosim.game_engine.organism.factory;

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.builder.AnimalBuilder;
import ecosim.game_engine.organism.builder.DesertAnimalBuilder;
import ecosim.game_engine.organism.builder.DesertPlantBuilder;
import ecosim.game_engine.organism.builder.PlantBuilder;

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
