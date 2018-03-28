<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 05.03.2018
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>partner Name: ${partnerName}</h3>
<br>
<br>
<br>
<h3>Table CreditApp</h3>
<table>
    <thead>
    <tr>
        <th>questionnaireFio</th>
        <th>productList</th>
        <th>statusOfApplicationParner</th>
    </tr>
    </thead>
    <c:forEach items="${creditapplication}" var="creditapplication">
        <jsp:useBean id="creditapplication" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
        <tr>
            <td>${creditapplication.fio}</td>
            <td> <c:forEach items="${creditapplication.product}"  var="productCre">
                <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                ${productCre.name} ${productCre.description}
            </c:forEach></td>
            <td>${creditapplication.statusOfApplicationParner}</td>
        </tr>
    </c:forEach>

</table>
<br>
<br>
<br>
<h3>My product</h3>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
    </tr>
    </thead>
    <c:forEach items="${products}" var="products">
        <jsp:useBean id="products" scope="page" type="ru.javaproject.loansystem.model.Product"/>
        <tr>
            <td>${products.name}</td>
            <td>${products.price}</td>
            <td>${products.description}</td>
        </tr>
    </c:forEach>

</table>
    <br>
    <br>
    <br>

    <hr>
    <a href="partnerpage?action=create">Add Product</a>
    <hr>
</body>
</html>
