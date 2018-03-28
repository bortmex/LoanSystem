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
<c:set var = "userid" value="${userId}"/>
<h3>userId: ${userid}</h3>

<table>
    <thead>
    <tr>
        <th>name</th>
    </tr>
    </thead>
    <c:forEach items="${partners}" var="partners">
        <jsp:useBean id="partners" scope="page" type="ru.javaproject.loansystem.model.User"/>
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
        <th>questionnaireId</th>
        <th>productList</th>
        <th>statusOfApplicationParner</th>
        <th>statusOfApplicationRepresentative</th>
    </tr>
    </thead>
    <c:forEach items="${creditapplication}" var="creditapplication">
        <jsp:useBean id="creditapplication" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
        <tr>
            <td>${creditapplication.id}</td>
            <td> <c:forEach items="${creditapplication.product}"  var="productCre">
                <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                ${productCre.name} ${productCre.description}
                <c:set var = "productInfo" scope="page" value="${productCre.user.id}"/>
            </c:forEach></td>
            <td>${creditapplication.statusOfApplicationParner}</td>
            <td>${creditapplication.statusOfApplicationRepresentative}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
