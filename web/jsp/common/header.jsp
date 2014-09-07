<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="pageManager" class="com.del.cft.test.server.beans.PageManagerBean" scope="request"/>

<META NAME="TITLE" CONTENT=""/>
<META NAME="DESCRIPTION" CONTENT=""/>
<META NAME="KEYWORDS" CONTENT=""/>

<meta http-equiv="Cache-control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">

<title>CFT Test app -- ${pageManager.title}</title>

<%--<link rel="shortcut icon" href="<c:url value="/c/favicon.ico"/>">--%>
<%--JAVA SCRIPT--%>
<script type="text/javascript" src="<c:url value="/c/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/c/js/page.js"/>"></script>

<%--CSS TABLE STYLES--%>
<link href="<c:url value="/c/css/default.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/c/css/page.css"/>" rel="stylesheet" type="text/css"/>
