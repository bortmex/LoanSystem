<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<hr>
<div class="footer">
    <div class="container">
        <spring:message code="app.footer"/>
    </div>
</div>

<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"users.edit", "product.currency", "product.id","credapps.more",
    "common.enable", "common.dontenable", "common.date.birthday", "credapps.more",
    "common.note","common.note.description","common.deleted","common.saved","common.deleted.admin",
    "common.deleted.partner","common.deleted.user","superuser.products.error","common.deleted.representative","common.deleted.superuser", "products.datetime.duplicate", "common.enabled","common.status", "common.disabled"}%>'>
    i18n['${key}'] = '<spring:message code="${key}"/>';
    </c:forEach>
</script>