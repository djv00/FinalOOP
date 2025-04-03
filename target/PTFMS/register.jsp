<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<h2>Register</h2>

<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>

<c:if test="${not empty message}">
    <p style="color: green">${message}</p>
</c:if>

<form method="post" action="register">
    <label for="name">Name:</label>
    <input type="text" name="name" required /><br><br>

    <label for="email">Email:</label>
    <input type="email" name="email" required /><br><br>

    <label for="password">Password:</label>
    <input type="password" name="password" required /><br><br>

    <label for="role">Role:</label>
    <select name="role">
        <option value="Manager">Manager</option>
        <option value="Operator">Operator</option>
    </select><br><br>

    <button type="submit">Register</button>
</form>

<p><a href="login.jsp">Already registered? Login here</a></p>
</body>
</html>
