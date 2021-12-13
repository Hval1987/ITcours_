
<%--
  Created by IntelliJ IDEA.
  User: Валера
  Date: 12.12.2021
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>input name</title>
</head>
<body>

<form action="Controller" method="get">
    name:
    <input type="hidden" name="command" value="DATA_FOR_REGISTRATION"/>
    <p style="margin-left: 50px"></p>
    <input type="text" name="name" value=""/>

    <br/>
    <br/>

    surname:
    <p style="margin-left: 50px"></p>
    <input type="text" name="surname" value=""/>
    <br/>
    <br/>
    phone number:
    <p style="margin-left: 50px"></p>
    <input type="text" name="phone number" value=""/>
    <br/>
    <br/>

    password:
    <p style="margin-left: 50px"></p>
    <input type="password" name="password" value=""/>
    <p style="margin-left: 50px"></p>

    <input type="submit" value="нажми для подтверждения"/>

</form>
<a href="index.jsp"> назад </a>

</body>
</html>

