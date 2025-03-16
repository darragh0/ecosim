package ecosim.organism.builder;

import java.util.Random;

import ecosim.misc.PlantDescriptor;
import ecosim.organism.plant.abs.Plant;
import ecosim.organism.plant.decorator.NutrientRichDecorator;
import ecosim.organism.plant.decorator.ToxicDecorator;

public abstract class AbstractPlantBuilder implements PlantBuilder {
        protected PlantDescriptor descriptor;
    protected Plant plant;
    protected Random random = new Random();
    
    /**
     * Creates a builder with the provided descriptor.
     * 
     * @param descriptor The descriptor containing plant specifications
     */
    public AbstractPlantBuilder(PlantDescriptor descriptor) {
        this.descriptor = descriptor;
        initializePlant();
    }
    @Override
    public PlantBuilder applyDecorators() {
        int decoratorChoice = random.nextInt(3); // 0-2
        
        switch (decoratorChoice) {
            case 0:
                plant = new ToxicDecorator(plant);
                break;
            case 1:
                plant = new NutrientRichDecorator(plant);
                break;
            default:
                // No decorator applied
                break;
        }
        
        return this;
    }

    
    /**
     * Initializes the plant instance.
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
    
    @Override
    public PlantBuilder buildBasicProperties() {
           // Get the species name from the descriptor
    String speciesName = descriptor.name();
    
    // Get the next number for this species
    int number = ecosim.misc.SpeciesNumbering.getNextNumber(speciesName);
    
    // Format the name with the number
    String formattedName = ecosim.misc.SpeciesNumbering.formatName(speciesName, number);
    
    // Set properties directly from descriptor but use formatted name
    plant.setName(formattedName)
             .setSymbol(descriptor.symbol())
             .setSize(descriptor.size());
             
        return this;
    }

      @Override
    public Plant build() {
        return plant;
    }
    
}
