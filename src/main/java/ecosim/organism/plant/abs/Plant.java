package ecosim.organism.plant.abs;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.Event;
import ecosim.enm.Size;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.organism.Organism;
import ecosim.organism.plant.energy_cycle_state.EnergyCycleState;
import ecosim.organism.plant.energy_cycle_state.PhotosynthesisState;


/**
 * This is the abstract class for all plants.
 * It contains the common methods and attributes for all plants.
 * It also contains the methods that are common to all plants.
 * 
 * @author MiaBorkoo
 */
public abstract class Plant extends Organism implements Observer {

    protected int biteCapacity;
    private static final int BITE_DIVISOR = 10;
    protected EnergyCycleState energyCycleState;
    protected float growthRate;
    private static final float HEALTH_THRESHOLD = 0.0f;

    public Plant(int num) {
        super(num);
        this.energyCycleState = new PhotosynthesisState();
    }

    @Override
    public Plant setSize(Size size) {
        super.setSize(size);
        this.biteCapacity = this.size.getMaxHealth() / BITE_DIVISOR;
        return this;
    }

    @Override
    public Plant setSymbol(String symbol) {
        super.setSymbol(symbol);
        return this;
    }

    // Method to be implemented by subclasses for specific growth rate adjustments
    public abstract void updateGrowthRate(Weather weather);

    // These are the methods that are common to all plants
    @Override
    public abstract Plant clone();

    public Plant createClone() {
        Plant clone = clone(); // This calls the concrete subclass implementation
        if (clone != null) {
            // Set common properties
            clone.health = this.getMaxHealth() / 2;
            clone.growthRate = this.growthRate;
            clone.energyCycleState = this.energyCycleState;
            return clone;
        }
        return null;
    }

    public Plant performAsexualReproduction() {
        return createClone();
    }

    @Override
    public void update(Observable observable) {
        Event event = observable.getCurrentState();
        switch (event) {
            case Weather newWeather -> handleWeatherUpdate(newWeather);
            case TimeOfDay newTimeOfDay -> handleTimeOfDayUpdate(newTimeOfDay);
            default -> {
            }
        }
    }

    public void handleWeatherUpdate(Weather weather) {
        // Handle weather changes by updating growth rate
        this.updateGrowthRate(weather);
        this.performEnergyCycle(weather);
    }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay) {
       this.energyCycleState = this.energyCycleState.handleTimeOfDayChange(this, timeOfDay);
    }

    public void setEnergyCycleState(EnergyCycleState state) {
        this.energyCycleState = state;
    }

    public void performEnergyCycle(Weather currentWeather) {
        if (this.energyCycleState != null && !isDead()) {
            float healthChange = this.energyCycleState.performEnergyCycle(growthRate, currentWeather);
            adjustHealth(healthChange);
        }
    }

    public void beEaten() {
        this.biteCapacity--;
        // Each bite takes away (100% / BITE_DIVISOR) of max health
        this.health -= this.getMaxHealth() / BITE_DIVISOR;
    }

    /**
     * Checks if the plant is dead based on its health
     * @return true if the plant is dead (health <= threshold), false otherwise
     */
    public boolean isDead() {
        return this.health <= HEALTH_THRESHOLD;
    }

    /**
     * Adds specified amount to plant's health, capped at max health
     * @param amount Amount to add to health (can be negative for damage)
     */
    public void adjustHealth(float amount) {
        float growthAdjustment = this.getMaxHealth() * amount;
        this.health = Math.min(this.health + growthAdjustment, this.getMaxHealth());
        this.health = Math.max(this.health, 0.0f); // Don't allow negative health
    }
}

