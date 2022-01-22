<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>

</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.key.ru" var="key_ru"/>
<fmt:message bundle="${loc}" key="local.key.en" var="key_en"/>

<fmt:message bundle="${loc}" key="local.text.login" var="text_login"/>
<fmt:message bundle="${loc}" key="local.text.password" var="text_password"/>
<fmt:message bundle="${loc}" key="local.text.message" var="text_message"/>
<fmt:message bundle="${loc}" key="local.key.signin" var="sign_in_key"/>
<fmt:message bundle="${loc}" key="local.key.registration" var="registration_key" />


<div class="block">
    <div class="language">
        <form  action="Controller" method="get">
            <input type="hidden" name="command" value="CHANGE_LANGUAGE">
            <input type="hidden" name="locale" value="ru">
            <input class="form_button_lang" type="submit" value="${key_ru}" />
        </form>
        <br>
        <form  action="Controller" method="get">
            <input type="hidden" name="command" value="CHANGE_LANGUAGE">
            <input type="hidden" name="locale" value="en">
            <input class="form_button_lang" type="submit" value="${key_en}" />
        </form>
    </div>


<h1 class="form_title">${text_message}
    <br>
</h1>
<h5 class="message">
 <c:out value="${Unsuccess}"/>
</h5>

<br>

<form  action="Controller" method="get">
    <input type="hidden" name="command" value="USER_SIGN_IN"/>


    ${text_login}:
    <br>
    <div class="form_grup">
        <input type="text" name="login" value=""/>
    </div>



    ${text_password}:
    <br>
    <div class="form_grup">
        <input type="password" name="password" value=""/>

    </div>



 <div>
    <input type="submit" class="form_button" value="${sign_in_key}"/>
 </div>
</form>



<form class="form_button2" action="Controller" method="get">
    <div>
    <input type="hidden" name="command" value="GO_TO_REGISTRATION"/>
    </div>
    <div>
    <input class="form_button" type="submit" value="${registration_key}"/>
    </div>
</form>



</div>

</body>
</html>

