<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 07.04.2018
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="credapps.my"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h3><a href="showCreditAppListAndProductListForPartner"><fmt:message key="common.back"/></a></h3>

<table>
    <thead>
    <tr>
        <th><fmt:message key="credapps.fio"/></th>
        <th><fmt:message key="product.title.ordered"/></th>
        <th><fmt:message key="credapps.status_of_application_parner"/></th>
    </tr>
    </thead>
    <c:forEach items="${creditapplication}" var="creditapplication">
        <jsp:useBean id="creditapplication" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
        <tr>
            <td>${creditapplication.fio}</td>
            <td> <c:forEach items="${creditapplication.product}"  var="productCre">
                <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                ${productCre.name}<br>
            </c:forEach></td>
            <td>${creditapplication.statusOfApplicationParner}</td>
        </tr>
    </c:forEach>

</table>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
