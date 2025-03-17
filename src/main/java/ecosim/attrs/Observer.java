package ecosim.attrs;

/**
 * Represents an observer that can be notified of changes in an observable object.
 * @author darragh0
 */

public interface Observer {
    void update(Observable observable);
}

