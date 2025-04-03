<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log Break</title>
</head>
<body>
    <h2>Log a Break</h2>

    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>
    <c:if test="${param.success eq 'true'}">
        <p style="color: green">Break successfully logged!</p>
    </c:if>

    <form method="post" action="controller?action=logBreak">
        <label for="vehicleId">Vehicle ID:</label>
        <input type="number" name="vehicleId" required /><br><br>

        <label for="breakStart">Break Start Time (yyyy-MM-dd HH:mm:ss):</label>
        <input type="text" name="breakStart" required /><br><br>

        <label for="breakEnd">Break End Time (yyyy-MM-dd HH:mm:ss):</label>
        <input type="text" name="breakEnd" required /><br><br>

        <label for="lat">Latitude:</label>
        <input type="text" name="lat" required /><br><br>

        <label for="lng">Longitude:</label>
        <input type="text" name="lng" required /><br><br>

        <!-- userId is auto-populated from session -->
        <input type="hidden" name="userId" value="${sessionScope.user.id}" />

        <button type="submit">Submit Break</button>
    </form>

    <p><a href="controller?action=operatorHome">Back to Home</a></p>
</body>
</html>
