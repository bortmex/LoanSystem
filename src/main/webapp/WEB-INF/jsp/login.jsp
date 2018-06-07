<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 27.05.2018
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<style>
    .btn:hover {
        background: #9feba6 !important;
        color: #378f92 !important;
        border: 1px solid #9feba6;
    }
</style>
<body>
<nav class="navbar navbar-default navbar-expand-lg navbar-light">
    <div class="navbar-header d-flex col">
        <a style="cursor: default;" class="navbar-brand" <%--href="#"--%>>Bank<b>Name</b></a>
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse"
                class="navbar-toggle navbar-toggler ml-auto">
            <span class="navbar-toggler-icon"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
    <!-- Collection of nav links, forms, and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
        <ul class="nav navbar-nav">
        </ul>
        <ul class="nav navbar-nav navbar-right ml-auto">
            <li class="nav-item dropdown">
                <a data-toggle="dropdown" class="nav-link dropdown-toggle" href="#"><i class="fa fa-user-o"></i>
                    Login</a>
                <ul class="dropdown-menu">
                    <li>
                        <sec:authorize access="isAnonymous()">
                            <form:form class="form-inline login-form" role="form" action="spring_security_check"
                                       method="post">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input type="text" class="form-control" name="username" placeholder="Username"
                                           required>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input type="password" class="form-control" name="password" placeholder="Password"
                                           required>
                                </div>
                                <button type="submit" id="logingo" class="btn btn-primary">Login</button>
                            </form:form>
                        </sec:authorize>
                    </li>
                </ul>
                <jsp:include page="fragments/localeToggle.jsp"/>
            </li>
        </ul>
    </div>
</nav>

<div class="jumbotron text-center">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message">
                <spring:message code="${param.message}"/>
            </div>
        </c:if>
        <div class="btn-toolbar">
            <div style="text-align: center; margin: 0 auto; float: initial;" class="btn-group">
            <button type="submit" style="
    background:  #29c68c;  color: white; font-size: 15px;" class="btn"
                    onclick="setCredentials('user@yandex.ru', 'password', false)">
                <spring:message code="app.enter"/> User
            </button>

            <button type="submit" style="
    background:  #29c68c;  color: white; font-size: 15px;" class="btn"
                    onclick="setCredentials('admin@gmail.com', 'admin', false)">
                <spring:message code="app.enter"/> Admin
            </button>
            <button type="submit" style="
    background:  #29c68c;  color: white; font-size: 15px;" class="btn"
                    onclick="setCredentials('partner1@yandex.ru', 'partner2', false)">
                <spring:message code="app.enter"/> Partner2
            </button>
            <button type="submit" style="
    background:  #29c68c;  color: white; font-size: 15px;" class="btn"
                    onclick="setCredentials('partner2@yandex.ru', 'partner3', false)">
                <spring:message code="app.enter"/> Partner3
            </button>
            <button type="submit" style="
    background:  #29c68c;  color: white; font-size: 15px;" class="btn"
                    onclick="setCredentials('representative@gmail.com', 'representative', false)">
                <spring:message code="app.enter"/> Representative
            </button>
            <button type="submit" style="
    background:  #29c68c;  color: white; font-size: 15px;" class="btn"
                    onclick="setCredentials('superuser@gmail.com', 'superuser', false)">
                <spring:message code="app.enter"/> Superuser
            </button>
            </div>
        </div>
        <div class="1234234">
            <p> </p>
        </div>
        <p>
            <a type="submit" role="button" style="
    background:  #29c68c;  color: white;" class="btn btn-lg btn-success" href="register">
                <spring:message code="app.register"/>
            </a>
        </p>
        <p>Стек технологий: <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
            <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security
                Test</a>,
            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <a href="http://www.slf4j.org/">SLF4J</a>,
            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
            <a href="http://www.webjars.org/">WebJars</a>,
            <a href="http://datatables.net/">DataTables plugin</a>,
            <a href="http://izitoast.marcelodolce.com//">iziToast</a>,
            <a href="http://ehcache.org">Ehcache</a>,
            <a href="http://www.postgresql.org/">PostgreSQL</a>,
            <a href="http://junit.org/">JUnit</a>,
            <a href="http://jquery.com/">jQuery</a>,
            <a href="http://ned.im/noty/">jQuery notification</a>,
            <a href="http://getbootstrap.com/">Bootstrap</a>.</p>
    </div>
</div>
<div class="container">
    <div class="lead">
        &nbsp;&nbsp;&nbsp;<a href="https://github.com/bortmex/LoanSystem">Java Enterprise проект</a> с
        регистрацией/авторизацией и интерфейсом на основе ролей (USER, ADMIN, PARTNER, REPRESENTATIVE, SUPERUSER).
        Администратор может создавать/редактировать/удалять/пользователей. Пользователь может просматривать
        продукты всех Партнеров и создать заявку на кредит у выбранного партнера, выбрав определенные продукты (Только у выбранного Партнера)
        которые его заинтересовали. Партнер может просматривать свои продукты, создавать продукты, просматривать анкеты адресованные ему от
        пользователей (просмотр для Партнера не полный) и принимать решение об одобрении.
        Представитель банка может создавать Партнеров, просматривать все анкеты (только одобренные Партнерами), а так же
        выносить окончательное решение о выдачи кредита пользователю.
        СуперПользователь может просматривать и редактировать все продукты и анкеты.

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "", true);
    </c:if>

    function setCredentials(username, password, bool) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
        if (bool === false)
            document.getElementById("logingo").click();
    }
</script>
</body>
</html>
