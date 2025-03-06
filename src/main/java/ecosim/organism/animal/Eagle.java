package ecosim.organism.animal;


import ecosim.attrs.Observable;
import ecosim.enm.ActivityType;
import ecosim.enm.Size;
import ecosim.enm.Diet;

/**
 * Represents an Eagle, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Eagle extends DesertAnimal {
    private static int eagleCount = 0;

    public Eagle(Observable observable) {
        super(Size.MEDIUM, Diet.CARNIVORE, ActivityType.DIURNAL, false, observable);
        this.name = "Eagle (" + ++eagleCount + ")";
    }

    @Override
    public void makeSound() {
        System.out.println("Sqwuak!");
    }

}
