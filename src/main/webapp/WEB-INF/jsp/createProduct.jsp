<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="product.create"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/prodforuser.js" defer></script>
<body>
<jsp:include page="fragments/bodyHeaderPart.jsp"/>

<div id="wrap">
    <jsp:useBean id="product" type="ru.javaproject.loansystem.model.Product" scope="request"/>
    <form class="form-horizontal" id="detProductForm">
        <input type="hidden" name="id" value="${product.id}">
        <!-- Form Name -->
        <legend style="text-align:center"><spring:message code="product.create"/></legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name"><spring:message code="product.name"/>:</label>
            <div class="col-md-4">
                <input id="name" value="${product.name}" name="name" type="text" placeholder="" class="form-control input-md">
                <span class="help-block">help</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="price"><spring:message code="product.price"/>:</label>
            <div class="col-md-4">
                <input id="price" type="number" value="${product.price}" name="price" placeholder="" class="form-control input-md">
                <span class="help-block">help</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="description"><spring:message code="product.description"/>:</label>
            <div class="col-md-4">
                <textarea style="height:14%;" id="description" value="${product.description}" name="description" placeholder="" class="form-control input-md"></textarea>
                <span class="help-block">help</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" class="btn btn-primary btn-lg btn-block" type="submit"><spring:message code="product.acreate"/></button>
            </div>
        </div>
        <hr>
    </form>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"common.saved","common.failed"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>
</html>
