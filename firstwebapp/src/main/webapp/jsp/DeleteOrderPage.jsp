<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Orders Info</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.key.delete.order" var="key_delete"/>
<jsp:include page="Header.jsp"/>

<div id="main">
    <br>

        <br>

        <table border="1">
            <tr>
                <td>id</td>

                <td>Transport type</td>

                <td>Booking date</td>

            </tr>
            <!-- меню удаления заказов для  диспетчера -->

            <c:if test="${role eq 'Dispatcher'}" >

            <c:forEach var="ord" items="${order}">
                <tr>
                    <td><c:out value="${ord.getId()}"/> </td>

                    <td><c:out value="${ord.getTransportType()}"/></td>

                    <td><c:out value="${ord.getDate()}"/></td>

                    <td><form action="Controller" method="post">
                             <input type="hidden" name="command" value="GO_TO_DELETE_ORDER"/>
                             <input type="hidden" name="id" value="${ord.getId()}">
                             <input class="form_button_delete" type="submit" value="${key_delete}"/>
                             <br>
                         </form>

                    </td>

                </tr>

            </c:forEach>

            </c:if>

            <!-- удаление заказов для пользователя -->

            <c:if test="${role eq 'Employer'}" >
            <c:forEach var="ord" items="${order}">
                <c:if test="${userId eq ord.getEmployerId()}">
                <tr>
                    <td><c:out value="${ord.getId()}"/> </td>

                    <td><c:out value="${ord.getTransportType()}"/></td>

                    <td><c:out value="${ord.getDate()}"/></td>

                    <td><form action="Controller" method="post">
                        <input type="hidden" name="command" value="GO_TO_DELETE_ORDER"/>
                        <input type="hidden" name="id" value="${ord.getId()}">
                        <input class="form_button_delete" type="submit" value="Delete order"/>
                        <br>
                    </form>
                    </td>

                </tr>

                </c:if>

            </c:forEach>

            </c:if>



        </table>


        <br/>
        <a href="Controller?command=GO_TO_MAIN_MENU"> назад </a>

</div>

</body>
</html>
