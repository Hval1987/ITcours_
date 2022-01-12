<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Orders Info</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<jsp:include page="Header.jsp"/>


<div id="main">

    <h3>

        <%-- <form action="Controller" method="get">
             <input  type="hidden" name="command" value="GO_TO_ADD_ORDER_PAGE"/>
             <input class="form_button_add" type="submit" value="добавить заказ"/>
             <br>
         </form>--%>
        <br>

        <table border="1">
            <tr>
                <td>id</td>

                <td>Transport type</td>

                <td>Booking date</td>

                <td>EmployerID </td>


            </tr>

            <c:forEach var="ord" items="${order}">
                <c:out value="${ord.key.getStatus()}"/>
                <c:out value="${test}"/>
                <c:if test="${ord.key.getStatus() eq 'IN_PROCESS' }" var="test" >
                    <c:out value="${test}"/>

                <tr>
                    <td><c:out value="${ord.key.getId()}"/> </td>

                    <td><c:out value="${ord.key.getIdAssignedCar()}"/></td>

                    <td><c:out value="${ord.value}"/></td>

                    <td><c:out value="${ord.key.getIdEmployer()}"/></td>



                         </td>
                         <td><form action="Controller" method="get">
                             <input type="hidden" name="command" value="CHANGE_STATUS_BY_DRIVER"/>
                             <input type="hidden" name="id" value="${ord.key.getId()}">
                             <input class="form_button_add" type="submit" value="Mark as completed"/>
                             <br>
                         </form>
                             <%--
                         </td>
                         <td><form action="Controller" method="get">
                             <input type="hidden" name="command" value="GO_APPROVE_ORDER"/>
                             <input type="hidden" name="id" value="${ord.getId()}">
                             <input type="submit" value="Process the order"/>
                             <br>
                         </form>
                         </td> --%>

                </tr>

            </c:if>

            </c:forEach>


        </table>


        <br/>
        <a href="Controller?command=GO_TO_MAIN_MENU"> назад </a>
    </h3>

</div>

</body>
</html>
