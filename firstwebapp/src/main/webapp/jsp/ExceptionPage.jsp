<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 23.01.2022
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exception</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<div class="block">
    <h2>Exception !!!!</h2>
    <p>${message}</p>
    <form action="Controller?" method="get" >
        <input type="hidden" name="command" value="GO_TO_MAIN_MENU"/>
        <input class="form_button" type="submit" value="MAIN MENU"/>
    </form>
</div>

</body>
</html>
