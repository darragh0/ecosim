package ecosim.plants;
/*
 * This is the abstract class for all plants.
 * It contains the common methods and attributes for all plants.
 * It also contains the methods that are common to all plants.
 * Author: @MiaBorkoo
 */

//TODO:import Weather and then updateGrowthRate

public abstract class Plant implements Cloneable{
    protected PlantSize size;
    protected int biteCapacity;
    protected EnergyCycleState energyCycleState;
    protected float growthRate;


    
    //this is the method that need to be implemented in the subclasses, specific to each plant type
    public abstract void updateGrowthRate(/* Weather weather*/);

    //these are the methods that are common to all plants
    @Override //clonable is a part of the java.lang.Cloneable interface
    public Plant clone() throws CloneNotSupportedException {
        return (Plant) super.clone();
    }
    public void update(){
        //TODO: update the plant
    }

    public void setEnergyCycleState(EnergyCycleState state) {
        this.energyCycleState = state;
    }

    public void performEnergyCycle() {
        if (energyCycleState != null) {
            energyCycleState.performEnergyCycle(growthRate);
        }
    }

    public void beEaten(){
        System.out.println("Plant has died from being eaten");
        //TODO: remove plant from the grid
    }

    public void performAsexualReproduction(){
        try {
            Plant offspring = this.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    
} 
   
