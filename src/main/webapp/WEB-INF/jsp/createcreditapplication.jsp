<%@ page import="java.util.Collection" %>
<%--
Created by IntelliJ IDEA.
  User: alexa
  Date: 02.03.2018
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://loadsystem.ru/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="credapps.create"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>

    <jsp:useBean id="creditApplication" type="ru.javaproject.loansystem.model.CreditApplication" scope="request"/>
    <c:url var="addAction" value="/creditApplication/add"/>
    <form:form action="${addAction}" modelAttribute="creditApplication">
        <input type="hidden" name="partnerId" value="${partnerId}">
        <input type="hidden" name="idcrapp" value="${creditApplication.id}">
        <dl>
            <dt><fmt:message key="credapps.fio"/>:</dt>
            <dd><input type="text" value="${creditApplication.fio}" name="fio"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="credapps.date_birth"/>:</dt>
            <dd><input type="date" value="${creditApplication.dateBirth}" name="dateBirth"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="credapps.phone_number"/>:</dt>
            <dd><input type="text" value="${creditApplication.phoneNumber}" name="phoneNumber"></dd>
        </dl>
            <c:forEach items="${products}" var="product">
                <jsp:useBean id="product" scope="page" type="ru.javaproject.loansystem.model.Product"/>
        <dl>
                        <c:if test="${productsTrue==null}">
                            <dt>${product.name} </dt>
                            <dd><input type="checkbox" name="product${product.id}"></dd>
                        </c:if>
                        <c:if test="${productsTrue!=null}">
                            <dt>${product.name} </dt>
                            <c:set var = "yes" scope="page" value="false"/>

                            <jsp:useBean id="productSelect" class="java.util.HashSet" scope="page">
                                <c:forEach items="${productsTrue}" var="productTrue">
                                    <%
                                    productSelect.addAll((Collection) request.getAttribute("productsTrue"));
                                    %>
                                </c:forEach>
                            </jsp:useBean>

                            <c:set var = "yes" scope="page" value="${myfn:contains(productSelect,product)}"/>
                        <c:if test="${!yes}">
                            <dd><input type="checkbox" name="product${product.id}"></dd>
                        </c:if>
                        <c:if test="${yes}">
                            <dd><input type="checkbox" checked name="product${product.id}"></dd>
                        </c:if>
                        </c:if>
        </dl>
            </c:forEach>
        <dl>
            <dt><fmt:message key="credapps.an_initial_fee"/>:</dt>
            <dd><input type="number" value="${creditApplication.anInitialFee}" name="anInitialFee"></dd>
        </dl>
        <button type="submit">Save</button>
    </form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
