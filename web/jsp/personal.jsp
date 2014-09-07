<%@ page import="com.del.cft.test.server.Page" %>
<%@ page import="com.del.cft.test.server.actions.RemovePerson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="personalBean" class="com.del.cft.test.server.beans.PersonalBean" scope="request"/>

<c:choose>
    <c:when test="${not empty(personalBean.list)}">
        <table class="common_table">
            <c:forEach var="item" items="${personalBean.list}">
                <tr>
                    <td>
                        <a href="<c:url value="<%=Page.EDIT_PERSON.getUrl()%>"/>?PID=${item.id}">
                                ${item.firstName}<c:if test="${not empty(item.patronymic)}">&nbsp;${item.patronymic}</c:if>&nbsp;${item.lastName}
                        </a>
                    </td>
                    <td>
                        <form name="removeForm" action="<c:url value="<%=Page.PERSONAL.getUrl()%>"/>" method="post">
                            <input type="hidden" name="action" value="<%=RemovePerson._NAME%>">
                            <input type="hidden" name="PID" value="${item.id}">
                            <input type="submit" value="Remove">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when><c:otherwise>
    <h2>No personal yet</h2>
</c:otherwise>
</c:choose>