<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="name" required="true" description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" description="Field label" %>
<%@ attribute name="value" required="false" description="Input type" %>
<%@ attribute name="read" type="java.lang.Boolean" required="false" description="Input type" %>
<%@ attribute name="inputType" required="false" description="Input type" %>

<spring:bind path="${name}">
    <div class="form-group ${status.error ? 'error' : '' }">
        <label class="control-label col-sm-2 col-md-4">${label}</label>

        <div class="col-sm-3 col-md-4">
    <c:if test="${empty value}" >
            <c:choose>
                <c:when test="${inputType == 'password'}"><form:password path="${name}" class="col-md-4 form-control" readonly="${read}"/></c:when>
                <c:when test="${inputType == 'roles'}"><form:input readonly="true" class="col-md-4 form-control" path="${name}" value="${roles}"/></c:when>
                <c:otherwise><form:input path="${name}" class="col-md-4 form-control" readonly="${read}"/></c:otherwise>
            </c:choose>
    </c:if>

<c:if test="${not empty value}" >
    <c:choose>
    <c:when test="${inputType == 'password'}"><form:password path="${name}" readonly="${read}" class="col-md-4 form-control"/></c:when>
    <c:when test="${inputType == 'roles'}"><form:input readonly="true" class="col-md-4 form-control" path="${name}" value="${value}"/></c:when>
        <c:otherwise><form:input path="${name}" class="col-md-4 form-control" readonly="${read}"/></c:otherwise>
    </c:choose>
</c:if>
        </div>
        <div class="col-sm-4 col-md-4">
            <span class="help-inline">${status.errorMessage}</span>
        </div>
    </div>
</spring:bind>