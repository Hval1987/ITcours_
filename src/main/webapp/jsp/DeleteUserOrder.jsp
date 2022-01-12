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


        <br>

        <table border="1">
            <tr>
                <td>id</td>

                <td>Transport type</td>

                <td>Booking date</td>




            </tr>

            <c:forEach var="ord" items="${order}">
                <tr>
                    <td><c:out value="${ord.getId()}"/> </td>

                    <td><c:out value="${ord.getTransportType()}"/></td>

                    <td><c:out value="${ord.getDate()}"/></td>

                    <td><form action="Controller" method="get">
                             <input type="hidden" name="command" value="GO_TO_DELETE_ORDER"/>
                             <input type="hidden" name="id" value="${ord.getId()}">
                             <input class="form_button_delete" type="submit" value="Delete order"/>
                             <br>
                         </form>
                        !!!!
                    </td>
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


            </c:forEach>


        </table>


        <br/>
        <a href="Controller?command=GO_TO_MAIN_MENU"> назад </a>
    </h3>

</div>

</body>
</html>
