<%@ page import="ru.javaproject.loansystem.AuthorizedUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 07.04.2018
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="product.my"/></title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<script src="resources/js/showProdListForPartn.js" defer></script>
<%
    Integer id = AuthorizedUser.get().getId();
%>
<script>
    function get(id, partnerid) {
        $.get('ajax/partner/product/' + id + "/" + partnerid, function(data){
            $(".productId").html(data.description);
            $('#editRowProduct').modal();
        });
    }
</script>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<c:if test="${pageContext.request.queryString=='_addProduct'}">
    <script type="text/javascript" src="resources/js/successAddProductOrCredApp.js" defer></script>
</c:if>

<div id="wrap">
    <header>
        <h1 style="text-align: center"><spring:message code="partner.product"/></h1>
    </header>
    <input type='hidden' id=partnerid value="<%=id%>" />
    <ul id="products" class="grid clearfix"></ul>

</div>

<div class="modal fade" id="editRowProduct">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" method="post" id="detailsFormProduct">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><spring:message code="product.description"/></h4>
                </div>
                <div class="modal-body">
                    <div class="productId" style="word-wrap: break-word; "></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="select.close"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
