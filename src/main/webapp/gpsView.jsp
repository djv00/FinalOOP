<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>GPS View - Vehicle Tracking</title>
</head>
<body>
    <h2>Vehicle GPS Logs</h2>

    <form action="GpsServlet" method="get">
        <label for="vehicleId">Enter Vehicle ID:</label>
        <input type="number" name="vehicleId" id="vehicleId" required />
        <input type="submit" value="View Logs" />
    </form>

    <c:if test="${not empty logs}">
        <h3>GPS Logs for Vehicle ID: ${param.vehicleId}</h3>
        <table border="1">
            <tr>
                <th>Timestamp</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Station</th>
                <th>Arrival</th>
                <th>Departure</th>
                <th>Final Station</th>
            </tr>
            <c:forEach var="log" items="${logs}">
                <tr>
                    <td><c:out value="${log.timestamp}"/></td>
                    <td><c:out value="${log.latitude}"/></td>
                    <td><c:out value="${log.longitude}"/></td>
                    <td><c:out value="${log.stationName}"/></td>
                    <td><c:out value="${log.arrivalTime}"/></td>
                    <td><c:out value="${log.departureTime}"/></td>
                    <td><c:out value="${log.atFinalStation}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty logs && not empty param.vehicleId}">
        <p>No GPS logs found for vehicle ID: ${param.vehicleId}</p>
    </c:if>
</body>
</html>
