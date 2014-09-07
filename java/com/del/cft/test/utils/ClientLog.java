package com.del.cft.test.utils;

import org.apache.log4j.Logger;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:11
 */
public class ClientLog {

    private static final Logger log = Logger.getLogger(ClientLog.class);

    public static void info(String m) {
        log.info(message(m));
    }

    public static void info(String m, Throwable t) {
        log.info(message(m), t);
    }

    public static void error(String m) {
        log.error(message(m));
    }

    public static void error(String m, Throwable t) {
        log.error(message(m), t);
    }

    public static void warning(String m) {
        log.warn(message(m));
    }

    public static void warning(String m, Throwable t) {
        log.warn(message(m), t);
    }

    private static String message(String m) {
        return " >> " + m;
    }
}
