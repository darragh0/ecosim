package ecosim;

/**
 * Main class for the ecosystem simulator.
 */

import ecosim.controller.EcosystemController;
import ecosim.interceptor.ExcDispatcher;
import ecosim.interceptor.PrettyExcInterceptor;


/**
 * Main class for the Ecosystem Simulator.
 * 
 * @author darragh0
 */
public class Main {

    public static void main(String[] args) {
        ExcDispatcher.addInterceptor(new PrettyExcInterceptor());
        EcosystemController ecosystemController = new EcosystemController();
        ecosystemController.run();
    }

}
