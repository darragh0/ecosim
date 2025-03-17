package ecosim;


import ecosim.controller.EcosystemController;
import ecosim.interceptor.ExcDispatcher;
import ecosim.interceptor.Interceptor;
import ecosim.interceptor.PrettyExcInterceptor;


public class Main {

    public static void main(String[] args) {
        // Example of the interceptor framework
        ExcDispatcher.addInterceptor(new PrettyExcInterceptor());
        Interceptor.exec(() -> 10 / 0, 1);
        EcosystemController ecosystemController = new EcosystemController();
        ecosystemController.run();
    }

}
