<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 19.01.2022
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Registration is success!</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.message.error.delete.car" var="message"/>
<fmt:message bundle="${loc}" key="local.show.all.cars" var="key_show_cars"/>
<div class="block">
    <h3 class="form_title">
        ${message}
    </h3>

    <form method="get" action="Controller" >
        <input type="hidden" name="command"   value="SHOW_ALL_CARS" >
        <input  class="form_button" type="submit" value="${key_show_cars}">
    </form>
</div>
</div>
</body>
</html>

