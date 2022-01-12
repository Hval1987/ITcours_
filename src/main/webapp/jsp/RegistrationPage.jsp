
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>input name</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body >
<div class="block">
    <h1 class="form_title"> Registration </h1>
    <h3 class="message">

    <c:out value="${Unsuccess}"/>
    </h3>
    <br/>




<form action="Controller" method="get">

    <input type="hidden" name="command" value="REGISTRATION"/>
    name:
    <br/>

    <input  class="form_grup" type="text" name="name" value=""/>

    <br/>


    emale:
    <br/>

    <input  class="form_grup" type="email" name="emale" value=""/>
    <br>

    Выберите тип вашей записи:

    <p  class="form_grup"><select name="access type">

        <option value="1">employer</option>
        <option value="2">driver</option>
        <option value="3">dispatcher</option>

    </select></p>

    passport number:
    <br/>
    <input  class="form_grup" type="text" name="passportNumber" value=""/>

    <br>
    password:
    <br/>
    <input  class="form_grup" type="password" name="userPassword" value=""/>

    <input class="form_button" type="submit" value="нажми для подтверждения"/>
    <br/>

</form>
<a href="index.jsp"> назад </a>
</div>


</body>
</html>

