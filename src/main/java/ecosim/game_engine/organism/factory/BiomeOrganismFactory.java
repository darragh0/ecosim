
package ecosim.game_engine.organism.factory;

import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.organism.builder.AnimalBuilder;
import ecosim.game_engine.organism.builder.PlantBuilder;

public interface BiomeOrganismFactory {
    /**
     * Creates an animal builder for the specific biome.
     * 
     * @param descriptor The descriptor containing the animal specifications
     * @return An animal builder
     */
    AnimalBuilder createAnimalBuilder(AnimalDescriptor descriptor);
    
    /**
     * Creates a plant builder for the specific biome.
     * 
     * @param descriptor The descriptor containing the plant specifications
     * @return A plant builder
     */
    PlantBuilder createPlantBuilder(PlantDescriptor descriptor);
    
}