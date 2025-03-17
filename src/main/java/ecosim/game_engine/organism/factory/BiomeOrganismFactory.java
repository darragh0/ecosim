
package ecosim.organism.factory;

import ecosim.misc.AnimalDescriptor;
import ecosim.misc.PlantDescriptor;
import ecosim.organism.builder.AnimalBuilder;
import ecosim.organism.builder.PlantBuilder;

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