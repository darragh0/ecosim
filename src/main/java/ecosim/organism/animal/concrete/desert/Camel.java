package ecosim.organism.animal.concrete.desert;


import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents a Camel, a diurnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Camel extends DesertAnimal {
    private static int num = 0;

    public Camel() {
        super(++num);
    }

    @Override
    public Camel clone() {
        return new Camel();
    }

}
