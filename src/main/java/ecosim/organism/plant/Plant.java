package ecosim.organism.plant;
/*
 * This is the abstract class for all plants.
 * It contains the common methods and attributes for all plants.
 * It also contains the methods that are common to all plants.
 * Author: @MiaBorkoo
 */

// TODO:import Weather and then updateGrowthRate
import ecosim.enm.Size;
import ecosim.organism.Organism;
import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.Event;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;
import ecosim.man.TimeOfDayMan;
import ecosim.man.WeatherMan;
import ecosim.organism.plant.Photosynthesis;
import ecosim.organism.plant.Respiration;


public abstract class Plant extends Organism implements Observer {

    protected Size size;
    protected int biteCapacity;
    private static final int BITE_DIVISOR = 10;
    protected EnergyCycleState energyCycleState;
    protected float growthRate;

    protected String name;
    private TimeOfDay currentTimeOfDay;
    private Weather currentWeather;

    

    public Plant(Size size, int x, int y) {
        super(size, size.getMaxHealth(), x, y, size.getNutritionalValue());
        this.biteCapacity = this.size.getMaxHealth() / BITE_DIVISOR;
        // this.timeOfDayManager = timeOfDayManager;
        // this.weatherManager = weatherManager;
    }

    // Method to be implemented by subclasses for specific growth rate adjustments
    public abstract void updateGrowthRate(Weather weather);

    // These are the methods that are common to all plants
    @Override // clonable is a part of the java.lang.Cloneable interface
    public Plant clone() throws CloneNotSupportedException {
        return (Plant) super.clone();
    }

    @Override
    public void update(Observable observable){
        Event event = observable.getCurrentState();
        if (event instanceof Weather newWeather) {
            handleWeatherUpdate(newWeather);
        }
        else if (event instanceof TimeOfDay newTimeOfDay) {
            handleTimeOfDayUpdate(newTimeOfDay);
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
        System.out.println("Plant has died from being eaten");
        // TODO: remove plant from the grid
    }

   

    public void performAsexualReproduction() {
        try {
            Plant offspring = this.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //timplementing energy cycle, photosynthesis if day, respiration if night
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
        System.out.println("Updated growth rate for " + this.getClass().getSimpleName() + ": " + this.growthRate);
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
}

