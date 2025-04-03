<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Operator Break Log</title>
</head>
<body>
    <h2>Log Operator Break</h2>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <c:if test="${param.success eq 'true'}">
        <p style="color: green;">Break logged successfully!</p>
    </c:if>

    <form action="BreakServlet" method="post">
        <label for="userId">User ID:</label>
        <input type="number" name="userId" id="userId" required /><br/>

        <label for="vehicleId">Vehicle ID:</label>
        <input type="number" name="vehicleId" id="vehicleId" required /><br/>

        <label for="breakStart">Break Start (yyyy-MM-dd HH:mm:ss):</label>
        <input type="text" name="breakStart" id="breakStart" required /><br/>

        <label for="breakEnd">Break End (yyyy-MM-dd HH:mm:ss):</label>
        <input type="text" name="breakEnd" id="breakEnd" required /><br/>

        <label for="lat">Latitude:</label>
        <input type="text" name="lat" id="lat" required /><br/>

        <label for="lng">Longitude:</label>
        <input type="text" name="lng" id="lng" required /><br/>

        <input type="submit" value="Log Break" />
    </form>
</body>
</html>
