package com.del.cft.test.server.forms;

import com.del.cft.test.server.RequestContext;
import com.del.cft.test.utils.Classes;
import com.del.cft.test.utils.ClientLog;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:37
 */
public class Forms {

    public static boolean load(IForm form) {
        List<ParamSetter> paramSetters = searchParamSetters(form.getClass());
        HttpServletRequest request = RequestContext.getCurrent().getRequest();
        boolean ok = true;
        for (ParamSetter paramSetter : paramSetters) {
            RequestParameter pMeta = paramSetter.parameter;
            String p = request.getParameter(pMeta.name());
            if (!pMeta.optional() || (p != null && !p.isEmpty())) {
                if (p == null || p.isEmpty() ||
                        (pMeta.max() > -1 && p.length() > pMeta.max()) ||
                        (pMeta.min() > -1 && p.length() < pMeta.min())) {
                    ok = false;
                } else {
                    try {
                        paramSetter.setter.invoke(form, p);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        ClientLog.warning("Cant load form parameter: " + p, e);
                        ok = false;
                    }
                }
            }
        }
        return ok;
    }

    @SuppressWarnings({"unchecked"})
    static private List<ParamSetter> searchParamSetters(Class aClass) {
        List<ParamSetter> result = new ArrayList<>();
        for (Class eClass : Classes.getClassesTree(aClass)) {
            for (Field field : eClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(RequestParameter.class)) {
                    RequestParameter rParam = field.getAnnotation(RequestParameter.class);
                    String fName = field.getName();
                    String mName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
                    try {
                        Method setter = eClass.getDeclaredMethod("set" + mName, field.getType());
                        result.add(new ParamSetter(rParam, setter));
                    } catch (NoSuchMethodException e) {
                        ClientLog.warning(e.getMessage(), e);
                    }
                }
            }
        }
        return result;
    }


    static class ParamSetter {
        RequestParameter parameter;
        Method setter;

        ParamSetter(RequestParameter parameter, Method setter) {
            this.parameter = parameter;
            this.setter = setter;
        }
    }


}
