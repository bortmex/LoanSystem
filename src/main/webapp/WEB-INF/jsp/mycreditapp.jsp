<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 07.04.2018
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="credapps.title"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/userUserDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<c:if test="${pageContext.request.queryString=='_addCredApp'}">
    <script type="text/javascript" src="resources/js/successAddProductOrCredApp.js" defer></script>
</c:if>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><spring:message code="users.status_of_application"/></h2>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover display" id="datatablepart">
            <thead>
            <tr>
                <th style="width: 15%"><spring:message code="credapps.id"/></th>
                <th style="width: 25%"><spring:message code="product.title.ordered"/></th>
                <th style="width: 30%"><spring:message code="credapps.status_of_application_parner"/></th>
                <th style="width: 30%"><spring:message code="credapps.status_of_application_representative"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${creditapplication}" var="creditapplication">
                <jsp:useBean id="creditapplication" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
                <tr>
                    <td>${creditapplication.id}</td>
                    <td> <c:forEach items="${creditapplication.product}"  var="productCre">
                        <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                        ${productCre.name} <br>
                        <c:set var = "productInfo" scope="page" value="${productCre.user.id}"/>
                    </c:forEach></td>
                    <c:if test="${creditapplication.statusOfApplicationParner!=null}">
                        <c:if test="${creditapplication.statusOfApplicationParner==true}">
                            <td><img src="<spring:message code="credapps.approved"/>"></td>
                        </c:if>
                        <c:if test="${creditapplication.statusOfApplicationParner==false}">
                            <td><img src="<spring:message code="credapps.rejected"/>"></td>
                        </c:if>
                    </c:if>
                    <c:if test="${creditapplication.statusOfApplicationParner==null}">
                            <td><spring:message code="credapps.under.consideration"/></td>
                    </c:if>

                    <c:if test="${creditapplication.statusOfApplicationRepresentative!=null}">
                        <c:if test="${creditapplication.statusOfApplicationRepresentative==true}">
                            <td><img style="size: 30px" src="<spring:message code="credapps.approved"/>"></td>
                        </c:if>
                        <c:if test="${creditapplication.statusOfApplicationRepresentative==false}">
                            <td><img src="<spring:message code="credapps.rejected"/>"></td>
                        </c:if>
                    </c:if>
                    <c:if test="${creditapplication.statusOfApplicationRepresentative==null}">
                        <c:if test="${creditapplication.statusOfApplicationParner==false}">
                            <td><img src="<spring:message code="credapps.rejected"/>"></td>
                        </c:if>
                        <c:if test="${creditapplication.statusOfApplicationParner==true}">
                            <td><spring:message code="credapps.under.consideration"/></td>
                        </c:if>
                        <c:if test="${creditapplication.statusOfApplicationParner==null}">
                            <td><spring:message code="credapps.under.consideration"/></td>
                        </c:if>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
