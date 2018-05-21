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
<jsp:include page="fragments/bodyHeaderUser.jsp"/>

<div id="wrap">
    <form class="form-horizontal" id="detForm">
        <jsp:useBean id="creditApplication" type="ru.javaproject.loansystem.model.CreditApplication" scope="request"/>

        <input type="text" hidden="hidden" id="listproductid" name="listproductid" autocomplete='tel-national'>
            <!-- Form Name -->
            <legend style="text-align:center"><spring:message code="credapps.create3"/></legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="fio"><spring:message code="credapps.fio"/>:</label>
                <div class="col-md-4">
                    <input id="fio" value="${creditApplication.fio}" name="fio" type="text" autocomplete='tel-national' placeholder="" class="form-control input-md">
                    <span class="help-block">help</span>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="databirth"><spring:message code="credapps.date_birth"/>:</label>
                <div class="col-md-4">
                    <input id="databirth" type="date" value="${creditApplication.dateBirth}" name="databirth" autocomplete='tel-national' placeholder="" class="form-control input-md">
                    <span class="help-block">help</span>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="phonenumber"><spring:message code="credapps.phone_number"/>:</label>
                <div class="col-md-4">
                    <input id="phonenumber" type="text" value="${creditApplication.phoneNumber}" name="phonenumber" autocomplete='tel-national' placeholder="XXXXXXXXXXX" class="form-control input-md">
                    <span class="help-block">help</span>
                </div>
            </div>

            <!-- Select Multiple -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="selectmultiple"><spring:message code="product.title.order"/>:</label>
                <div class="col-md-4">

                    <select id="selectmultiple" class="selectpicker" multiple data-selected-text-format="count">
                            <c:forEach items="${products}" var="product">
                                <option id="product${product.id}" value="product${product.id}">${product.name}</option>
                            </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="aninitialfee"><spring:message code="credapps.an_initial_fee"/>:</label>
                <div class="col-md-4">
                    <input id="aninitialfee" type="number" value="${creditApplication.anInitialFee}" name="aninitialfee" autocomplete='tel-national' placeholder="" class="form-control input-md">
                    <span class="help-block">help</span>
                </div>
            </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-primary btn-lg btn-block"><spring:message code="credapps.create"/></button>
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





<%--<section>

    <jsp:useBean id="creditApplication" type="ru.javaproject.loansystem.model.CreditApplication" scope="request"/>
    <c:url var="addAction" value="/creditApplication/add"/>
    <form:form action="${addAction}" modelAttribute="creditApplication">
        <input type="hidden" name="partnerId" value="${partnerId}">
        <input type="hidden" name="idcrapp" value="${creditApplication.id}">
        <dl>
            <dt><spring:message code="credapps.fio"/>:</dt>
            <dd><input type="text" value="${creditApplication.fio}" name="fio"></dd>
        </dl>
        <dl>
            <dt><spring:message code="credapps.date_birth"/>:</dt>
            <dd><input type="date" value="${creditApplication.dateBirth}" name="dateBirth"></dd>
        </dl>
        <dl>
            <dt><spring:message code="credapps.phone_number"/>:</dt>
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
            <dt><spring:message code="credapps.an_initial_fee"/>:</dt>
            <dd><input type="number" value="${creditApplication.anInitialFee}" name="anInitialFee"></dd>
        </dl>
        <button type="submit">Save</button>
    </form:form>
</section>--%>
