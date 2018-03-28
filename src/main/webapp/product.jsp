<%@ page import="java.util.List" %>
<%@ page import="ru.javaproject.loansystem.model.Product" %>
<%@ page import="java.util.ArrayList" %><%--
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
<h3>partnerName: ${partnerName}</h3>
<hr>

<%
    List<Product> products = new ArrayList<>();
%>
<h2><a href="products">Back</a></h2>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>description</th>
        </tr>
    </thead>
    <c:forEach items="${products}" var="product">
        <jsp:useBean id="product" scope="page" type="ru.javaproject.loansystem.model.Product"/>
        <tr>
            <td>${product.id}</td>
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
<a href="products?action=see&use=create&id=${partnerId}">Create Credit Application</a>
<hr>

</body>
</html>
