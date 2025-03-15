package ecosim.organism.animal.concrete.grassland;


import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents a Rabbit, a diurnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Rabbit extends GrasslandAnimal {
    private static int num = 0;

    public Rabbit() {
        super(++num);
    }

    @Override
    public Rabbit clone() {
        return new Rabbit();
    }

}
