<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <h2 style="color: red;">An Error Occurred</h2>

    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>

    <c:if test="${empty error}">
        <p>Something went wrong. Please try again later.</p>
    </c:if>

    <p><a href="login.jsp">Back to Login</a></p>
</body>
</html>
