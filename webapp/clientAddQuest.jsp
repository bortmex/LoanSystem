<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 25.10.2017
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Клиент</title>
</head>
<body>
<h2><a href="index.jsp">Back</a></h2>
<h2>Partner List</h2>

<table class="item-table">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 80px;">Имя</td>
    </tr>
    </thead>

    <c:forEach var="partner" items="${list}">
        <tr>
            <td><a href="client?action=select&partnerId=<c:out value="${partner.id}"/>">${partner.name}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
