package ecosim.game_engine.organism.builder;

import ecosim.game_engine.misc.PlantDescriptor;
import ecosim.game_engine.misc.SpeciesNumbering;
import ecosim.game_engine.organism.plant.abs.Plant;

/**
 * Abstract implementation of the PlantBuilder with common functionality.
 * Provides base implementation for building plants across different biomes.
 * @author Kabidoye-17, MiaBorkoo 
 */
public abstract class PlantBuilder{
    protected PlantDescriptor descriptor;
    protected Plant plant;
    /**
     * Creates a builder with the provided descriptor.
     * 
     * @param descriptor The descriptor containing plant specifications
     */
    public PlantBuilder(PlantDescriptor descriptor) {
        this.descriptor = descriptor;
        initializePlant();
    }
    
    /**
     * Initializes the plant instance.
     * Creates the appropriate concrete plant instance.
     */
    private void initializePlant() {
        this.plant = createPlantInstance();
    }

     /**
     * Creates the appropriate plant instance for this builder.
     * Each specific builder must implement this to create the right type.
     * 
     * @return A new plant instance of the appropriate biome type
     */
    protected abstract Plant createPlantInstance();
    
    /**
     * Sets basic properties on the plant from the descriptor.
     * Includes name, symbol, and size attributes.
     * 
     * @return This builder for method chaining
     */

    public PlantBuilder buildBasicProperties() {
       // Get the species name from the descriptor
    String speciesName = descriptor.name();
    
    // Get the next number for this species
    int number = SpeciesNumbering.getNextNumber(speciesName);
    
    // Format the name with the number
    String formattedName = SpeciesNumbering.formatName(speciesName, number);
    
    // Set properties directly from descriptor but use formatted name
    plant.setName(formattedName)
             .setSymbol(descriptor.symbol())
             .setSize(descriptor.size());
             
        return this;
    }

    /**
     * Returns the fully constructed plant.
     * 
     * @return The constructed plant instance
     */

    public Plant build() {
        return plant;
    }
    
}
