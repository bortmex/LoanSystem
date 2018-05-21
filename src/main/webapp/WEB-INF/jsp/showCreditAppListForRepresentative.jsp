<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://loadsystem.ru/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="representative.area"/></title>
    <link rel="stylesheet" href="resources/css/iziToast.min.css">
</head>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/iziToast.min.js" defer></script>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/reprUserDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeaderRepres.jsp"/>

<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><spring:message code="representative.table.allCA"/></h2>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover display" id="detCredAppForm">
            <thead>
            <tr>
                <th><spring:message code="credapps.id"/></th>
                <th><spring:message code="credapps.fio"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${creditapplications}" var="creditapplications">
                <jsp:useBean id="creditapplications" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
                <tr>
                    <td><c:out value="${creditapplications.id}"/></td>
                    <td><c:out value="${creditapplications.fio}"/></td>
                    <td><button type="button" class="btn btn-info" onclick="get(${creditapplications.id})"><spring:message code="credapps.more"/></button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



<div class="modal fade" id="showCredApp">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" id="detailsFormProduct">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><spring:message code="product.description"/></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name1" class="control-label col-xs-3"><spring:message code="credapps.id"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name1" name="name"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name2" class="control-label col-xs-3"><spring:message code="credapps.fio"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name2" name="name"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name3" class="control-label col-xs-3"><spring:message code="credapps.date_birth"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name3" name="name"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name4" class="control-label col-xs-3"><spring:message code="credapps.date_create"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name4" name="name"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name5" class="control-label col-xs-3"><spring:message code="credapps.phone_number"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name5" name="name"></div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="name6" class="control-label col-xs-3"><spring:message code="credapps.products"/></label>

                        <div class="col-xs-9">
                            <%--<div class="form-control" id="name6" name="name"></div>--%>
                                <textarea style="resize: none; overflow: hidden; background: white"
                                          class="form-control" rows="1" readonly id="name6" name="name"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name7" class="control-label col-xs-3"><spring:message code="credapps.an_initial_fee"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name7" name="name"></div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button id="button1Ok" name="button1Ok" class="btn btn-success"><spring:message code="partner.approve"/></button>
                    <button id="button2NoOk" name="button2NoOk" class="btn btn-danger"><spring:message code="partner.refuse"/></button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="select.close"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--<table>
    <thead>
    <tr>
        <th>questionnaireFio</th>
        <th>dateBirth</th>
        <th>dateTimeCreate</th>
        <th>phoneNumber</th>
        <th>anInitialFee</th>
        <th>productList</th>
        <th>statusOfApplicationParner</th>
        <th>statusOfApplicationRepresentative</th>
    </tr>
    </thead>
    <c:forEach items="${creditapplications}" var="creditapplications">
        <jsp:useBean id="creditapplications" scope="page" type="ru.javaproject.loansystem.model.CreditApplication"/>
        <tr>
            <td>${creditapplications.fio}</td>
            <td>${creditapplications.dateBirth}</td>
            <td>${creditapplications.dateTimeCreate}</td>
            <td>${creditapplications.phoneNumber}</td>
            <td>${creditapplications.anInitialFee}</td>
            <td> <c:forEach items="${creditapplications.product}"  var="productCre">
                <jsp:useBean id="productCre" scope="page" type="ru.javaproject.loansystem.model.Product"/>
                ${productCre.name} ${productCre.description}
            </c:forEach></td>
            <td>${creditapplications.statusOfApplicationParner}</td>
            <td>${creditapplications.statusOfApplicationRepresentative}</td>
        </tr>
    </c:forEach>

</table>--%>
<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"credapps.more", "common.enable", "common.dontenable", "common.failed"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>
</html>
