package ecosim.organism.animal.factory;


import java.lang.reflect.InvocationTargetException;

import ecosim.misc.AnimalDescriptor;
import ecosim.organism.animal.abs.Animal;


/**
 * Abstract factory interface.
 * 
 * @author jjola00
 */
public abstract class AnimalFactory<T extends Animal> {

    @SuppressWarnings("unchecked")
    public T createAnimal(AnimalDescriptor animal) {
        try {
            T instance = (T) animal.animalClass().getConstructor().newInstance();

            instance.setSymbol(animal.symbol())
                .setSize(animal.size())
                .setDiet(animal.diet())
                .setActivityType(animal.activityType())
                .setCanHibernate(animal.canHibernate())
                .setSound(animal.sound());

            return instance;
        } catch (
            InstantiationException
            | IllegalAccessException
            | IllegalArgumentException
            | InvocationTargetException
            | NoSuchMethodException
            | SecurityException e) {

            throw new IllegalArgumentException("Invalid animal type: " + animal.animalClass().getSimpleName());
        }
    }

}
