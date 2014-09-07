<%@ page import="com.del.cft.test.server.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<jsp:useBean id="pageManager" class="com.del.cft.test.server.beans.PageManagerBean" scope="request"/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <jsp:include page="../common/header.jsp"/>
</head>
<body>
<div id="root">
    <c:set var="errors" value="${pageManager.errors}"/>
    <c:set var="messages" value="${pageManager.messages}"/>
    <c:if test="${not empty(errors)}">
        <div class="errors_and_messages errors">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:if test="${not empty(messages)}">
        <div class="errors_and_messages messages">
            <ul>
                <c:forEach var="message" items="${messages}">
                    <li>${message}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <div id="main_menu">
        <a href="<c:url value="<%=Page.PERSONAL.getUrl()%>"/>">Personal</a>
        &nbsp;|&nbsp;
        <a href="<c:url value="<%=Page.NEW_PERSON.getUrl()%>"/>">Add new</a>
        <br/>
        <br/>
        <hr/>
        <br/>
    </div>
    <div id="page_content">
        <jsp:include page="${pageManager.jsp}"/>
    </div>
</div>
</body>
</html>
