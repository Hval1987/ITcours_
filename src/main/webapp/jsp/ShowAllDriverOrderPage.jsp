<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title>Orders Info</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.key.set.complited" var="key_set_complited"/>
<jsp:include page="Header.jsp"/>


<div id="main">
    <h3>
        <br>

        <table border="1">
            <tr>
                <td>id</td>

                <td>Booking date</td>

                <td>EmployerID </td>


            </tr>

            <c:forEach var="ord" items="${order}">

                <c:if test="${ord.key.getStatus() eq 'IN_PROCESS' }" var="test" >
                    <br/>
                <tr>
                    <td><c:out value="${ord.key.getId()}"/> </td>


                    <td><c:out value="${ord.value}"/></td>

                    <td><c:out value="${ord.key.getIdEmployer()}"/></td>



                         </td>
                         <td><form action="Controller" method="get">
                             <input type="hidden" name="command" value="CHANGE_STATUS_BY_DRIVER"/>
                             <input type="hidden" name="id" value="${ord.key.getId()}">
                             <input class="form_button_add" type="submit" value="${key_set_complited}"/>
                             <br>
                         </form>


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
