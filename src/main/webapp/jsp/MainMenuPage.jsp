<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.text.introduce" var="int_text"/>
<jsp:include page="Header.jsp"/>

<div id="main">

<h1 >


</h1>
    <br/>
    <h2 class="introduce">
        Привет <c:out value="${role}"/>!
        <c:out value="${in_text}"/>
        <c:out value="${Success}"/>
        <br>
        ${int_text}



    </h2>

</div>


</body>

</html>

