<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 26.10.2017
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
<h4>Id partnera: </h4>
<c:set var="partnerId" value="${id}"/>
<h4>${partnerId}</h4>

<h2><a href="partner">Back</a></h2>

<form method="post" action="partner">
    <input type="hidden" name="id" value="${partner.id}">
    <input type="hidden" readonly="readonly" name="partnerId"
           value="<c:out value="${partnerId}" />" /> <br />
    <dl>
        <dt>Name: </dt>
        <dd><input type="text" value="${partner.description}" name="description"></dd>
    </dl>
    <dl>
        <dt>Price: </dt>
        <dd><input type="text" value="${partner.price}" size=40 name="price"></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
