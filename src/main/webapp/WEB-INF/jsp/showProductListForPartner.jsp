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
    <title><fmt:message key="product.my"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<h3><a href="showCreditAppListAndProductListForPartner"><fmt:message key="common.back"/></a></h3>
<table>
    <thead>
    <tr>
        <th><fmt:message key="product.name"/></th>
        <th><fmt:message key="product.price"/></th>
        <th><fmt:message key="product.description"/></th>
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
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
