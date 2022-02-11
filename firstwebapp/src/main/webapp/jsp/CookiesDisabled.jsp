
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cookies Warning</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
</head>
<body>
<div class="block">
    cookies are disabled to use this application, <br/>
    enable cookies in the browser
    <form  action="Controller" method="get">
        <input type="hidden" name="command" value="GO_TO_SIGN_IN"/>
        <input class="form_button" type="submit" value="try again"/>
    </form>
</div>
</body>
</html>
