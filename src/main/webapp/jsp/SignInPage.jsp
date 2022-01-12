<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>

</head>
<body>
<div class="block">


<h1 class="form_title">HELLO
    <br>
</h1>
<h5 class="message">
 <c:out value="${Unsuccess}"/>
</h5>

<br>

<form  action="Controller" method="get">
    <input type="hidden" name="command" value="USER_SIGN_IN"/>


    login:
    <div class="form_grup">
        <input type="text" name="login" value=""/>
    </div>



    password:
    <div class="form_grup">
        <input type="password" name="password" value=""/>

    </div>



 <div>
    <input type="submit" class="form_button" value="SIGN IN"/>
 </div>
</form>



<form class="form_button2" action="Controller" method="get">
    <div>
    <input type="hidden" name="command" value="GO_TO_REGISTRATION"/>
    </div>
    <div>
    <input class="form_button" type="submit" value="REGISTRATION"/>
    </div>
</form>



</div>

</body>
</html>

