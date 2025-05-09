package ecosim.game_engine.organism.builder;

import ecosim.common.Util;
import ecosim.game_engine.misc.AnimalDescriptor;
import ecosim.game_engine.misc.SpeciesNumbering;
import ecosim.game_engine.organism.animal.abs.Animal;
import ecosim.game_engine.organism.animal.decorator.ConservationBoostDecorator;
import ecosim.game_engine.organism.animal.decorator.FertilityBoostDecorator;
import ecosim.game_engine.organism.animal.decorator.SurvivabilityBoostDecorator;

/**
 * Abstract implementation of the AnimalBuilder with common functionality.
 * Provides base implementation for building animals across different biomes.
 * @author Kabidoye-17, jjola00
 */
public abstract class AnimalBuilder {
    protected AnimalDescriptor descriptor;
    protected Animal animal;
    
    /**
     * Creates a builder with the provided descriptor.
     * 
     * @param descriptor The descriptor containing animal specifications
     */
    public AnimalBuilder(AnimalDescriptor descriptor) {
        this.descriptor = descriptor;
        initializeAnimal();
    }

    /**
     * Initializes the animal instance.
     * Creates the appropriate concrete animal instance.
     */
    private void initializeAnimal() {
        this.animal = createAnimalInstance();
    }

    /**
     * Creates the appropriate animal instance for this builder.
     * Each specific builder must implement this to create the right type.
     * 
     * @return A new animal instance of the appropriate biome type
     */
    protected abstract Animal createAnimalInstance();
    
    /**
     * Sets basic properties on the animal from the descriptor.
     * Includes name, size, diet, activity type, and other attributes.
     * 
     * @return This builder for method chaining
     */

    public AnimalBuilder buildBasicProperties() {

        String speciesName = descriptor.name();
    
        // Get the next number for this species
        int number = SpeciesNumbering.getNextNumber(speciesName);
        
        // Format the name with the number
        String formattedName = SpeciesNumbering.formatName(speciesName, number);
        animal.setName(formattedName)
              .setSize(descriptor.size())
              .setDiet(descriptor.diet())
              .setActivityType(descriptor.activityType())
              .setCanHibernate(descriptor.canHibernate())
              .setSound(descriptor.sound())
              .setSymbol(descriptor.symbol());
        return this;
    }
    
    /**
     * Randomly applies decorator patterns to enhance the animal.
     * May provide conservation, fertility, or survivability boosts.
     * 
     * @return This builder for method chaining
     */

    public AnimalBuilder applyDecorators() {
        int randomDecorator = Util.randInt(0, 6);
        switch (randomDecorator) {
            case 0 -> animal = new ConservationBoostDecorator(animal);
            case 1 -> animal = new FertilityBoostDecorator(animal);
            case 2 -> animal = new SurvivabilityBoostDecorator(animal);
            default -> {
            }
        }
        
        return this;
    }
    
    /**
     * Returns the fully constructed animal.
     * 
     * @return The constructed animal instance
     */

    public Animal build() {
        return animal;
    }
}
