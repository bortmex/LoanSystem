<%@ page import="ru.javaproject.loansystem.AuthorizedUser" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-default navbar-expand-lg navbar-light">
    <div class="navbar-header d-flex col">

        <a style="cursor: default;" class="navbar-brand" <%--href="#"--%>>Bank<b>Name</b></a>
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse"
                class="navbar-toggle navbar-toggler ml-auto">
            <span class="navbar-toggler-icon"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
    <!-- Collection of nav links, forms, and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
        <sec:authorize access="isAuthenticated()">
        <form:form  class="navbar-form" action="logout" method="post">
            <sec:authorize access="hasRole('ROLE_USER')">
            <ul class="nav navbar-nav navbar-left">
                <li class="nav-item"><a href="<c:url value='/partnerlists'/>" class="nav-link"><spring:message
                        code="partner.list"/></a></li>
                <li class="nav-item"><a href="<c:url value='/mycreditapplication'/>" class="nav-link"><spring:message
                        code="credapps.title.status"/></a></li>
            </ul>
            </sec:authorize>
                <sec:authorize access="hasRole('ROLE_PARTNER')">
                <ul class="nav navbar-nav">
                    <li class="nav-item"><a href="<c:url value='/showCreditAppListForPartner'/>"
                                            class="nav-link"><spring:message code="credapps.forpartner"/></a></li>
                    <li class="nav-item"><a href="<c:url value='/showproductlistforpartner'/>"
                                            class="nav-link"><spring:message code="product.my"/></a></li>
                    <li class="nav-item"><a href="<c:url value='/createredproduct'/>"
                                            class="nav-link"><spring:message code="product.create"/></a></li>
                </ul>

                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_REPRESENTATIVE')">
                <ul class="nav navbar-nav">
                    <li class="nav-item"><a href="<c:url value='/showcreditapplistforrepresentative'/>"
                                            class="nav-link"><spring:message code="credapps.title"/></a></li>
                    <li class="nav-item"><a href="<c:url value='/showAllPartnerForRepresentative'/>"
                                            class="nav-link"><spring:message code="representative.allpartner"/></a></li>
                </ul>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <ul class="nav navbar-nav">
                    <li class="nav-item"><a href="<c:url value='/users'/>" class="nav-link"><spring:message
                            code="users.list"/></a></li>
                </ul>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SUPERUSER')">
                <ul class="nav navbar-nav">
                    <li class="nav-item"><a href="<c:url value='/superEditCredApp'/>" class="nav-link"><spring:message
                            code="superuser.credapp"/></a></li>
                    <li class="nav-item"><a href="<c:url value='/superEditProduct'/>" class="nav-link"><spring:message
                            code="superuser.products"/></a></li>
                </ul>
                </sec:authorize>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="profile">${userTo.name} <spring:message code="app.profile"/></a></li>
                <jsp:include page="localeToggle.jsp"/>
                <li class="active"><button style="background-color: #29c68c; color: white;  margin-top:5px;" class="btn btn-default" type="submit">
                    <span aria-hidden="true">Logout</span>
                </button> <span aria-hidden="true"></span></li>
            </ul>
            </form:form>
        </sec:authorize>
    </div>
</nav>