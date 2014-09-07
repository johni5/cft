package com.del.cft.test.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:38
 */
public class Classes {

    public static List<Class> getClassesTree(Class eClass) {
        List<Class> result = new ArrayList<Class>();
        do {
            result.add(eClass);
            eClass = eClass.getSuperclass();
        } while (!eClass.equals(Object.class));
        return result;
    }

}
