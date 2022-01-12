<%--
  Created by IntelliJ IDEA.
  User: Hval
  Date: 26.12.2021
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Order</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>

<div class="block">
    <div class="form_title">
        <h2>
        Order registration
        </h2>
    </div>

<h1 style="color:dodgerblue">
    <c:out value="${Unsuccess}"/>

</h1>
<br>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="ORDER_REGISTRATION"/>
    Выберите тип авто:
    <p class="form_grup"><select name="type_of_car" required>
        <option>Выберите из списка</option>
        <option>truck</option>
        <option>bus</option>
        <option>crane</option>
        <option>car</option>
    </select></p>
    На какую дату <br> хотите оформить заказ:

<%--    <p>--%>
<%--        <label for="firstname">Имя: </label>--%>
<%--        <input type="text" id="firstname" name="firstname"/>--%>
<%--    </p>--%>
    <p class="form_grup">
        <%--<label for="date"> Дата заказа </label> --%>
        <br>
        <input type="date" id="date" name="date"/>
    </p>


        <input class="form_button_add" type="submit" value="подтвердить заказ"/>
    </form>


</div>



</body>
</html>
