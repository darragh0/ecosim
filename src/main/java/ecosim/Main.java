package ecosim;

/**
 * Main class for the ecosystem simulator.
 */

import ecosim.controller.EcosystemController;

public class Main {

    public static void main(String[] args) {
        EcosystemController ecosystemController = new EcosystemController();
        ecosystemController.run();
    }

}
