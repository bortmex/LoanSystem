<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="representative.area"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<h3><fmt:message key="representative.name"/>: ${representativeName}</h3>
<br>
<h3><fmt:message key="representative.table.allCA"/></h3>
<table>
    <thead>
    <tr>
        <th>questionnaireFio</th>
        <th>dateBirth</th>
        <th>dateTimeCreate</th>
        <th>phoneNumber</th>
        <th>anInitialFee</th>
        <th>productList</th>
        <th>statusOfApplicationParner</th>
        <th>statusOfApplicationRepresentative</th>
    </tr>
    </thead>
    <c:forEach items="${creditapplications}" var="creditapplications">
        <jsp:useBean id="creditapplications" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
        <tr>
            <td>${creditapplications.fio}</td>
            <td>${creditapplications.dateBirth}</td>
            <td>${creditapplications.dateTimeCreate}</td>
            <td>${creditapplications.phoneNumber}</td>
            <td>${creditapplications.anInitialFee}</td>
            <td> <c:forEach items="${creditapplications.product}"  var="productCre">
                <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                ${productCre.name} ${productCre.description}
            </c:forEach></td>
            <td>${creditapplications.statusOfApplicationParner}</td>
            <td>${creditapplications.statusOfApplicationRepresentative}</td>
        </tr>
    </c:forEach>

</table>

<a href="<c:url value='/showAllPartnerForRepresentative'/>"><fmt:message key="representative.allpartner"/></a>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
