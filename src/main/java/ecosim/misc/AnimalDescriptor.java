package ecosim.misc;


import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;
import ecosim.organism.animal.abs.Animal;


public record AnimalDescriptor(
    Class<? extends Animal> animalClass,
    Size size,
    Diet diet,
    ActivityType activityType,
    boolean canHibernate,
    String sound,
    String symbol) implements Descriptor {
}
