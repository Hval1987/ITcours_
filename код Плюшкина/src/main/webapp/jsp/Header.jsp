

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.key.ru" var="key_ru"/>
<fmt:message bundle="${loc}" key="local.key.en" var="key_en"/>

<fmt:message bundle="${loc}" key="local.main.menu" var="key_main_menu"/>
<fmt:message bundle="${loc}" key="local.management" var="key_management"/>
<fmt:message bundle="${loc}" key="local.management.show.all.orders" var="key_management_show_all_car"/>
<fmt:message bundle="${loc}" key="local.management.approve.order" var="key_management_approve_order"/>
<fmt:message bundle="${loc}" key="local.management.delete.order" var="key_management_delete_order"/>
<fmt:message bundle="${loc}" key="local.management.change.ability" var="key_management_change_ability"/>
<fmt:message bundle="${loc}" key="local.show.all.cars" var="key_management_show_all_cars"/>

<fmt:message bundle="${loc}" key="local.driver.menu" var="key_driver_menu"/>
<fmt:message bundle="${loc}" key="local.driver.orders" var="key_driver_order"/>
<fmt:message bundle="${loc}" key="local.driver.breakdown" var="key_driver_breaksown"/>

<fmt:message bundle="${loc}" key="local.employer.menu" var="key_employer_menu"/>
<fmt:message bundle="${loc}" key="local.employer.personal.order" var="key_personal_order"/>
<fmt:message bundle="${loc}" key="local.employer.add.order" var="key_employer_add_order"/>
<fmt:message bundle="${loc}" key="local.employer.delete.order" var="key_employer_delete_order"/>

<fmt:message bundle="${loc}" key="local.signout" var="key_signout"/>






<div id="header">
    <c:out value="${local}"/>
    <c:out value="${key_en}"/>

    <ul>
        <li><a href="Controller?command=GO_TO_MAIN_MENU">${key_main_menu}</a> </li>


        <li> <a>${key_management}  </a>
            <ul>
                <li><a href="Controller?command=SHOW_ALL_ORDERS">${key_management_show_all_car}</a> </li>
                <li> <a href="Controller?command=GO_TO_APPROVE_ORDERS">${key_management_approve_order}</a> </li>
                <li> <a href="Controller?command=GO_TO_DELETE_ORDERS_PAGE">${key_management_delete_order}</a> </li>
                <li> <a href="Controller?command=CHANGE_AVAILABILITY_CAR">${key_management_change_ability}</a> </li>
                <li> <a href="Controller?command=SHOW_ALL_CARS">${key_management_show_all_cars}</a> </li>

            </ul>

        </li>
        <li> <a>${key_driver_menu}</a>
            <ul>
                <li><a href="Controller?command=SHOW_ALL_DRIVER'S_ORDERS">${key_driver_order}</a> </li>
                <li> <a href="Controller?command=CHANGE_AVAILABILITY_CAR">${key_driver_breaksown}</a> </li>

            </ul>

        </li>
        <li> <a>${key_employer_menu}</a>
            <ul>
                <li><a href="Controller?command=SHOW_ALL_ORDERS">${key_personal_order}</a> </li>
                <li> <a href="Controller?command=GO_TO_ADD_ORDER_PAGE">${key_employer_add_order}</a> </li>
                <li> <a href="Controller?command=GO_TO_DELETE_ORDERS_PAGE">Delete personal order</a> </li>

            </ul>

        </li>

        <li> <a href="Controller?command=GO_TO_SIGN_IN"> ${key_signout} </a> </li>

    </ul>
    <div class="language">
        <form  action="Controller" method="get">
            <input type="hidden" name="command" value="CHANGE_LANGUAGE">
            <input type="hidden" name="locale" value="ru">
            <input class="form_button_lang" type="submit" value="${key_ru}" />
        </form>
        <br>
        <form  action="Controller" method="get">
            <input type="hidden" name="command" value="CHANGE_LANGUAGE">
            <input type="hidden" name="locale" value="en">
            <input class="form_button_lang" type="submit" value="${key_en}" />
        </form>
    </div>




</div>


</body>
</html>
