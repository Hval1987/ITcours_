<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>

<h1>   hello user</h1>


<form action="Controller" method="get">
    <input type="hidden" name="command" value="USER_SIGN"/>
    <br/>
    <br/>
    login:
    <input type="text" name="login" value=""/>
    <br/>
    <br/>

    password:
    <input type="password" name="password" value=""/>
    <br/>
    <br/>



    <input type="submit" value="нажми меня для подтверждения"/>
</form>
<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="GO_TO_REGISTRATION"/>
    <input type="submit" value="регистрация"/>
</form>


<a href="index.jsp"> назад </a>

</body>
</html>

