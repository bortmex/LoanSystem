<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <meta charset="UTF-8">
    <title>LoanSystem</title>
    <script type="text/javascript" src="date_time.js"></script>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <form method="post" action="authorization">
        <fmt:message key="app.login"/>: <select name="userId">
        <option value="100000">User</option>
        <option value="100002">Admin</option>
        <option value="100004">Partner2</option>
        <option value="100005">Partner3</option>
        <option value="100006">Representative</option>
    </select>
        <button type="submit"><fmt:message key="common.select"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
