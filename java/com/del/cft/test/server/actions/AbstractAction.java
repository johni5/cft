package com.del.cft.test.server.actions;

import com.del.cft.test.server.Page;
import com.del.cft.test.server.RequestContext;
import com.del.cft.test.server.forms.Forms;
import com.del.cft.test.server.forms.IForm;
import com.del.cft.test.server.managers.PageManager;
import com.del.cft.test.utils.Strings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:39
 */
abstract public class AbstractAction implements IAction{

    protected String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    protected HttpServletResponse getResponse() {
        return RequestContext.getCurrent().getResponse();
    }

    protected HttpServletRequest getRequest() {
        return RequestContext.getCurrent().getRequest();
    }

    protected boolean validateAndLoad(IForm form) {
        if (!Forms.load(form)) {
            addError("Please type all required fields");
            return false;
        }
        return true;
    }

    protected boolean validateParams(String... params) {
        HttpServletRequest request = getRequest();
        for (String param : params) {
            String pValue = request.getParameter(param);
            if (Strings.isTrimmedEmpty(pValue)) {
                addError("Please type all required fields");
                return false;
            }
        }
        return true;
    }

    protected void addError(String error) {
        PageManager.getCurrent().pushError(error);
    }

    protected void addMessage(String message) {
        PageManager.getCurrent().pushMessage(message);
    }

    protected void setPage(Page page) {
        PageManager.getCurrent().setPage(page);
    }

}
