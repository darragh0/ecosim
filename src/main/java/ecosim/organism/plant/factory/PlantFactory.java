package ecosim.organism.plant.factory;



import ecosim.enm.PlantType;
import ecosim.organism.plant.Plant;


/**
 * This interface defines the factory for creating plant objects.
 * It declares the method for creating plants based on type, position, and other parameters.
 * 
 * @author MiaBorkoo
 */
public interface PlantFactory {

    Plant createPlant(final PlantType plantType);

}

