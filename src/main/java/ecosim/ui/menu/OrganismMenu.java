package ecosim.ui.menu;


import java.util.List;


/**
 * Base menu for selecting organism species.
 * 
 * @author darragh0
 */
public abstract class OrganismMenu<T> extends Menu<T> {

    public OrganismMenu(final String heading, final List<T> options) {
        super(heading, options);
    }

}
