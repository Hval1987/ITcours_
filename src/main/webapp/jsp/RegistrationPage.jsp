
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>input name</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body >
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.key.ru" var="key_ru"/>
<fmt:message bundle="${loc}" key="local.key.en" var="key_en"/>


<fmt:message bundle="${loc}" key="local.text.login" var="text_login"/>
<fmt:message bundle="${loc}" key="local.text.name" var="text_name"/>
<fmt:message bundle="${loc}" key="local.text.emale" var="text_emale"/>
<fmt:message bundle="${loc}" key="local.text.passport" var="text_passport"/>
<fmt:message bundle="${loc}" key="local.text.password" var="key_password"/>
<fmt:message bundle="${loc}" key="local.key.assign" var="text_assign"/>

<fmt:message bundle="${loc}" key="local.ref.back" var="ref_back"/>




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
    <h1 class="form_title"> Registration </h1>
    <div class="message">
        ${message}
    </div>

<form action="Controller" method="get">

    <input type="hidden" name="command" value="REGISTRATION"/>
    ${text_login}:
    <br/>


    <input  class="form_grup" type="text" name="login" value="" placeholder="from 4 to 15 latin character"/>

    <br/>
    ${text_name}:
    <br/>

    <input  class="form_grup" type="text" name="name" value="" placeholder="in Russian characters"/>
    <br>


    ${text_emale}:
    <br/>

    <input  class="form_grup" type="email" name="emale" value=""placeholder="enter email"/>
    <br>

    Choose the type of your record:

    <p  class="form_grup"><select name="access type">

        <option value="1">employer</option>
        <option value="2">driver</option>
        <option value="3">dispatcher</option>

    </select></p>

    ${text_passport}:
    <br/>
    <input  class="form_grup" type="text" name="passportNumber" value="" placeholder="XX(ser)XXXXXXX(num)"/>

    <br>
    ${key_password}:
    <br/>
    <input  class="form_grup" type="password" name="userPassword" value=""placeholder="latin characters & numbers"/>
    <br>

    <input class="form_button" type="submit" value="${text_assign}"/>
    <br/>

</form>
<a href="index.jsp"> ${ref_back} </a>
</div>


</body>
</html>

