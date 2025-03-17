package ecosim.game_engine.organism.plant.abs;

import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.common.Util;
import ecosim.game_engine.enm.EnergyCycle;
import ecosim.game_engine.enm.Event;
import ecosim.game_engine.enm.Size;
import ecosim.game_engine.enm.TimeOfDay;
import ecosim.game_engine.enm.Weather;
import ecosim.game_engine.misc.SpeciesNumbering;
import ecosim.game_engine.organism.Organism;
import ecosim.game_engine.organism.plant.energy_cycle_state.EnergyCycleState;
import ecosim.game_engine.organism.plant.energy_cycle_state.PhotosynthesisState;
import ecosim.game_engine.organism.plant.energy_cycle_state.RespirationState;

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

    public Plant(Plant source) {
        // Copy Organism properties
        this.symbol = source.symbol;
        this.size = source.size;
        this.name = SpeciesNumbering.generateCloneName(source.name);
        this.health = (float) (source.getMaxHealth() * 0.75); // New plants start at 75% health

        // Copy Plant-specific properties
        this.biteCapacity = source.biteCapacity;
        this.growthRate = source.growthRate;

        if (source.energyCycleState.getEnergyCycle() == EnergyCycle.PHOTOSYNTHESIS) {
            this.energyCycleState = new PhotosynthesisState();
        } else if (source.energyCycleState.getEnergyCycle() == EnergyCycle.RESPIRATION) {
            this.energyCycleState = new RespirationState();
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
    public final void updateGrowthRate(Weather currentWeather) {
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
     * Performs asexual reproduction to create a new plant.
     * 
     * @return A new plant created through asexual reproduction, or null if reproduction fails
     */
    public Plant performAsexualReproduction() {
        return clone();
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
     * Sets the energy cycle state of the plant.
     * 
     * @param state The new energy cycle state
     */
    public void setEnergyCycleState(EnergyCycleState state) {
        this.energyCycleState = state;
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
    public void performEnergyCycle() {
        if (this.energyCycleState != null && !isDead()) {
                        // Get base health change from energy cycle
            float healthChange = this.energyCycleState.performEnergyCycle(growthRate);
            
            float healthPercentage = this.health / this.getMaxHealth();
            
            if (healthPercentage < 0.5f) {
                // Low health plants get less benefit/more harm
                if (healthChange > 0) {
                    healthChange *= 0.7f;  // Reduced benefit
                } else {
                    healthChange *= 1.3f;  // Increased harm
                }
            }
            
            adjustHealth(healthChange);
        }
    }

    /**
     * Called when an animal eats this plant. Reduces health based on bite damage.
     * Healthier plants (above 70% health) have 20% damage resistance.
     */
    public void beEaten() {
        biteCapacity--;
    }

    /**
     * Checks if the plant is dead based on its health. Implemented in EcosystemMan?
     * 
     * @return true if the plant is dead (health <= threshold), false otherwise
     */
    public boolean isDead() {
        return this.health <= HEALTH_THRESHOLD || biteCapacity <= 0;
    }

    public boolean canReproduce() {
        // Only attempt reproduction if health is above 60% of max health
        if (this.health < this.getMaxHealth() * 0.6) {
            return false;
        }
        
        // Calculate health percentage (0-100)
        float healthPercentage = (this.health / this.getMaxHealth()) * 100;
        
        // Calculate reproduction chance (0.5-4%)
        // Linear scaling from 0.5% at 60% health to 4% at 100% health
        float reproductionChance = 0.5f + (healthPercentage - 60) * 0.0875f;
        
        // Further reduce chance to make reproduction even rarer
        reproductionChance *= 0.5f;  // Cut all chances in half
        
        // Generate random percentage (0-100)
        float randomChance = Util.randFloat(0, 100);
        
        // Return true if random roll is less than reproduction chance
        return randomChance < reproductionChance;
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

