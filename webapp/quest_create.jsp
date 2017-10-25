<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 25.10.2017
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Анкета</title>
</head>
<body>
<h2><a href="client">Back</a></h2>

<h4>Вы хотите взять в кредит: </h4>
<c:set var="product_description" value="${productdescription}"/>
<h4>${product_description}</h4>

<h4>Сумма кредита составит: </h4>
<c:set var="product_price" value="${productprice}"/>
<h4>${product_price}</h4>

<c:set var="productpartnerId" value="${partnerId}"/>

<form method="POST" action='partner' name="frmAddQuest">

    <input type="hidden" readonly="readonly" name="questId"
           value="<c:out value="${partner.id}" />" /> <br />
    <input type="hidden" readonly="readonly" name="productpartnerId"
           value="<c:out value="${productpartnerId}" />" /> <br />
     <input type="hidden" readonly="readonly" name="product_description"
           value="<c:out value="${product_description}" />" /> <br />
     <input type="hidden" readonly="readonly" name="product_price"
           value="<c:out value="${product_price}" />" /> <br />
    FLOOR : <input
        type="text" name="floor"
        value="${partner.floor}" /> <br />
    AGE : <input type="text" name="age"
                         value="<c:out value="${partner.age}" />" /> <br />
    ДОХОД : <input
        type="text" name="income"
        value="<c:out value="${partner.income}" />" /> <br />
    СРОК КРЕДИТА (в днях) : <input
        type="text" name="credit_term_day"
        value="<c:out value="${partner.credit_term_day}" />" /> <br />

    <input
        type="submit" value="Отправить" />

</form>

</body>
</html>
