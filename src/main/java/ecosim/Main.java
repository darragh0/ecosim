package ecosim;


import ecosim.controller.EcosystemController;
import ecosim.interceptor.ExcDispatcher;
import ecosim.interceptor.PrettyExcInterceptor;


public class Main {

    public static void main(String[] args) {
        ExcDispatcher.addInterceptor(new PrettyExcInterceptor());
        EcosystemController ecosystemController = new EcosystemController();
        ecosystemController.run();
    }

}
