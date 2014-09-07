<%@ page import="com.del.cft.test.server.Page" %>
<%@ page import="com.del.cft.test.server.actions.UpdatePerson" %>
<%@ page import="com.del.cft.test.server.forms.NameForm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="editBean" class="com.del.cft.test.server.beans.EditPersonBean" scope="session"/>
<jsp:setProperty name="editBean" property="id" param="PID"/>


<form name="updateForm" action="<c:url value="<%=Page.EDIT_PERSON.getUrl()%>"/>" method="post">
    <input type="hidden" name="action" value="<%=UpdatePerson._NAME%>">
    <input type="hidden" name="<%=NameForm._P_ID%>" value="${editBean.person.id}">

    <ul class="nomarked_list">
        <li>
            <span class="required">*</span><input class="input_common" type="text" placeholder="First Name"
                                                  title="First Name" maxlength="255" name="<%=NameForm._P_NAME_F_NAME%>"
                                                  value="${editBean.person.firstName}">
        </li>
        <li>
            <span class="required"> </span><input class="input_common" type="text" placeholder="Patronymic"
                                                  title="Patronymic"
                                                  maxlength="255" name="<%=NameForm._P_NAME_PAT%>"
                                                  value="${editBean.person.patronymic}">
        </li>
        <li>
            <span class="required">*</span><input class="input_common" type="text" placeholder="Last Name"
                                                  title="Last Name"
                                                  maxlength="255" name="<%=NameForm._P_NAME_L_NAME%>"
                                                  value="${editBean.person.lastName}">
        </li>
        <li>
            <input type="submit" value="Save">
        </li>
    </ul>

</form>