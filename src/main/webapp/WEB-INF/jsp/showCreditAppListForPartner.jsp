<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://loadsystem.ru/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/partUserDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">

    <form class="form-horizontal" id="detailsPartnerCredForm">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2><spring:message code="credapps.title"/></h2>
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
            </table>
        </div>
    </form>
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
                            <div class="form-control" id="name1" name="name1"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name2" class="control-label col-xs-3"><spring:message code="credapps.fio"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name2" name="name2"></div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name3" class="control-label col-xs-3"><spring:message code="credapps.phone_number"/></label>

                        <div class="col-xs-9">
                            <div class="form-control" id="name3" name="name3"></div>
                        </div>
                    </div>

                   <div class="form-group">
                        <label for="name4" class="control-label col-xs-3"><spring:message code="credapps.products"/></label>

                        <div class="col-xs-9">
                            <textarea style="resize: none; overflow: hidden; background: white"
                                      class="form-control" rows="1" readonly id="name4" name="name4"></textarea>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button id="button1Ok"  name="button1Ok" class="btn btn-success"><spring:message code="partner.approve"/></button>
                    <button id="button2NoOk" name="button2NoOk" class="btn btn-danger"><spring:message code="partner.refuse"/></button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="select.close"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
