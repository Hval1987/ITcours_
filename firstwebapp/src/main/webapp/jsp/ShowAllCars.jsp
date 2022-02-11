

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
    <title>Cars info</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.key.add.car" var="key_add"/>
<fmt:message bundle="${loc}" key="local.key.delete.car" var="key_delete"/>
<jsp:include page="Header.jsp"/>
<br/>
<div id="main">
    <br/>
    <br/>

 <table border="1">
    <tr>
        <td>id</td>

        <td>idCharacteristic</td>

        <td>driverId</td>

        <td>regNumber </td>
    </tr>

    <c:forEach var="x" items="${allcars}">
        <tr>
            <td><c:out value="${x.getId()}"/></td>

            <td><c:out value="${x.getTransportType()}"/></td>

            <td><c:out value="${x.getDriverId()}"/></td>

            <td><c:out value="${x.getRegNumber()}"/></td>

            <td><form action="Controller" method="get">
                <input type="hidden" name="command" value="GO_TO_DELETE_CAR"/>
                <input type="hidden" name="id" value="${x.getId()}">
                <input class="form_button" type="submit" value="${key_delete}"/>
                <br>
            </form>
            </td>

        </tr>


    </c:forEach>



</table>
</h3>
</br>
    <form action="Controller" method="get">
        <input type="hidden"   name="command"  value="GO_TO_ADD_CAR_PAGE"/>
        <input class="form_button_add" type="submit" value="${key_add}"/>
    </form>

<br/>
 <br>
<a href="index.jsp"> назад </a>
</div>
</body>
</html>

