<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myfn" uri="http://loadsystem.ru/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<script src="resources/js/datatablesUtil.js" defer></script>
<script src="resources/js/createCredApp.js" defer></script>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div id="wrap">
    <form class="form-horizontal" id="detForm">

        <input type="hidden" id="listproductid" name="listproductid" autocomplete='tel-national'>
            <!-- Form Name -->
            <legend style="text-align:center"><spring:message code="credapps.create3"/></legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="fio"><spring:message code="credapps.fio"/>:</label>
                <div class="col-md-4">
                    <input id="fio" name="fio" type="text" autocomplete='tel-national' placeholder="" class="form-control input-md">
                    <span style="color: red;" class="help-block" id="help1"></span>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="dateBirth"><spring:message code="credapps.date_birth"/>:</label>
                <div class="col-md-4">
                    <input id="dateBirth" type="date" name="dateBirth" autocomplete='tel-national' placeholder="" class="form-control input-md">
                    <span style="color: red;" class="help-block" id="help5"></span>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="phoneNumber"><spring:message code="credapps.phone_number"/>:</label>
                <div class="col-md-4">
                    <input id="phoneNumber" type="text" value="89" name="phoneNumber" autocomplete='tel-national' placeholder="XXXXXXXXXXX" class="form-control input-md">
                    <span style="color: red;" class="help-block" id="help2"></span>
                </div>
            </div>

            <!-- Select Multiple -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="selectmultiple"><spring:message code="product.title.order"/>:</label>
                <div  class="col-md-4">

                    <select id="selectmultiple" class="selectpicker" multiple data-selected-text-format="count">
                            <c:forEach items="${products}" var="product">
                                <option id="product${product.id}" value="product${product.id}">${product.name}</option>
                            </c:forEach>
                    </select>
                    <span style="color: red;" class="help-block" id="help3"></span>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="anInitialFee"><spring:message code="credapps.an_initial_fee"/>:</label>
                <div  class="col-md-4">
                    <input id="anInitialFee" type="number" name="anInitialFee" autocomplete='tel-national' placeholder="" class="form-control input-md">
                    <span style="color: red;" class="help-block" id="help4"></span>
                </div>
            </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div  class="col-md-4">
                <button style="border-color: #29c68c; background-color: #29c68c; color: white;  margin-top:5px;"  id="singlebutton" name="singlebutton" class="btn btn-primary btn-lg btn-block"><spring:message code="credapps.save"/></button>
            </div>
        </div>
        <hr>
    </form>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

