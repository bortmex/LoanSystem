<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<body>
<nav class="navbar navbar-default navbar-expand-lg navbar-light">
    <div class="navbar-header d-flex col">
        <a class="navbar-brand" href="#">Bank<b>Name</b></a>
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
            <span class="navbar-toggler-icon"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
    <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
        <ul class="nav navbar-nav">    <%--/rest/profile/credapp/partner/showCreditAppListForPartner--%>
            <li class="nav-item"><a href="<c:url value='/showCreditAppListForPartner'/>" class="nav-link"><spring:message code="credapps.forpartner"/></a></li>
            <li class="nav-item"><a href="<c:url value='/showproductlistforpartner'/>" class="nav-link"><spring:message code="product.my"/></a></li>
            <li class="nav-item"><a href="<c:url value='/createredproduct'/>" class="nav-link"><spring:message code="product.create"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right ml-auto">
            <li class="nav-item dropdown">
                <a data-toggle="dropdown" class="nav-link dropdown-toggle" href="#"><i class="fa fa-user-o"></i> Login</a>
                <ul class="dropdown-menu">
                    <li>
                        <form class="form-inline login-form" action="#" method="post">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" class="form-control" placeholder="Username" required>
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input type="text" class="form-control" placeholder="Password" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Login</button>
                        </form>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>