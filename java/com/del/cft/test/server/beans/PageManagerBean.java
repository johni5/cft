package com.del.cft.test.server.beans;

import com.del.cft.test.server.Page;
import com.del.cft.test.server.managers.PageManager;

import java.util.List;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:39
 */
public class PageManagerBean {

    private Page page;

    public PageManagerBean() {
        page = PageManager.getCurrent().getPage();
    }

    public String getUrl() {
        return page.getUrl();
    }

    public String getTitle() {
        return page.getTitle();
    }

    public String getName() {
        return page.getName();
    }

    public String getJsp() {
        return page.getJsp();
    }

    public List<String> getErrors() {
        return pageManager().pullErrors();
    }

    public List<String> getMessages() {
        return pageManager().pullMessages();
    }

    private PageManager pageManager() {
        return PageManager.getCurrent();
    }
}
