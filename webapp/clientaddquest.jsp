<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://javaproject.ru/functions" %>
<%--
  Created by IntelliJ IDEA.
  Client: alexa
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

<h3>clientId: ${id}</h3>

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

<h3>Мои анкеты:</h3>

<table class="item-table">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 80px;">Id Анкеты</td>
        <td style="width: 80px;">Товар</td>
        <td style="width: 80px;">Статус анкеты</td>
    </tr>
    </thead>

    <c:forEach var="quest" items="${listz}">
        <tr>
            <td>${quest.id}</td>
            <td>${quest.productDescription}</td>
            <td>${fn:getStatisForClientAnket(quest)}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
