package ecosim.game_engine.organism.factory;

/**
 * Factory for creating desert-specific organisms.
 * Creates builders specialized for desert biome animals and plants.
 * @author Kabidoye-17
 */

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.builder.AnimalBuilder;
import ecosim.game_engine.organism.builder.DesertAnimalBuilder;
import ecosim.game_engine.organism.builder.DesertPlantBuilder;
import ecosim.game_engine.organism.builder.PlantBuilder;

public class DesertOrganismFactory implements BiomeOrganismFactory {

    /**
     * Creates a desert-specific animal builder
     * 
     * @param descriptor The animal descriptor with specifications
     * @return A desert animal builder
     */
    @Override
    public AnimalBuilder createAnimalBuilder(AnimalDescriptor descriptor) {
        return new DesertAnimalBuilder(descriptor);
    }
    
    /**
     * Creates a desert-specific plant builder
     * 
     * @param descriptor The plant descriptor with specifications
     * @return A desert plant builder
     */
    @Override
    public PlantBuilder createPlantBuilder(PlantDescriptor descriptor) {
        return new DesertPlantBuilder(descriptor);
    }
    
}
