<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 05.03.2018
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Partner List</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h3><fmt:message key="select.partner"/></h3>
<table>
    <thead>
    <tr>
        <th><fmt:message key="users.name"/></th>
    </tr>
    </thead>
    <c:forEach items="${partners}" var="partners">
        <jsp:useBean id="partners" scope="page" type="ru.javaproject.loansystem.model.User"/>
        <tr>
            <td><a href="<c:url value='/see/${partners.id}'/>">${partners.name}</a></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value='/mycreditapplication'/>"><fmt:message key="credapps.title.status"/></a>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
