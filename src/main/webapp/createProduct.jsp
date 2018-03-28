<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 19.03.2018
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product for ${partnerName}</title>
</head>
<body>
<section>

    <h2><a href="partnerpage">Home</a></h2>
    <hr>
    <jsp:useBean id="product" type="ru.javaproject.loansystem.model.Product" scope="request"/>
    <form method="post" action="partnerpage?action=createcomplite">
        <input type="hidden" name="id" value="${product.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${product.name}" name="name"></dd>
        </dl>
        <dl>
            <dt>Price: </dt>
            <dd><input type="number" value="${product.price}" name="price"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${product.description}" name="description"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>

</body>
</html>
