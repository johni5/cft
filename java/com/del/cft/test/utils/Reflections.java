package com.del.cft.test.utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:24
 */
public class Reflections {

    public static <T> T tryCreate(Class<T> aClass) {
        try {
            return aClass.newInstance();
        } catch (Exception e) {
            ClientLog.warning(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T tryCreate(Class<T> aClass, Object... args) {
        if (args == null) {
            T obj = tryCreate(aClass);
            if (obj != null) {
                return obj;
            }
        }
        try {
            List<Class> pTytes = new ArrayList<Class>();
            if (args != null) {
                for (Object arg : args) {
                    pTytes.add(arg.getClass());
                }
                Class[] pTypes = pTytes.toArray(new Class[args.length]);
                Constructor<T> constructor = null;
                try {
                    constructor = aClass.getDeclaredConstructor(pTypes);
                } catch (Exception e) {
                    //
                }
                if (constructor == null) {
                    Constructor[] declaredConstructors = aClass.getDeclaredConstructors();
                    for (Constructor declaredConstructor : declaredConstructors) {
                        Class[] declaredPTypes = declaredConstructor.getParameterTypes();
                        if (sameTypes(declaredPTypes, pTypes)) {
                            try {
                                return Unchecked.cast(declaredConstructor.newInstance(args));
                            } catch (Exception e) {
                                //
                            }
                        }
                    }
                } else {
                    return constructor.newInstance(args);
                }
            }
        } catch (Exception e) {
            ClientLog.warning(e.getMessage(), e);
        }
        return null;
    }

    private static boolean sameTypes(Class[] declaredPTypes, Class[] pTypes) {
        if (declaredPTypes == pTypes) {
            return true;
        }
        if (declaredPTypes == null || pTypes == null ||
                declaredPTypes.length != pTypes.length) {
            return false;
        }
        int i = 0;
        for (Class declaredPType : declaredPTypes) {
            Class pType = pTypes[i++];
            if (!declaredPType.isAssignableFrom(pType)) {
                return false;
            }
        }
        return true;
    }


}
