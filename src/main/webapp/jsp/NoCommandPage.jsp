<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 03.02.2022
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoCommandError!</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body >
<div class="block">
    <div class="message">
        <h2>Ошибка выполнения комманды в контроллере!!!</h2>
        <br/>
        <form action="Controller?" method="get">
        <input type="hidden" name="command" value="GO_TO_MAIN_MENU"/>
        <input class="form_button_delete" type="submit" value="MAIN MENU"/>
        </form>
    </div>
</div>


</body>
</html>
