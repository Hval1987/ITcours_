<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 11.01.2022
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="header">

    <ul>
        <li><a href="Controller?command=GO_TO_MAIN_MENU">Main menu</a> </li>


        <li> <a>Management  </a>
            <ul>
                <li><a href="Controller?command=SHOW_ALL_ORDERS">Show all orders</a> </li>
                <li> <a href="Controller?command=GO_TO_APPROVE_ORDERS">Approve order  </a> </li>
                <li> <a href="Controller?command=GO_TO_DELETE_ORDERS_PAGE">Delete order   </a> </li>
                <li> <a href="Controller?command=CHANGE_AVAILABILITY_CAR">Change the availability </a> </li>
                <li> <a href="Controller?command=SHOW_ALL_CARS">Show all cars </a> </li>

            </ul>

        </li>
        <li> <a>Driver's menu </a>
            <ul>
                <li><a href="Controller?command=SHOW_ALL_DRIVER'S_ORDERS">Your orders</a> </li>
                <li> <a href="Controller?command=CHANGE_AVAILABILITY_CAR">Mark a breakdown   </a> </li>

            </ul>

        </li>
        <li> <a>Employer's menu </a>
            <ul>
                <li><a href="Controller?command=SHOW_ALL_ORDERS">View the personal orders</a> </li>
                <li> <a href="Controller?command=GO_TO_ADD_ORDER_PAGE">Add order </a> </li>
                <li> <a href="Controller?command=GO_TO_DELETE_USERS_ORDERS">Delete personal order</a> </li>

            </ul>

        </li>

        <li> <a href="Controller?command=GO_TO_SIGN_IN"> Sign out </a> </li>

    </ul>

</div>

</body>
</html>
