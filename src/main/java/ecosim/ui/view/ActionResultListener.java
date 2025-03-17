package ecosim.ui.view;

/**
 * Interface for handling action result notifications.
 * Implemented by classes that need to respond to ecosystem actions.
 * @author Kabidoye-17
 */

import ecosim.game_engine.map.ActionResult;

public interface ActionResultListener {
    /**
     * Called when an action is performed in the ecosystem.
     * 
     * @param result The result of the action
     */
    void onActionPerformed(ActionResult result);
}

