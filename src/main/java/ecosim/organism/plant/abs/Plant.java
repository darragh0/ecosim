package ecosim.organism.plant.abs;


// TODO:import Weather and then updateGrowthRate
import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.Event;
import ecosim.enm.Size;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.organism.Organism;
import ecosim.organism.plant.energy_cycle_state.EnergyCycleState;
import ecosim.organism.plant.energy_cycle_state.Photosynthesis;
import ecosim.organism.plant.energy_cycle_state.Respiration;


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

    private TimeOfDay currentTimeOfDay;
    private Weather currentWeather;



    public Plant(int num) {
        super(num);
        // this.timeOfDayManager = timeOfDayManager;
        // this.weatherManager = weatherManager;
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
    @Override // clonable is a part of the java.lang.Cloneable interface
    public Plant clone() {
        try {
            Plant clonedPlant = (Plant) super.clone();
            // Reset health to half of maximum for the offspring
            clonedPlant.health = this.getMaxHealth() / 2;
            
            // Position the clone near the parent plant
            // This assumes your Plant class has position coordinates
            double angle = Math.random() * 2 * Math.PI;
            int offsetX = (int) Math.round(Math.cos(angle));
            int offsetY = (int) Math.round(Math.sin(angle));
            clonedPlant.setX(this.getX() + offsetX);
            clonedPlant.setY(this.getY() + offsetY);
            
            // Ensure same growth rate as parent
            clonedPlant.growthRate = this.growthRate;
            
            // Clone the energy cycle state
            if (this.energyCycleState != null) {
                if (this.energyCycleState instanceof Photosynthesis) {
                    clonedPlant.energyCycleState = new Photosynthesis();
                } else if (this.energyCycleState instanceof Respiration) {
                    clonedPlant.energyCycleState = new Respiration();
                }
            }
            
            return clonedPlant;
        } catch (CloneNotSupportedException e) {
            System.err.println("Failed to clone plant: " + e.getMessage());
            return null;
        }
    }

    public Plant createClone() {
        Plant clone = this.clone();
        if (clone != null) {
            // Additional setup for the clone could go here
            return clone;
        }
        return null;
    }

    public Plant performAsexualReproduction() {
        Plant offspring = createClone();
        if (offspring != null) {
            System.out.println(this.getClass().getSimpleName() + " has reproduced asexually!");
            // Here you would add the offspring to your ecosystem/grid
            // For example: ecosystem.addOrganism(offspring);
            return offspring;
        } else {
            System.err.println("Asexual reproduction failed for " + this.getClass().getSimpleName());
            return null;
        }
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
        updateGrowthRate(weather);
    }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay) {
        // Handle time of day changes by setting appropriate energy cycle state
        if (timeOfDay == TimeOfDay.DAY) {
            setEnergyCycleState(new Photosynthesis());
        } else {
            setEnergyCycleState(new Respiration());
        }
    }

    public void setEnergyCycleState(EnergyCycleState state) {
        this.energyCycleState = state;
    }

    public void performEnergyCycle(Weather currentWeather) {
        if (energyCycleState != null) {
            energyCycleState.performEnergyCycle(growthRate, currentWeather);
        }
    }

    public void beEaten() {
        this.biteCapacity--;
    }

    public int getBiteCapacity() {
        return biteCapacity;
    }

    // timplementing energy cycle, photosynthesis if day, respiration if night
    public void performDailyActivities() {
        TimeOfDay currentTime = getCurrentTimeOfDay();
        Weather currentWeather = getCurrentWeather();

        if (currentTime == TimeOfDay.DAY) {
            setEnergyCycleState(new Photosynthesis());
        } else {
            setEnergyCycleState(new Respiration());
        }
        performEnergyCycle(currentWeather);
    }

    protected void adjustGrowthRate(Weather currentWeather) {
        float growthAdjustment = 0.0f;

        switch (currentWeather) {
            case SUNNY:
                growthAdjustment = 0.2f; // Increase growth rate by 20% if sunny
                break;
            case RAINY:
                growthAdjustment = 0.15f; // Increase growth rate by 15% if rainy
                break;
            case DRY:
                growthAdjustment = -0.1f; // Decrease growth rate by 10% if dry
                break;
            case CLOUDY:
                growthAdjustment = 0.05f; // Increase growth rate by 5% if cloudy
                break;
            case SNOWY:
                growthAdjustment = -0.2f; // Decrease growth rate by 20% if snowy
                break;
        }

        // Update the growth rate based on the adjustment
        this.growthRate += this.growthRate * growthAdjustment;
    }

    public String getName() {
        return name;
    }



    /**
     * Gets the current time of day
     * @return The current time of day
     */
    public TimeOfDay getCurrentTimeOfDay() {
        return currentTimeOfDay;
    }

    /**
     * Gets the current weather
     * @return The current weather
     */
    public Weather getCurrentWeather() {
        return currentWeather;
    }

    /**
     * Updates the current time of day for the plant
     * @param timeOfDay The new time of day
     */
    public void updateTimeOfDay(TimeOfDay timeOfDay) {
        this.currentTimeOfDay = timeOfDay;
        // The update method handles the behavior changes already
    }

    /**
     * Updates the current weather for the plant
     * @param weather The new weather condition
     */
    public void updateWeather(Weather weather) {
        this.currentWeather = weather;
        // The update method handles the behavior changes already
    }

}

