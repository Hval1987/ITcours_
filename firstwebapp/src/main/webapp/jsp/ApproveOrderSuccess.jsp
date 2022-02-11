<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>Approve Order Successfull</title>
    <base href="firstwebapp_war">

    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
</head>
<body>
<div class="block">

  <h1 class="form_grup"
      class="form_title">
    Approve Order Successfull

  </h1>
<form action="/../firstwebapp_war/Controller" method="get">
    <input type="hidden" name="command" value="GO_TO_APPROVE_ORDERS"/>

    <input  class="form_button_add " type="submit" value="Order approval page"/>
    <br>
</form>
</div>

</body>
</html>
