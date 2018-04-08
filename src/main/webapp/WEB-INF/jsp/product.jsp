<%@ page import="java.util.List" %>
<%@ page import="ru.javaproject.loansystem.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="product.title"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<%
    List<Product> products = new ArrayList<>();
%>
<h3><a href="partnerlist"><fmt:message key="common.back"/></a></h3>
<table>
    <thead>
        <tr>
            <th><fmt:message key="product.name"/></th>
            <th><fmt:message key="product.price"/></th>
            <th><fmt:message key="product.description"/></th>
        </tr>
    </thead>
    <c:forEach items="${products}" var="product">
        <jsp:useBean id="product" scope="page" type="ru.javaproject.loansystem.model.Product"/>
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <% Product product1 = new Product(product.getId(), product.getName(), product.getPrice(), product.getDescription());
                products.add(product1);
            %>
        </tr>
    </c:forEach>

</table>
<%
    session.setAttribute("products", products);
%>

<hr>
<a href="<c:url value='/see/create/${partnerId}'/>"><fmt:message key="credapps.create"/></a>
<hr>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
