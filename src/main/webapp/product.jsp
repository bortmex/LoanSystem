<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 02.03.2018
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product list</title>
</head>
<body>
<h1>TABLE</h1>
<hr>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>partnerId</th>
        </tr>
    </thead>
    <c:forEach items="${products}" var="product">
        <jsp:useBean id="product" scope="page" type="ru.javaproject.model.Product"/>
        <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.partnerId}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
