package ecosim.organism.animal.concrete.desert;


import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents a Lizard, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Lizard extends DesertAnimal {
    private static int num = 0;

    public Lizard() {
        super(++num);
    }

    @Override
    public Lizard clone() {
        return new Lizard();
    }

}
