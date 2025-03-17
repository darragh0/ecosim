package ecosim.game_engine.misc;

import ecosim.game_engine.enm.ActivityType;
import ecosim.game_engine.enm.Diet;
import ecosim.game_engine.enm.Size;

/**
 * Record that describes an animal's properties.
 * Used as a blueprint for creating animal instances.
 * @author darragh0
 */
public record AnimalDescriptor( 
    String name,
    Size size,
    Diet diet,
    ActivityType activityType,
    boolean canHibernate,
    String sound,
    String symbol) implements Descriptor {
}