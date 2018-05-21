<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 05.03.2018
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="partner.area"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeaderPart.jsp"/>
<c:if test="${pageContext.request.queryString=='_addProduct'}">
    <script type="text/javascript" src="resources/js/successAddProductOrCredApp.js" defer></script>
</c:if>

<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"common.saved"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>
</html>
