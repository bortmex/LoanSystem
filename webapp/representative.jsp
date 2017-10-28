<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://javaproject.ru/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 26.10.2017
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Все Анкеты Партнеров</title>
</head>
<body>
<h2><a href="index.jsp">Back</a></h2>
<a href="representative?action=create">Partner Create</a>
<table class="item-table">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 80px;">Id Анкеты</td>
        <td style="width: 80px;">Id Партнера</td>
        <td style="width: 80px;">Система Кредитного скролинга</td>
        <td style="width: 80px;">Одобрить?</td>
    </tr>
    </thead>

    <c:forEach var="quest" items="${list}">
        <tr>
            <td>${quest.id}</td>
            <td>${quest.partnerId}</td>
            <td>${fn:getAnswerCreditScrollingSystem(quest)}</td>
            <td><form method="get" action="representative">
                <input type="hidden" name="idforpartner" value="${quest.id}">
                <p><label>
                    <select name="compliteid" size="2" multiple>
                        <option selected value="1">да</option>
                        <option value="2">нет</option>
                    </select>
                </label>
                    <input type="submit" value="Отправить"></p>
            </form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
