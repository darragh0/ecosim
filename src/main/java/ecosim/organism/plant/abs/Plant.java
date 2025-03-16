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
import ecosim.organism.plant.energy_cycle_state.RespirationState;

/**
 * Abstract base class for all plants in the ecosystem simulation.
 * 
 * This class implements core plant functionality including:
 * - Energy cycle management (photosynthesis/respiration)
 * - Growth rate calculations based on environmental factors
 * - Health management and its impact on other plant functions
 * - Reproduction through asexual reproduction
 * - Response to being eaten by herbivores
 * - Environmental event observation (weather, time of day)
 * 
 * @author MiaBorkoo
 */
public abstract class Plant extends Organism implements Observer {

    /** Number of bites a plant can withstand before being completely consumed */
    protected int biteCapacity;
    
    /** Divisor used to calculate bite damage as a fraction of max health */
    private static final int BITE_DIVISOR = 10;
    
    /** Current energy cycle state (photosynthesis during day, respiration at night) */
    protected EnergyCycleState energyCycleState;
    
    /** Current growth rate, affected by weather and health */
    protected float growthRate;

    /** Health threshold below which the plant is considered dead */
    private static final float HEALTH_THRESHOLD = 0.0f;

    
    public Plant() {
        
        this.energyCycleState = new PhotosynthesisState();
    }
    /**
     * Handles time of day changes by updating the plant's energy cycle state.
     * Transitions between photosynthesis (day) and respiration (night).
     * 
     * @param timeOfDay The new time of day
     */
    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay) {
        // Handle time of day changes by setting appropriate energy cycle state
        this.energyCycleState = this.energyCycleState.handleTimeOfDayChange(this, timeOfDay);
    }
    
    /**
     * Performs the plant's energy cycle based on current state and weather.
     * This method:
     * 1. Calls the current energy cycle state to calculate health changes and modify growth rate
     * 2. Applies health-based efficiency modifiers to the health change
     * 3. Updates plant health with the final adjusted value
     * 
     * Note: Growth rate is modified directly by the energy cycle state, while
     * health changes are returned and then adjusted based on current health before being applied.
     * 
     * Plants with lower health (below 50%) get less benefit from positive energy cycles
     * and take more damage from negative energy cycles.
     * 
     * @param currentWeather Current weather condition affecting energy cycle
     */
    public void performEnergyCycle(Weather currentWeather) {
        if (this.energyCycleState != null && !isDead()) {
            // Get base health change from energy cycle
            float healthChange = this.energyCycleState.performEnergyCycle(growthRate, currentWeather);
            
            float healthPercentage = this.health / this.getMaxHealth();
            if (healthPercentage < 0.5f) {
                // Low health plants get less benefit/more harm
                if (healthChange > 0) {
                    healthChange *= 0.7f;  // Reduced benefit
                } else {
                    healthChange *= 1.3f;  // Increased harm
                }
            }
            
            // Apply the health change
            adjustHealth(healthChange);
        }
    }

    /**
     * Sets the size of the plant and calculates bite capacity based on max health.
     * 
     * @param size The size to set for this plant
     * @return 
     */
    @Override
    public Plant setSize(Size size) {
        super.setSize(size);
        this.biteCapacity = this.size.getMaxHealth() / BITE_DIVISOR;
        return this;
    }

    /**
     * Sets the display symbol for this plant.
     * 
     * @param symbol The symbol to represent this plant
     * @return This plant instance (for method chaining)
     */
    @Override
    public Plant setSymbol(String symbol) {
        super.setSymbol(symbol);
        return this;
    }

    /**
     * Updates growth rate based on current weather conditions.
     * Different plant types respond differently to weather.
     * 
     * @param weather Current weather condition
     */
    @Override
    public Plant setName(String name) {
        super.setName(name);
        return this;
    }

    /**
 * Updates the growth rate based on current weather conditions.
 * This is a template method that defines the algorithm structure.
 * 
 * @param currentWeather Current weather condition
    */
    public void updateGrowthRate(Weather currentWeather) {
        float growthAdjustment = getWeatherGrowthAdjustment(currentWeather);
        this.growthRate += this.growthRate * growthAdjustment;
    }

    /**
     * Gets the growth adjustment factor for a specific weather condition.
     * This is meant to be implemented by subclasses to provide specialized behavior.
     * 
     * @param weather The current weather condition
     * @return A float value representing the growth rate adjustment factor
     */
    protected abstract float getWeatherGrowthAdjustment(Weather weather);

    /**
     * Creates a copy of this plant. Must be implemented by concrete subclasses.
     * 
     * @return A new instance of the same plant type
     */
    @Override
    public abstract Plant clone();

    /**
     * Creates a clone for asexual reproduction.
     * Reproduction is only possible if plant has sufficient health (at least 40%).
     * Parent plant loses health when reproducing.
     * 
     * @return A new plant instance or null if reproduction fails
     */
    public Plant createClone() {
        // Simple health check - must be at least 40% healthy to reproduce
        if (this.health < this.getMaxHealth() * 0.4f) {
            return null; // Too unhealthy to reproduce
        }
        
        Plant clone = clone(); // This calls the concrete subclass implementation
        if (clone != null) {
            // Simple health setting - offspring gets 60% of max health
            clone.health = clone.getMaxHealth() * 0.6f;
            
            clone.growthRate = this.growthRate;
            clone.energyCycleState = this.energyCycleState;
            
            // Simple health cost - reproduction costs 15% of max health
            this.health -= this.getMaxHealth() * 0.15f;
            
            return clone;
        }
        return null;
    }

    /**
     * Performs asexual reproduction to create a new plant.
     * 
     * @return A new plant created through asexual reproduction, or null if reproduction fails
     */
    public Plant performAsexualReproduction() {
        return createClone();
    }

    /**
     * Handles environmental events that the plant is observing.
     * Currently responds to weather changes and time of day changes.
     * 
     * @param observable The observable object that triggered the update
     */
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

    /**
     * Handles weather changes by updating growth rate and performing energy cycle.
     * Also applies health-based adjustments to growth rate.
     * 
     * @param weather The new weather condition
     */
    public void handleWeatherUpdate(Weather weather) {
        // Handle weather changes by updating growth rate
        this.updateGrowthRate(weather);
        this.adjustGrowthRateForHealth(); // Apply health effects to growth rate
        this.performEnergyCycle(weather);
    }
    
    /**
     * Adjusts growth rate based on current health percentage.
     * Plants with lower health have reduced growth rates:
     * - Below 30% health: 50% growth reduction
     * - 30-70% health: 20% growth reduction
     * - Above 70% health: No reduction
     */
    protected void adjustGrowthRateForHealth() {
        // Simple threshold-based growth penalty
        float healthPercentage = this.health / this.getMaxHealth();
        
        // Just two simple thresholds
        if (healthPercentage < 0.3f) {
            // Severe growth penalty for very low health
            this.growthRate *= 0.5f;  // 50% reduction
        } else if (healthPercentage < 0.7f) {
            // Minor growth penalty for moderately low health
            this.growthRate *= 0.8f;  // 20% reduction
        }
        // No penalty for health >= 70%
    }

    
    
    /**
     * Sets the energy cycle state of the plant.
     * 
     * @param state The new energy cycle state
     */
    public void setEnergyCycleState(EnergyCycleState state) {
        this.energyCycleState = state;
    }

    

    /**
     * Called when an animal eats this plant. Reduces health based on bite damage.
     * Healthier plants (above 70% health) have 20% damage resistance.
     */
    public void beEaten() {
        this.biteCapacity--;
        
        // Base damage
        float damage = this.getMaxHealth() / BITE_DIVISOR;
        
        // Simple health-based adjustment - just one threshold 
        if (this.health > this.getMaxHealth() * 0.7f) {
            // Healthy plants resist damage better
            damage *= 0.8f; // 20% less damage when healthy
        }
        
        // Apply damage
        this.health -= damage;
        
        // Ensure health doesn't go below zero
        if (this.health < 0) {
            this.health = 0.0f;
        }
    }

    /**
     * Checks if the plant is dead based on its health. Implemented in EcosystemMan?
     * 
     * @return true if the plant is dead (health <= threshold), false otherwise
     */
    public boolean isDead() {
        return this.health <= HEALTH_THRESHOLD;
    }

    /**
     * Adds specified amount to plant's health, capped at max health.
     * Health cannot go below zero.
     * 
     * @param amount Amount to add to health (can be negative for damage)
     */
    public void adjustHealth(float amount) {
        this.health = Math.min(this.health + amount, this.getMaxHealth());
        this.health = Math.max(this.health, 0.0f); // Don't allow negative health
    }
}

