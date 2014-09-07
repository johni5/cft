package com.del.cft.test.server;

import java.util.Objects;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:25
 */
public enum Page {

    PERSONAL("/personal.html", "Personal", "/jsp/personal.jsp"),
    NEW_PERSON("/create.html", "Add new person", "/jsp/create.jsp"),
    EDIT_PERSON("/edit.html", "Edit info", "/jsp/edit.jsp");

    public static final String TEMPLATE = "/jsp/template/main.jsp";

    private String url;
    private String title;
    private String jsp;

    Page(String url, String title, String jsp) {
        this.url = url;
        this.title = title;
        this.jsp = jsp;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name();
    }

    public String getJsp() {
        return jsp;
    }

    public static Page find(String url) {
        for (Page page : values()) {
            if (Objects.deepEquals(url, page.getUrl())) {
                return page;
            }
        }
        return PERSONAL;
    }


}



