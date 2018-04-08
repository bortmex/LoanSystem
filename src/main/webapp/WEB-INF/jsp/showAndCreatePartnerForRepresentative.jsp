<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 07.04.2018
  Time: 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<h3><fmt:message key="representative.allpartner"/></h3>
<table>
    <thead>
    <tr>
        <th><fmt:message key="users.name"/></th>
        <th><fmt:message key="users.email"/></th>
    </tr>
    </thead>
    <c:forEach items="${partnersForRep}" var="partnersForRep">
        <jsp:useBean id="partnersForRep" scope="page" type="ru.javaproject.loansystem.model.User"/>
        <tr>
            <td>${partnersForRep.name}</td>
            <td>${partnersForRep.email}</td>
        </tr>
    </c:forEach>

</table>
<a href="<c:url value='/rest/profile/partner/create'/>"><fmt:message key="representative.add.partner"/></a>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
