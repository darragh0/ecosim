package ecosim.game_engine.organism.factory;

/**
 * Factory for creating grassland-specific organisms.
 * Creates builders specialized for grassland biome animals and plants.
 * @author Kabidoye-17
 */

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.builder.AnimalBuilder;
import ecosim.game_engine.organism.builder.GrasslandAnimalBuilder;
import ecosim.game_engine.organism.builder.GrasslandPlantBuilder;
import ecosim.game_engine.organism.builder.PlantBuilder;

public class GrasslandOrganismFactory implements BiomeOrganismFactory {
    
    /**
     * Creates a grassland-specific animal builder
     * 
     * @param descriptor The animal descriptor with specifications
     * @return A grassland animal builder
     */
    @Override
    public AnimalBuilder createAnimalBuilder(AnimalDescriptor descriptor) {
        return new GrasslandAnimalBuilder(descriptor);
    }
    
    /**
     * Creates a grassland-specific plant builder
     * 
     * @param descriptor The plant descriptor with specifications
     * @return A grassland plant builder
     */
    @Override
    public PlantBuilder createPlantBuilder(PlantDescriptor descriptor) {
        return new GrasslandPlantBuilder(descriptor);
    }
}