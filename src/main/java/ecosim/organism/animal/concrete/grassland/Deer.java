package ecosim.organism.animal.concrete.grassland;


import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents a Deer, a diurnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Deer extends GrasslandAnimal {
    private static int num = 0;

    public Deer() {
        super(++num);
    }

    @Override
    public Deer clone() {
        return new Deer();
    }

}
