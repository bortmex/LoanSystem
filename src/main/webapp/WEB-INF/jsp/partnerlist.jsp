<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 05.03.2018
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="partner.list"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeaderUser.jsp"/>

<section class="jumbotron text-center">
        <h1 class="jumbotron-heading"><h3><spring:message code="select.partner"/></h3></h1>
    <div class="list-inline">
    <c:forEach items="${partners}" var="partners">
        <jsp:useBean id="partners" scope="page" type="ru.javaproject.loansystem.model.User"/>
        <li><a href="<c:url value='/see/${partners.id}'/>" class="list-group-item">${partners.name}</a></li>
    </c:forEach>
</div>
</section>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
