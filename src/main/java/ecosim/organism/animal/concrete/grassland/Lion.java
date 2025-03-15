package ecosim.organism.animal.concrete.grassland;


import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents a Lion, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lion extends GrasslandAnimal {
    private static int num = 0;

    public Lion() {
        super(++num);
    }

    @Override
    public Lion clone() {
        return new Lion();
    }

}
