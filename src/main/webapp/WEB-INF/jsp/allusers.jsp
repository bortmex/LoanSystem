<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/userDatatables.js" defer></script>
<head>
    <title><spring:message code="users.title"/></title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><spring:message code="users.title"/></h2>
                </div>
                <div class="col-sm-6">
                    <a onclick="add()" class="btn btn-default"><i class="material-icons">&#xE147;</i> <span><spring:message code="users.add"/></span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover display" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="users.name"/></th>
                <th><spring:message code="users.email"/></th>
                <th><spring:message code="users.roles"/></th>
                <th><spring:message code="users.registered"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="ru.javaproject.loansystem.model.User"/>
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                    <td>${user.roles}</td>
                    <td><fmt:formatDate type="date" value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                    <td><a class="btn btn-xs edit" id="${user.id}">
                        <span class="material-icons" aria-hidden="true">&#xE254;</span>
                    </a></td>
                    <td><a class="btn btn-xs delete" onclick="deleteRow(${user.id})">
                        <span class="material-icons" aria-hidden="true">&#xE872;</span>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="users.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3"><spring:message code="users.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="users.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><spring:message code="users.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="users.email"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><spring:message code="users.password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="<spring:message code="users.password"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="role1" class="control-label col-xs-3"><spring:message code="users.roles.user"/></label>

                        <div class="col-xs-9">
                            <input type="radio" class="form-control" id="role1" name="role" placeholder="<spring:message code="users.roles.user"/>">
                        </div>
                        <label for="role2" class="control-label col-xs-3"><spring:message code="users.roles.partner"/></label>

                        <div class="col-xs-9">
                            <input type="radio" class="form-control" id="role2" name="role"  placeholder="<spring:message code="users.roles.partner"/>">
                        </div>
                        <label for="role3" class="control-label col-xs-3"><spring:message code="users.roles.representative"/></label>

                        <div class="col-xs-9">
                            <input type="radio" class="form-control" id="role3" name="role"  placeholder="<spring:message code="users.roles.representative"/>">
                        </div>

                        <div>
                            <input type="hidden" id="roles" name="roles"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
