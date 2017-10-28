<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Client: alexa
  Date: 25.10.2017
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h2><a href="client">Back</a></h2>

<h2>Product List </h2>
<c:set var="namePartner" value="${name}"/>
<h4>${namePartner}</h4>
<table class="item-table">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 30px;">Название</td>
        <td style="width: 80px;">Цена</td>
        <th style="width: 80px;">Action</th>
    </tr>
    </thead>

    <c:forEach var="product" items="${list}">
        <tr>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><a href="partner?action=create&productpartnerid=<c:out value="${product.partnerId}"/>&product_description=<c:out value="${product.description}"/>&product_price=<c:out value="${product.price}"/>">Оформать анкету на кредит</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
