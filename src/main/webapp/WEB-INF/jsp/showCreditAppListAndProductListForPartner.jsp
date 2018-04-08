<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <title><fmt:message key="partner.area"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<a href="<c:url value='/showCreditAppListForPartner'/>"><fmt:message key="credapps.forpartner"/></a>
<br>
<a href="<c:url value='/showproductlistforpartner'/>"><fmt:message key="product.my"/></a>
<br>
<a href="<c:url value='/crateredproduct'/>"><fmt:message key="product.create"/></a>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
