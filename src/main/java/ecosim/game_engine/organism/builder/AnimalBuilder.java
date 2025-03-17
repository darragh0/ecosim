package ecosim.game_engine.organism.builder;

import ecosim.game_engine.organism.animal.abs.Animal;

/**
 * Builder interface for creating animals with flexible configuration.
 * Follows the Builder design pattern to construct animal objects step by step.
 * @author jjola00, Kabidoye-17
 */
public interface AnimalBuilder {
    /**
     * Builds the basic properties of the animal.
     * Sets attributes like name, size, diet, activity type, etc.
     * 
     * @return This builder for method chaining
     */
    AnimalBuilder buildBasicProperties();
    
    /**
     * Applies any decorator enhancements to the animal.
     * May add special abilities or modify behaviors.
     * 
     * @return This builder for method chaining
     */
    AnimalBuilder applyDecorators();
    
    /**
     * Creates the animal instance.
     * Finalizes construction and returns the completed animal object.
     * 
     * @return A fully constructed animal
     */
    Animal build();
}
    