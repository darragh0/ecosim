package ecosim.organism.builder;

import ecosim.organism.animal.abs.Animal;

/**
 * Builder interface for creating animals with flexible configuration.
 */
public interface AnimalBuilder {
    /**
     * Builds the basic properties of the animal.
     * 
     * @return This builder for method chaining
     */
    AnimalBuilder buildBasicProperties();
    
    /**
     * Applies any decorator enhancements to the animal.
     * 
     * @return This builder for method chaining
     */
    AnimalBuilder applyDecorators();
    
       /**
     * Creates the animal instance.
     * 
     * @return A fully constructed animal
     */
    Animal build();
}
    