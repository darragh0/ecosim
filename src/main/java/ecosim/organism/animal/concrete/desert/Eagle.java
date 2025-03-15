package ecosim.organism.animal.concrete.desert;


import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents an Eagle, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Eagle extends DesertAnimal {
    private static int num = 0;

    public Eagle() {
        super(++num);
    }

    @Override
    public Eagle clone() {
        return new Eagle();
    }

}
