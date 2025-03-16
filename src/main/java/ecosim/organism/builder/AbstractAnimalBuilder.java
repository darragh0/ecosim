package ecosim.organism.builder;

import java.util.Random;

import ecosim.misc.AnimalDescriptor;
import ecosim.organism.animal.abs.Animal;
import ecosim.organism.animal.decorator.ConservationBoostDecorator;
import ecosim.organism.animal.decorator.FertilityBoostDecorator;
import ecosim.organism.animal.decorator.SurvivabilityBoostDecorator;

/**
 * Abstract implementation of the AnimalBuilder with common functionality.
 */
public abstract class AbstractAnimalBuilder implements AnimalBuilder {
    protected AnimalDescriptor descriptor;
    protected Animal animal;
    protected Random random = new Random();
    
    public AbstractAnimalBuilder(AnimalDescriptor descriptor) {
        this.descriptor = descriptor;
        initializeAnimal();
    }

    /**
     * Initializes the animal instance.
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
    
    @Override
    public AnimalBuilder buildBasicProperties() {

        String speciesName = descriptor.name();
    
        // Get the next number for this species
        int number = ecosim.misc.SpeciesNumbering.getNextNumber(speciesName);
        
        // Format the name with the number
        String formattedName = ecosim.misc.SpeciesNumbering.formatName(speciesName, number);
        animal.setName(formattedName)
              .setSize(descriptor.size())
              .setDiet(descriptor.diet())
              .setActivityType(descriptor.activityType())
              .setCanHibernate(descriptor.canHibernate())
              .setSound(descriptor.sound())
              .setSymbol(descriptor.symbol());
        return this;
    }
    
    @Override
    public AnimalBuilder applyDecorators() {
        int randomDecorator = random.nextInt(4); // 0-3, with 3 being no decorator
        switch (randomDecorator) {
            case 0 -> animal = new ConservationBoostDecorator(animal);
            case 1 -> animal = new FertilityBoostDecorator(animal);
            case 2 -> animal = new SurvivabilityBoostDecorator(animal);
            default -> {
            }
        }
        
        return this;
    }
    
    @Override
    public Animal build() {
        return animal;
    }
}
