<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <form method="post" action="authorization">
            <spring:message code="app.login"/>: <select name="userId">
            <option value="100000" selected>User</option>
            <option value="100002">Admin</option>
            <option value="100004">Partner2</option>
            <option value="100005">Partner3</option>
            <option value="100006">Representative</option>
        </select>
            <button type="submit"><spring:message code="common.select"/></button>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
