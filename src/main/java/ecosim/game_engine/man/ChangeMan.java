package ecosim.game_engine.man;


import ecosim.attrs.Observable;
import ecosim.attrs.Observer;

/**
 * Interface for managing the Observer pattern in the ecosystem.
 * Provides methods to register, unregister, and notify observers of changes.
 * @author Kabidoye-17
 */
public interface ChangeMan {
    /**
     * Registers an observer to be notified of changes to an observable.
     * 
     * @param observable The object being observed
     * @param observer The observer to be notified
     */
    void register(Observable observable, Observer observer);

    /**
     * Unregisters an observer from an observable.
     * 
     * @param observable The object being observed
     * @param observer The observer to be removed
     */
    void unregister(Observable observable, Observer observer);

    /**
     * Notifies all observers of a change in the observable.
     * 
     * @param subject The observable that has changed
     */
    void notifyObservers(Observable subject);

}
