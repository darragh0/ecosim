package ecosim.game_engine.organism.builder;

import ecosim.game_engine.organism.plant.abs.Plant;

/**
 * Builder interface for creating plants with flexible configuration.
 * Follows the Builder design pattern to construct plant objects step by step.
 * @author Kabidoye-17, MiaBorkoo
 */
public interface PlantBuilder {
    /**
     * Builds the basic properties of the plant.
     * Sets attributes like name, size, symbol, etc.
     * 
     * @return This builder for method chaining
     */
    PlantBuilder buildBasicProperties();
    
    /**
     * Creates the plant instance.
     * Finalizes construction and returns the completed plant object.
     * 
     * @return A fully constructed plant
     */
    Plant build();
}