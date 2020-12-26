<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login page</title>
</head>
<body>
<form method="post" id="loginform" action="login">
    <input name="username" type="text"><br><p>username</p>
    <input name="password" type="password"><br><p>password</p>
    <input name="email" type="email"><p>email</p>
    <input type="submit">
</form>
</body>
</html>