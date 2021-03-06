<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add car</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>


<div class="block">

<h1>Add car</h1>
    <div class="message">
        ${requestScope.message}
    </div>
<form  action="Controller" method="get">
    <input type="hidden" name="command" value="CAR_REGISTRATION"/>

    Select the type of car:
    <p class="form_grup" ><select name="typeOfCar">

        <option>truck</option>
        <option>bus</option>
        <option>crane</option>
        <option>car</option>
    </select></p>
   <br>
    Enter the Registration number <br/>
    in the format XXXX XX-X :
    <br>
    <input  class="form_grup" type="text" name="regNumber" value="" required/>
    <br>
    Select a fixed driver:
    <br>
    <p class="form_grup"><select name="driver_name">
    <c:forEach var="driver" items="${listDrivers}">


        <option >${driver.getName()}</option>
    </c:forEach>
    </select></p>


    <br>
    <input class="form_button_add" type="submit" value="Add car"/>
</form>
</div>



</body>
</html>
