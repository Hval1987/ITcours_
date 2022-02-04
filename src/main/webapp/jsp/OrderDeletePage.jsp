<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 05.01.2022
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <title>Delete order</title>
</head>
<body>
<jsp:include page="Header.jsp"/>


<div id="main">

    <h3>

        <br>

        <table border="1">
            <tr>
                <td>id</td>

                <td>Transport typr</td>

                <td>Booking date</td>

                <td>EmployerID </td>


            </tr>

            <c:forEach var="ord" items="${order}">
                <tr>
                    <td><c:out value="${ord.getId()}"/> </td>

                    <td><c:out value="${ord.getTransportType()}"/></td>

                    <td><c:out value="${ord.getDate()}"/></td>

                    <td><c:out value="${ord.getEmployerId()}"/></td>



                    </td>
                    <td><form action="Controller" method="get">
                        <input type="hidden" name="command" value="GO_TO_DELETE_ORDER"/>
                        <input type="hidden" name="id" value="${ord.getId()}">
                        <input class="form_button_delete" type="submit" value="Delete order"/>
                        <br>
                    </form>
                    </td>


                </tr>


            </c:forEach>


        </table>


        <br/>
        <a href="Controller?command=GO_TO_MAIN_MENU"> назад </a>
    </h3>

</div>


</body>
</html>
