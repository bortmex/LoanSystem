<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 26.10.2017
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Partner</title>
</head>
<body>
<h2><a href="representative.jsp">Back</a></h2>
<form method="post" action="representative">
    <input type="hidden" name="id" value="${representative.id}">
    <dl>
        <dt>Name: </dt>
        <dd><input type="text" value="${representative.name}" name="name"></dd>
    </dl>
    <dl>
        <dt>Email: </dt>
        <dd><input type="text" value="${representative.email}" size=40 name="email"></dd>
    </dl>
    <dl>
        <dt>Password:</dt>
        <dd><input type="text" value="${representative.password}" name="password"></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
