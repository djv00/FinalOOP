<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>User Login</h2>

    <c:if test="${not empty error}">
        <div style="color: red; font-weight: bold;">${error}</div>
    </c:if>

    <form method="post" action="login">
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required /><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required /><br><br>

        <button type="submit">Login</button>
    </form>

    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
</body>
</html>
