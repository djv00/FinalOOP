<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fuel/Energy Usage Report</title>
</head>
<body>
<h2>Fuel/Energy Usage Report</h2>

<form method="get" action="controller">
    <input type="hidden" name="action" value="fuelReport" />
    <label for="vehicleId">Select Vehicle:</label>
    <select name="vehicleId" required>
        <option value="">--Choose Vehicle--</option>
        <c:forEach var="vehicle" items="${vehicleList}">
            <option value="${vehicle.id}" ${vehicle.id == param.vehicleId ? 'selected' : ''}>
                ${vehicle.vehicleNumber}
            </option>
        </c:forEach>
    </select>
    <button type="submit">View Report</button>
</form>

<c:if test="${not empty logs}">
    <table border="1">
        <thead>
        <tr>
            <th>Amount</th>
            <th>Unit</th>
            <th>Distance (km)</th>
            <th>Timestamp</th>
            <th>Efficiency</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${logs}">
            <tr>
                <td>${entry.log.amount}</td>
                <td>${entry.log.unit}</td>
                <td>${entry.log.distanceKm}</td>
                <td>${entry.log.timestamp}</td>
                <td><fmt:formatNumber value="${entry.efficiency}" maxFractionDigits="2"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty logs && not empty param.vehicleId}">
    <p>No data available for selected vehicle.</p>
</c:if>

<p><a href="controller?action=managerHome">Back to Home</a></p>
</body>
</html>
