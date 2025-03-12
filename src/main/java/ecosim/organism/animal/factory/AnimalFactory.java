package ecosim.organism.animal.factory;


import java.lang.reflect.InvocationTargetException;

import ecosim.organism.animal.Animal;


/**
 * Abstract factory interface.
 * 
 * @author jjola00
 */
public abstract class AnimalFactory<T extends Animal> {

    @SuppressWarnings("unchecked")
    public T createAnimal(Class<? extends Animal> animal) {
        try {
            return (T) animal.getConstructor().newInstance();
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
