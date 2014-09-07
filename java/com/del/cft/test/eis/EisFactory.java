package com.del.cft.test.eis;

import com.del.cft.test.eis.impl.EisControllerImpl;

/**
 * User: johni5
 * Date: 04.09.14
 * Time: 18:39
 */
public class EisFactory {

    private static EisFactory instance;
    private IEisController controller;

    public static EisFactory getInstance() {
        if (instance == null) {
            instance = new EisFactory();
        }
        return instance;
    }

    private EisFactory() {
        controller = new EisControllerImpl();
    }

    public IEisController getController() {
        return controller;
    }

}
