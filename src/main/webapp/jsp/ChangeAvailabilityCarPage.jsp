

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
<fmt:message bundle="${loc}" key="local.key.change.availability" var="key_change"/>
<fmt:message bundle="${loc}" key="local.ref.back" var="key_back"/>

<jsp:include page="Header.jsp"/>

<div id="main">
   <br/>
    <table border="1">
        <br/>
        <tr>
            <td>id</td>

            <td>idCharacteristic</td>

            <td>driverId</td>

            <td>regNumber </td>

            <td>Availability </td>

        </tr>

        <c:if test="${role eq 'Dispatcher'}" >

        <c:forEach var="x" items="${allcars}">


            <tr>
                <td><c:out value="${x.getId()}"/></td>

                <td><c:out value="${x.getTransportType()}"/></td>

                <td><c:out value="${x.getDriverId()}"/></td>

                <td><c:out value="${x.getRegNumber()}"/></td>

                <td><c:out value="${x.getAvailable()}"/></td>

                <td><form action="Controller" method="get">
                    <input type="hidden" name="command" value="CHANGE_AVAILABILITY"/>
                    <input type="hidden" name="id" value="${x.getId()}">
                    <input class="form_button" type="submit" value="${key_change}"/>
                    <br>
                </form>
                </td>

            </tr>

        </c:forEach>
        </c:if>


        <c:if test="${role eq 'driver'}" var="test" >

            <c:forEach var="car" items="${allcars}">


                <c:if test="${car.getDriverId()eq userId}" var="testIdDr">

                    <td><c:out value="${car.getId()}"/></td>

                    <td><c:out value="${car.getTransportType()}"/></td>

                    <td><c:out value="${car.getDriverId()}"/></td>

                    <td><c:out value="${car.getRegNumber()}"/></td>

                    <td><c:out value="${car.getAvailable()}"/></td>

                    <td><form action="Controller" method="get">
                        <input type="hidden" name="command" value="CHANGE_AVAILABILITY"/>
                        <input type="hidden" name="id" value="${car.getId()}">
                        <input class="form_button" type="submit" value="${key_change}"/>
                        <br>
                    </form>
                    </td>

                    <br/>


                </tr>
             </c:if>


            </c:forEach>
        </c:if>




    </table>
    </h3>


    <br/>
    <br>
    <a href="index.jsp"> ${key_back} </a>
</div>
</body>
</html>


