<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 02.03.2018
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="partner.create"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <jsp:useBean id="user" type="ru.javaproject.loansystem.model.User" scope="request"/>
    <c:url var="addAction" value="/showAllPartnerForRepresentative"/>
    <form:form action="${addAction}" method="post" methodParam="user">
        <dl>
            <dt><fmt:message key="users.name"/>:</dt>
            <dd><input type="text" value="${user.name}" name="name"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="users.email"/>:</dt>
            <dd><input type="text" value="${user.email}" name="email"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="users.password"/>:</dt>
            <dd><input type="text" value="${user.password}" name="password"></dd>
        </dl>
        <button type="submit">Save</button>
    </form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
