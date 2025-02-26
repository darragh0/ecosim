package ecosim.menu;


import java.util.List;


public abstract class OrganismMenu extends Menu<String> {

    public OrganismMenu(final String heading, final List<String> options) {
        super(heading, options);
    }

}
