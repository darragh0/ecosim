package ecosim.misc;

import ecosim.enm.ActivityType;
import ecosim.enm.Diet;
import ecosim.enm.Size;

public record AnimalDescriptor(
    String name,
    Size size,
    Diet diet,
    ActivityType activityType,
    boolean canHibernate,
    String sound,
    String symbol) implements Descriptor {
}