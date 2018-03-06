<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<body>
<h3>userId: ${userId}</h3>
<table>
    <thead>
    <tr>
        <th>name</th>
    </tr>
    </thead>
    <c:forEach items="${partners}" var="partners">
        <jsp:useBean id="partners" scope="page" type="ru.javaproject.model.User"/>
        <tr>
            <td><a href="products?action=see&id=${partners.id}">${partners.name}</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<br>
<br>
<table>
    <thead>
    <tr>
        <th>userId</th>
        <th>fio</th>
        <th>dateBirth</th>
        <th>dateTimeCreate</th>
        <th>phoneNumber</th>
        <th>anInitialFee</th>
        <th>statusOfApplicationParner</th>
        <th>statusOfApplicationRepresentative</th>
    </tr>
    </thead>
    <c:forEach items="${creditapplication}" var="creditapplication">
        <jsp:useBean id="creditapplication" scope="page" type="ru.javaproject.model.CreditApplication"/>
        <tr>
            <td>${creditapplication.userId}</td>
            <td>${creditapplication.fio}</td>
            <td>${creditapplication.dateBirth}</td>
            <td>${creditapplication.dateTimeCreate}</td>
            <td>${creditapplication.phoneNumber}</td>
            <td>${creditapplication.anInitialFee}</td>
            <td>${creditapplication.statusOfApplicationParner}</td>
            <td>${creditapplication.statusOfApplicationRepresentative}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
