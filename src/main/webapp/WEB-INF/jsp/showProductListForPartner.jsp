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
<script>
    function get(id, partnerid) {
        $.get('ajax/partner/product/' + id + "/" + partnerid, function(data){
            $(".productId").html(data.description);
            $('#editRowProduct').modal();
        });
    }
</script>
<body>
<jsp:include page="fragments/bodyHeaderPart.jsp"/>

<div id="wrap">
    <header>
        <h1 style="text-align: center"><spring:message code="partner.product"/></h1>
    </header>

    <ul id="products" class="grid clearfix">
        <!-- row 1 -->
        <c:forEach items="${products}" var="products">
            <jsp:useBean id="products" scope="page" type="ru.javaproject.loansystem.model.Product"/>
            <li id="detailsLiProduct" class="clearfix">
                <section class="left">
                    <img src="resources/images/list-default-thumb.png" alt="default thumb" class="thumb">
                    <h3>${products.name}</h3>
                    <span class="meta"><spring:message code="product.id"/> ${products.id}</span>
                </section>

                <section class="right">
                    <span class="price">${products.price} <spring:message code="product.currency"/></span>
                    <span class="darkview">
					<a onclick="get(${products.id}, ${products.user.id})"><img src="resources/images/read-more-btn.png" alt="Read More..."></a>
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
