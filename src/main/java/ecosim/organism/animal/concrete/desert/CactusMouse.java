package ecosim.organism.animal.concrete.desert;


import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents a Cactus Mouse, a nocturnal herbivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class CactusMouse extends DesertAnimal {
    private static int num = 0;

    public CactusMouse() {
        super(++num);
    }

    @Override
    public CactusMouse clone() {
        return new CactusMouse();
    }

}
