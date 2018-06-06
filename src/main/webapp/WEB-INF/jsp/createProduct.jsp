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
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/prodforuser.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div id="wrap">
    <form:form  class="form-horizontal" id="detProductForm">
        <!-- Form Name -->
        <legend style="text-align:center"><spring:message code="product.create"/></legend>

        <input type="hidden" name="id">

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="name"><spring:message code="product.name"/>:</label>
            <div class="col-md-4">
                <input id="name" name="name" type="text" placeholder="" class="form-control input-md">
                <span style="color: red;" class="help-block" id="help1"></span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="price"><spring:message code="product.price"/>:</label>
            <div class="col-md-4">
                <input id="price" type="number" name="price" placeholder="" class="form-control input-md">
                <span style="color: red;" class="help-block" id="help2"></span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="description"><spring:message code="product.description"/>:</label>
            <div class="col-md-4">
                <textarea style="height:14%;" id="description" name="description" placeholder="" class="form-control input-md"></textarea>
                <span style="color: red;" class="help-block" id="help3"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button style="border-color: #29c68c; background-color: #29c68c; color: white;  margin-top:5px;" id="singlebutton" class="btn btn-primary btn-lg btn-block" type="submit"><spring:message code="product.acreate"/></button>
            </div>
        </div>
        <hr>
    </form:form>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
