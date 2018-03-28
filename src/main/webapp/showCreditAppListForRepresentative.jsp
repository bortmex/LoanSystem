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
<h3>representative Name: ${representativeName}</h3>
<br>
<br>
<br>
<h3>Table All CreditApplication</h3>
<table>
    <thead>
    <tr>
        <th>questionnaireFio</th>
        <th>dateBirth</th>
        <th>dateTimeCreate</th>
        <th>phoneNumber</th>
        <th>anInitialFee</th>
        <th>productList</th>
        <th>statusOfApplicationParner</th>
        <th>statusOfApplicationRepresentative</th>
    </tr>
    </thead>
    <c:forEach items="${creditapplications}" var="creditapplications">
        <jsp:useBean id="creditapplications" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
        <tr>
            <td>${creditapplications.fio}</td>
            <td>${creditapplications.dateBirth}</td>
            <td>${creditapplications.dateTimeCreate}</td>
            <td>${creditapplications.phoneNumber}</td>
            <td>${creditapplications.anInitialFee}</td>
            <td> <c:forEach items="${creditapplications.product}"  var="productCre">
                <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                ${productCre.name} ${productCre.description}
            </c:forEach></td>
            <td>${creditapplications.statusOfApplicationParner}</td>
            <td>${creditapplications.statusOfApplicationRepresentative}</td>
        </tr>
    </c:forEach>

</table>
<br>
<br>
<br>
<h3>All partner</h3>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
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
    <br>
    <br>
    <br>

    <hr>
    <a href="representativepage?action=createpartner">Add Partner</a>
    <hr>
</body>
</html>
