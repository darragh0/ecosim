package ecosim.ui.menu;


import java.util.List;


/**
 * Base menu for selecting organism species.
 * Abstract class that serves as a foundation for specialized organism selection menus.
 * 
 * @author darragh0
 */
public abstract class OrganismMenu<T> extends Menu<T> {

    /**
     * Creates an organism selection menu.
     * 
     * @param heading The title of the menu
     * @param options List of available organism options
     */
    public OrganismMenu(final String heading, final List<T> options) {
        super(heading, options);
    }

}
