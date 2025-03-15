package ecosim.organism.plant.factory;


import java.lang.reflect.InvocationTargetException;

import ecosim.misc.PlantDescriptor;
import ecosim.organism.plant.abs.Plant;


/**
 * This interface defines the factory for creating plant objects.
 * It declares the method for creating plants based on type, position, and other parameters.
 *
 * @author MiaBorkoo
 */
public abstract class PlantFactory<T extends Plant> {

    @SuppressWarnings("unchecked")
    public T createPlant(PlantDescriptor plant) {
        try {
            T instance = (T) plant.plantClass().getConstructor().newInstance();

            instance.setSymbol(plant.symbol())
                .setSize(plant.size());

            return instance;

        } catch (
            InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException
            | NoSuchMethodException
            | SecurityException e) {

            throw new IllegalArgumentException("Invalid plant type: " + plant.plantClass());
        }
    }

}
