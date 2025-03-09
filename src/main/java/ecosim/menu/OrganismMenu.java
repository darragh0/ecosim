package ecosim.menu;


import java.util.List;


public abstract class OrganismMenu<T> extends Menu<T> {

    public OrganismMenu(final String heading, final List<T> options) {
        super(heading, options);
    }

}
