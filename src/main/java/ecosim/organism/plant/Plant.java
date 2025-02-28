package ecosim.organism.plant;
/*
 * This is the abstract class for all plants.
 * It contains the common methods and attributes for all plants.
 * It also contains the methods that are common to all plants.
 * Author: @MiaBorkoo
 */


// TODO:import Weather and then updateGrowthRate
import ecosim.organism.Organism;
import ecosim.attrs.Observable;
import ecosim.attrs.Observer;
import ecosim.enm.Event;
import ecosim.enm.TimeOfDay;
import ecosim.enm.Weather;

public abstract class Plant extends Organism implements Observer{

    protected PlantSize size;
    protected int biteCapacity;
    protected EnergyCycleState energyCycleState;
    protected float growthRate;

    public Plant(PlantSize size, int x, int y) {
        super(size.getMaxHealth(), x, y, size.getNutritionalValue());
        this.size = size;
    }


    // this is the method that need to be implemented in the subclasses, specific to each plant type
    public abstract void updateGrowthRate(/* Weather weather */);

    // these are the methods that are common to all plants
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

    public void handleWeatherUpdate(Weather weather){
        // TODO: Handle weather changes
    }

    public void handleTimeOfDayUpdate(TimeOfDay timeOfDay){
        // TODO: Handle time of day changes
    }

    public void setEnergyCycleState(EnergyCycleState state) {
        this.energyCycleState = state;
    }

    public void performEnergyCycle() {
        if (energyCycleState != null) {
            energyCycleState.performEnergyCycle(growthRate);
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



}

