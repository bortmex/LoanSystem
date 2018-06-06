<%@ page import="ru.javaproject.loansystem.AuthorizedUser" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="mytagsjava" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 01.06.2018
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<style type="text/css">
    .error {
        margin-right: 15px !important;
        margin-left: 15px !important;
    }
</style>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div id="wrap">
    <div class="shadow">
        <br/>
        <legend style="text-align:center">${register ? '' : userTo.name} <spring:message
                code="${register ? 'app.register' : 'app.profile'}"/></legend>

        <form:form modelAttribute="userTo" class="form-horizontal" method="post"
                   action="${register ? 'register' : 'profile'}"
                   charset="utf-8" accept-charset="UTF-8">
            <%

                boolean yesreadOnly = false;
                if (AuthorizedUser.safeGet() != null) {
                    int id = AuthorizedUser.get().getId();
                    if (id == 100000 || id == 100002 || id == 100004 || id == 100005 || id == 100006|| id == 100007) {
                        yesreadOnly = true;
                    }
                }
            %>
            <spring:message code="users.name" var="userName"/>
            <mytagsjava:inputField label='${userName}' name="name" read='<%=yesreadOnly%>'/>

            <spring:message code="users.email" var="userEmail"/>
            <mytagsjava:inputField label='${userEmail}' name="email" read='<%=yesreadOnly%>'/>

            <spring:message code="users.password" var="userPassword"/>
            <mytagsjava:inputField label='${userPassword}' name="password" inputType="password"
                                   read='<%=yesreadOnly%>'/>
            <c:if test="${!register}">
                <spring:message code="users.roles" var="userRoles"/>
                <mytagsjava:inputField label='${userRoles}' name="roles" inputType="roles" read='<%=yesreadOnly%>'/>
            </c:if>

            <c:if test="${register}">
                <spring:message code="users.roles" var="userRoles"/>
                <mytagsjava:inputField label='${userRoles}' name="roles" inputType="roles" value="[ROLE_USER]"/>
            </c:if>
            <c:if test='<%=yesreadOnly%>'>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="singlebutton"><spring:message
                            code="common.note"/></label>
                    <div class="col-md-4">
                        <textarea style="resize: none;height:8%;overflow:  hidden;" class="form-control input-md"
                                  readonly><spring:message
                                code="common.note.description"/></textarea>
                    </div>
                </div>
            </c:if>
            <c:if test='<%=!yesreadOnly%>'>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="singlebutton"></label>
                    <div class="col-md-4">
                        <button id="singlebutton" class="btn btn-primary btn-lg btn-block" type="submit"><spring:message
                                code="users.addEdit"/></button>
                    </div>
                </div>
            </c:if>

            <%
                boolean registered = false;
                if (AuthorizedUser.safeGet() != null){
                    registered = true;
                }
            %>
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <c:if test='<%=!registered%>'>
                        <a type="button" class="btn btn-primary btn-lg btn-block" style="cursor: default;" href="login"><spring:message
                                code="common.back"/></a>
                    </c:if>
                </div>
            </div>

        </form:form>
        <br/>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>