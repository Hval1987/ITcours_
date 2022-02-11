<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 26.12.2021
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add Order</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.text.add.order.choose.avto" var="text_choose_avto"/>



<fmt:message bundle="${loc}" key="local.text.add.order.date.order" var="text_date_order"/>
<fmt:message bundle="${loc}" key="local.key.assign" var="key_assign"/>

<div class="block">
    <div class="form_title">
        <h2>
        Order registration
        </h2>
    </div>

<h1 style="color:dodgerblue">
    <c:out value="${message}"/>

</h1>
<br>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ORDER_REGISTRATION"/>
    ${text_choose_avto}
    <p class="form_grup"><select name="type_of_car" required>

        <option>truck</option>
        <option>bus</option>
        <option>crane</option>
        <option>car</option>
    </select></p>
    ${text_date_order}

    <p class="form_grup">
        <br>
        <input type="date" id="date" name="date" required/>
    </p>


        <input class="form_button_add" type="submit" value="${key_assign}"/>
    </form>


</div>



</body>
</html>
