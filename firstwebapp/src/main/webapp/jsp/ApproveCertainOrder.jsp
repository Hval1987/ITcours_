<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 07.01.2022
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Approve order</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<jsp:include page="Header.jsp"/>

<div id="main">
    <br/>

    <h3>
        <br/>


        <table border="1">
            <tr>
                <td>id</td>

                <td>Transport type</td>

                <td>Booking date</td>

                <td>EmployerID </td>

            </tr>

            <tr>
                    <td><c:out value="${order.getId()}"/> </td>

                    <td><c:out value="${order.getTransportType()}"/></td>

                    <td><c:out value="${order.getDate()}"/></td>

                    <td><c:out value="${order.getEmployerId()}"/></td>

                    </td>

                </tr>

        </table>

        <br/>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="GO_TO_ADD_APPROVED_ORDER"/>

            <input type="hidden" name="id_order" value="${order.getId()}">

            <br>
            <h2>
                choose an available car:
            </h2>
            <p class="form_grup"><select name="selected_car" >
            <c:forEach var="car" items="${cars}">



                <option value=${car.getId()} >${car.getTransportType()}         ${car.getRegNumber()}></option>


            </c:forEach>
            </select></p>
            <br/>
            <input class="form_button" type="submit" value="Assign a car"/>
        </form>
        <br/>
        <a href="Controller?command=GO_TO_MAIN_MENU"> назад </a>
    </h3>

</div>


</body>
</html>
