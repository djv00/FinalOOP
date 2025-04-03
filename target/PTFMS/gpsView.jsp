<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GPS Tracking Report</title>
</head>
<body>
    <h2>Vehicle GPS Tracking Report</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form method="get" action="controller">
        <input type="hidden" name="action" value="gpsReport" />
        <label for="vehicleId">Enter Vehicle ID:</label>
        <input type="number" name="vehicleId" required />
        <button type="submit">View Logs</button>
    </form>

    <c:if test="${not empty logs}">
        <table border="1">
            <thead>
                <tr>
                    <th>Station</th>
                    <th>Arrival Time</th>
                    <th>Departure Time</th>
                    <th>At Final Station?</th>
                    <th>Timestamp</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="log" items="${logs}">
                    <tr>
                        <td>${log.stationName}</td>
                        <td>${log.arrivalTime}</td>
                        <td>${log.departureTime}</td>
                        <td><c:out value="${log.atFinalStation ? 'Yes' : 'No'}" /></td>
                        <td>${log.timestamp}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <p><a href="controller?action=managerHome">Back to Home</a></p>
</body>
</html>
