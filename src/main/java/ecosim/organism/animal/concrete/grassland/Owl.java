package ecosim.organism.animal.concrete.grassland;


import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents an Owl, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Owl extends GrasslandAnimal {
    private static int num = 0;

    public Owl() {
        super(++num);
    }

    @Override
    public Owl clone() {
        return new Owl();
    }

}
