package com.del.cft.test.server.managers;

import com.del.cft.test.server.Page;
import com.del.cft.test.server.RequestContext;
import com.del.cft.test.server.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:27
 */
public class PageManager {

    private Page page;
    private List<String> errors = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<String> pullErrors() {
        ArrayList<String> pull = new ArrayList<>(errors);
        errors.clear();
        return pull;
    }

    public void pushError(String error) {
        this.errors.add(error);
    }

    public List<String> pullMessages() {
        ArrayList<String> pull = new ArrayList<>(messages);
        messages.clear();
        return pull;
    }

    public void pushMessage(String message) {
        this.messages.add(message);
    }

    public static PageManager getCurrent() {
        return RequestContext.getCurrent().getVar(PageManager.class, Scope.REQUEST);
    }

}
