package com.del.cft.test.utils;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:24
 */
public class Unchecked {

    private Unchecked() {
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T cast(Object value) {
        return (T) value;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T readObject(ObjectInputStream in)
            throws ClassNotFoundException, IOException {
        return (T) in.readObject();
    }
}
