<%@ page import="ru.javaproject.loansystem.model.Product" %>
<%@ page import="ru.javaproject.loansystem.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<style>
    .black-background {
        background-color: #b6bdbe;
        border: none;
        color: black;
        padding: 7px 16px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 2px 1px;
        cursor: pointer;
    }


    .black-background:hover {
        background-color: #378f92;
    }

</style>
<script>
    function get(id, partnerid) {
        $.get('ajax/user/product/' + id + "/" + partnerid, function (data) {
            /*$('#detailsFormProduct').find("p[name='productGetId']").val(data.description);*/
            $(".productGetId").html(data.description);
            $('#editRowProduct').modal();
        });
    }
</script>
<%--<head>
    <title><spring:message code="product.title"/></title>
</head>--%>

<body>
<jsp:include page="fragments/bodyHeaderUser.jsp"/>

<div id="wrap">
    <header>
        <span class="list-style-buttons">
        <%--<a id="gridview" class="switcher" href="<c:url value='/see/create/${partnerId}'/>"><img src="<spring:message code="apply.online"/>" alt=<spring:message code="credapps.create"/>></a>--%>
        <a id="gridview" class="switcher" href="<c:url value='/see/create/${partnerId}'/>"><input type="submit"
                                                                                                  class="btn btn-primary black-background"
                                                                                                  value="<spring:message code="credapps.create"/>"/></a>
        </span>
        <h1><spring:message code="users.products"/> ${partnerName}</h1>
    </header>

    <ul id="products" class="grid clearfix">
        <!-- row 1 -->
        <c:forEach items="${products}" var="product">
            <jsp:useBean id="product" scope="page" type="ru.javaproject.loansystem.model.Product"/>
            <li id="detailsLiProduct" class="clearfix">
                <section class="left">
                    <img src="resources/images/list-default-thumb.png" alt="default thumb" class="thumb">
                    <h3>${product.name}</h3>
                    <span class="meta"><spring:message code="product.id"/>${product.id}</span>
                </section>

                <section class="right">
                    <span class="price">${product.price} <spring:message code="product.currency"/></span>
                    <span class="darkview">
					<a onclick="get(${product.id}, ${product.user.id})"><img src="resources/images/read-more-btn.png"
                                                                             alt="Read More..."></a>
					</span>
                </section>
            </li>

        </c:forEach>
    </ul>

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
                    <div class="productGetId" style="word-wrap: break-word; "></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                            code="select.close"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
