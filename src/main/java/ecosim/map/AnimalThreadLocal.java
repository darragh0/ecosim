package ecosim.map;

import ecosim.organism.animal.abs.Animal;

/**
 * Utility class that tracks which animal is currently eating a plant.
 * Uses thread-local storage to maintain a reference during the eat operation.
 * This enables plant decorators to affect the animal that is eating them.
 */
public class AnimalThreadLocal {
    // Thread-local storage to track the current animal during eating operations
    private static final ThreadLocal<Animal> currentAnimal = new ThreadLocal<>();
    
    /**
     * Set the animal that is currently eating a plant.
     * Should be called before the plant.beEaten() method.
     * 
     * @param animal The animal that is eating
     */
    public static void setCurrentAnimal(Animal animal) {
        currentAnimal.set(animal);
    }
    
    /**
     * Get the animal that is currently eating a plant.
     * Will return null if no animal is currently in the eating process.
     * 
     * @return The animal currently eating, or null
     */
    public static Animal getCurrentAnimal() {
        return currentAnimal.get();
    }
    
    /**
     * Clear the current animal reference.
     * Should always be called after the eating process completes.
     */
    public static void clear() {
        currentAnimal.remove();
    }
}