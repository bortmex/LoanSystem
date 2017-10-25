<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 25.10.2017
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quest List</title>
</head>
<body>
<h2><a href="index.jsp">Home</a></h2>

<h2>Quest List</h2>

<table class="item-table">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 30px;">Пол</td>
        <td style="width: 80px;">Возраст</td>
        <td style="width: 80px;">Доход</td>
        <td style="width: 80px;">Описание продукта</td>
        <td style="width: 80px;">Стоимость продукта</td>
        <td style="width: 80px;">Срок Кредита</td>
        <th style="width: 80px;" colspan=2>Action</th>
    </tr>
    </thead>

    <c:forEach var="quest" items="${list}">
        <tr>
        <td>${quest.isFloor() ? "Ж" : "М"}</td>
        <td> ${quest.age}</td>
        <td> ${quest.income}</td>
        <td> ${quest.productDescription}</td>
        <td> ${quest.productPrice}</td>
        <td> ${quest.credit_term_day}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
