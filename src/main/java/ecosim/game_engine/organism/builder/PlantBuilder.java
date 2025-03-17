package ecosim.game_engine.organism.builder;

import ecosim.game_engine.organism.plant.abs.Plant;

/**
 * Builder interface for creating plants with flexible configuration.
 */
public interface PlantBuilder {
    /**
     * Builds the basic properties of the plant.
     * 
     * @return This builder for method chaining
     */
    PlantBuilder buildBasicProperties();
    
    /**
     * Creates the plant instance.
     * 
     * @return A fully constructed plant
     */
    Plant build();
}