

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
    <title>Cars info</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<jsp:include page="Header.jsp"/>

<div id="main">


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
                <input class="form_button" type="submit" value="Delete car"/>
                <br>
            </form>
            </td>


            <br/>


        </tr>


    </c:forEach>



</table>
</h3>
</br>
    <form action="Controller" method="get">
        <input type="hidden"   name="command"  value="GO_TO_ADD_CAR_PAGE"/>
        <input class="form_button_add" type="submit" value="Add new car"/>
    </form>

<br/>
 <br>
<a href="index.jsp"> назад </a>
</div>
</body>
</html>

