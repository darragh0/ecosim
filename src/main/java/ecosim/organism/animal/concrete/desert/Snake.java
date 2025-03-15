package ecosim.organism.animal.concrete.desert;


import ecosim.organism.animal.abs.DesertAnimal;


/**
 * Represents a Snake, a nocturnal carnivore
 * that inhabits the ecosystem, with unique behaviors.
 * 
 * @author jjola00
 */
public class Snake extends DesertAnimal {
    private static int num = 0;

    public Snake() {
        super(++num);
    }

    @Override
    public Snake clone() {
        return new Snake();
    }

}
