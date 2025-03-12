package ecosim.organism.plant.factory;


import java.lang.reflect.InvocationTargetException;

import ecosim.organism.plant.Plant;


/**
 * This interface defines the factory for creating plant objects.
 * It declares the method for creating plants based on type, position, and other parameters.
 *
 * @author MiaBorkoo
 */
public abstract class PlantFactory<T extends Plant> {

    @SuppressWarnings("unchecked")
    public T createPlant(Class<? extends Plant> plant) {
        try {
            return (T) plant.getConstructor().newInstance();
        } catch (
            InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException
            | NoSuchMethodException
            | SecurityException e) {

            throw new IllegalArgumentException("Invalid animal type");
        }
    }

}
