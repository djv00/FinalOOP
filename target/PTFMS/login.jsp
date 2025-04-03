<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form method="post" action="login">
    <label for="email">Email:</label>
    <input type="email" name="email" required /><br><br>

    <label for="password">Password:</label>
    <input type="password" name="password" required /><br><br>

    <button type="submit">Login</button>
</form>

<p><a href="register.jsp">Don't have an account? Register here</a></p>
</body>
</html>
