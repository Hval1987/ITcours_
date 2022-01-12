<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<jsp:include page="Header.jsp"/>

<div id="main">

<h1 >


</h1>
    <br/>
    <h2 class="introduce">
        Привет <c:out value="${role}"/>!
        <c:out value="${Success}"/>
        <br>

        <p>
            Это моя первая программа , которая использует архитектуру WEB, технологию JDCB, базу данных mySQL,
            принцип послойного построения приложения и собственно сам JAVA :)

        </p>
        <p>
            Я попытался просимулировать работу сайта автопарка, где можно размещать, удалять и
            обрабатывать заказы.
        </p>
        <p>
            Не факт , что все в реальной жизни работает именно так, но это же учебный проект...
        </p>
    </h2>
</div>


</body>

</html>

