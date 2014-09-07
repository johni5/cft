<%@ page import="com.del.cft.test.server.Page" %>
<%@ page import="com.del.cft.test.server.actions.CreatePerson" %>
<%@ page import="com.del.cft.test.server.forms.NameForm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="createBean" class="com.del.cft.test.server.beans.CreatePersonBean" scope="session"/>

<form name="createForm" action="<c:url value="<%=Page.PERSONAL.getUrl()%>"/>" method="post">
    <input type="hidden" name="action" value="<%=CreatePerson._NAME%>">

    <ul class="nomarked_list">
        <li>
            <span class="required">*</span><input class="input_common" type="text" placeholder="First Name"
                                                  title="First Name"
                                                  maxlength="255" name="<%=NameForm._P_NAME_F_NAME%>"
                                                  value="${createBean.form.firstName}">
        </li>
        <li>
            <span class="required"> </span><input class="input_common" type="text" placeholder="Patronymic"
                                                  title="Patronymic"
                                                  maxlength="255" name="<%=NameForm._P_NAME_PAT%>"
                                                  value="${createBean.form.patronymic}">
        </li>
        <li>
            <span class="required">*</span><input class="input_common" type="text" placeholder="Last Name"
                                                  title="Last Name"
                                                  maxlength="255" name="<%=NameForm._P_NAME_L_NAME%>"
                                                  value="${createBean.form.lastName}">
        </li>
        <li>
            <input type="submit" value="Create">
        </li>
    </ul>

</form>