<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Client: alexa
  Date: 25.10.2017
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quest List</title>
</head>
<body>
<h2><a href="index.jsp">Home</a></h2>

<h2>Quest List</h2>

<h3>partnerId: ${id}</h3>

<table class="item-table">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 30px;">Пол</td>
        <td style="width: 80px;">Возраст</td>
        <td style="width: 80px;">Доход</td>
        <td style="width: 80px;">Описание продукта</td>
        <td style="width: 80px;">Стоимость продукта</td>
        <td style="width: 80px;">Срок Кредита</td>
        <td style="width: 80px;">Статус одобрения партнера</td>
        <td style="width: 80px;">Статус одобрения представителя банка</td>
        <th style="width: 80px;">Action</th>
        <th style="width: 80px;">Action</th>
    </tr>
    </thead>

    <c:forEach var="quest" items="${list}">
        <tr>
        <td>${quest.floor}</td>
        <td> ${quest.age}</td>
        <td> ${quest.income}</td>
        <td> ${quest.productDescription}</td>
        <td> ${quest.productPrice}</td>
        <td> ${quest.credit_term_day}</td>
        <td> ${quest.decisionPartner}</td>
        <td> ${quest.decisionRepresentative}</td>
        <td><a href="partner?action=forrepar&idforrepar=${quest.id}">GoForReper</a></td>
            <td><form method="get" action="partner">
                <input type="hidden" name="idforclient" value="${quest.id}">
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

<a href="partner?action=createproduct&partnerid=<c:out value="${id}"/>">Product Create</a>

</body>
</html>
