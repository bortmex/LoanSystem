<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="product.create"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <hr>
    <jsp:useBean id="product" type="ru.javaproject.loansystem.model.Product" scope="request"/>
    <c:url var="addAction" value="/partner/add"/>
    <form:form action="${addAction}" method="post" methodParam="product">
        <input type="hidden" name="id" value="${product.id}">
        <dl>
            <dt><fmt:message key="product.name"/>:</dt>
            <dd><input type="text" value="${product.name}" name="name"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="product.price"/>: </dt>
            <dd><input type="number" value="${product.price}" name="price"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="product.description"/>:</dt>
            <dd><input type="text" value="${product.description}" name="description"></dd>
        </dl>
        <button type="submit">Save</button>
    </form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
