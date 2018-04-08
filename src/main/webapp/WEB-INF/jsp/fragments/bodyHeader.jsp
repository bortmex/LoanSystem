<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header>
    <a href="${pageContext.request.contextPath}/">
    <fmt:message key="app.home"/></a>
    &nbsp;|&nbsp;<a href="#">
    <fmt:message key="app.title"/></a>
</header>