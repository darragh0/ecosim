package ecosim.organism.animal.concrete.grassland;


import ecosim.organism.animal.abs.GrasslandAnimal;


/**
 * Represents a Fox, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Fox extends GrasslandAnimal {
    private static int num = 0;

    public Fox() {
        super(++num);
    }

    @Override
    public Fox clone() {
        return new Fox();
    }

}
