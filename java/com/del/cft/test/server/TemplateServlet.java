package com.del.cft.test.server;

import com.del.cft.test.CommonException;
import com.del.cft.test.server.actions.IAction;
import com.del.cft.test.server.managers.ActionRegister;
import com.del.cft.test.server.managers.PageManager;
import com.del.cft.test.utils.ClientLog;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:14
 */
public class TemplateServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        ClientLog.info("******   Web container is starting   ******");
        Locale.setDefault(ServerConfig.LOCALE);
        TimeZone.setDefault(ServerConfig.TIME_ZONE);
        ClientLog.info("Default locale: " + Locale.getDefault());
        ClientLog.info("Default time zone: " + TimeZone.getDefault());
    }

    @Override
    public void destroy() {
        ClientLog.info("******   Web container is stopped   ******");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void handleRequest(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        RequestContext.initialize(request, response);
        try {
            Page page = Page.find(request.getServletPath());
            ClientLog.info("Handle request: " + page.getUrl());
            PageManager.getCurrent().setPage(page);
            IAction action = ActionRegister.getInstance().detect(request.getParameter("action"), page);
            if (action != null) {
                try {
                    action.handle();
                } catch (Throwable e) {
                    ClientLog.error("Handle action error", e);
                }
            }
            request.getSession().getServletContext().getRequestDispatcher(
                    Page.TEMPLATE + "?timestamp=" + System.currentTimeMillis()).forward(request, response);
        } finally {
            RequestContext.destroy();
        }
    }
}
