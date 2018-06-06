<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 05.06.2018
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/superusereditproductDatatables.js" defer></script>
<head>
    <title><spring:message code="superuser.title"/></title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><spring:message code="superuser.title.product"/></h2>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover display" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="product.id"/></th>
                <th><spring:message code="product.user.name"/></th>
                <th><spring:message code="product.name"/>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" id="detailsForm" >

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title" id="modalTitle"><spring:message code="superuser.product.edit"/></h2>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group" id="group1">
                        <label for="name" class="control-label col-xs-3"><spring:message code="product.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<spring:message code="product.name"/>">
                        </div>
                    </div>

                    <div class="form-group" id="group2">
                        <label for="price" class="control-label col-xs-3"><spring:message code="product.price"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="price" name="price" placeholder="<spring:message code="product.price"/>">
                        </div>
                    </div>

                    <div class="form-group" id="group3">
                        <label for="description" class="control-label col-xs-3"><spring:message code="product.description"/></label>

                        <div class="col-xs-9">
                            <textarea id="description" name="description" placeholder="<spring:message code="product.description"/>" class="form-control"></textarea>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button id="buttonOk"  name="buttonOk" class="btn btn-success"><spring:message code="common.saveedit"/></button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="select.close"/></button>
                </div>

            </form>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>