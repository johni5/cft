package com.del.cft.test.server;

import com.del.cft.test.utils.Reflections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:23
 */
public class RequestContext {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private static final ThreadLocal<RequestContext> instance =
            new ThreadLocal<RequestContext>();

    private RequestContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public <T> T getVar(Class<T> varClass, Scope scope) {
        String name = "WEB_VAR_" + varClass.getName();
        return getBean(name, varClass, scope);
    }

    public <T> T getBean(String name, Class<T> beanClass, Scope scope) {
        return scope.get(name, beanClass, getRequest());
    }

    public static RequestContext initialize(HttpServletRequest request,
                                            HttpServletResponse response) {
        RequestContext ctx = new RequestContext(request, response);
        instance.set(ctx);
        return ctx;
    }

    public static RequestContext getCurrent() {
        return instance.get();
    }

    public static void destroy() {
        instance.remove();
    }

}
