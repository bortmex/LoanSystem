<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 07.04.2018
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="credapps.title"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h3><a href="partnerlist"><fmt:message key="common.back"/></a></h3>
<table>
    <thead>
    <tr>
        <th><fmt:message key="credapps.id"/></th>
        <th><fmt:message key="product.title.ordered"/></th>
        <th><fmt:message key="credapps.status_of_application_parner"/></th>
        <th><fmt:message key="credapps.status_of_application_representative"/></th>
    </tr>
    </thead>
    <c:forEach items="${creditapplication}" var="creditapplication">
        <jsp:useBean id="creditapplication" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
        <tr>
            <td>${creditapplication.id}</td>
            <td> <c:forEach items="${creditapplication.product}"  var="productCre">
                <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                ${productCre.name} <br>
                <c:set var = "productInfo" scope="page" value="${productCre.user.id}"/>
            </c:forEach></td>
            <td>${creditapplication.statusOfApplicationParner}</td>
            <td>${creditapplication.statusOfApplicationRepresentative}</td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
