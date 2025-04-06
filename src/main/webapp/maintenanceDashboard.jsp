<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Dashboard</title>
</head>
<body>
    <h2>Maintenance Dashboard</h2>
    
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <!-- Summary Section -->
    <h3>Maintenance Summary</h3>
    <p>Active Alerts: ${alerts.size()}</p>
    <p>Scheduled Maintenance Tasks: ${schedules.size()}</p>
    
    <!-- Alerts By Vehicle -->
    <h3>Alerts By Vehicle</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Vehicle ID</th>
                <th>Vehicle Type</th>
                <th>Alert Count</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="vehicle1Count" value="0" />
            <c:set var="vehicle2Count" value="0" />
            <c:set var="vehicle3Count" value="0" />
            
            <c:forEach var="alert" items="${alerts}">
                <c:if test="${alert.vehicleId == 1}"><c:set var="vehicle1Count" value="${vehicle1Count + 1}" /></c:if>
                <c:if test="${alert.vehicleId == 2}"><c:set var="vehicle2Count" value="${vehicle2Count + 1}" /></c:if>
                <c:if test="${alert.vehicleId == 3}"><c:set var="vehicle3Count" value="${vehicle3Count + 1}" /></c:if>
            </c:forEach>
            
            <tr>
                <td>1</td>
                <td>Diesel Bus</td>
                <td>${vehicle1Count}</td>
                <td><a href="controller?action=maintenanceAlertServlet">View Alerts</a></td>
            </tr>
            <tr>
                <td>2</td>
                <td>Electric Light Rail</td>
                <td>${vehicle2Count}</td>
                <td><a href="controller?action=maintenanceAlertServlet">View Alerts</a></td>
            </tr>
            <tr>
                <td>3</td>
                <td>Diesel-Electric Train</td>
                <td>${vehicle3Count}</td>
                <td><a href="controller?action=maintenanceAlertServlet">View Alerts</a></td>
            </tr>
        </tbody>
    </table>
    
    <!-- Combined Maintenance Data -->
    <h3>Combined Maintenance Data</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Vehicle ID</th>
                <th>Type</th>
                <th>Details</th>
                <th>Status/Time</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="data" items="${reportData}">
                <tr>
                    <td>${data.vehicleId}</td>
                    <td>${data.label}</td>
                    <td>${data.value}</td>
                    <td>${data.context}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty reportData}">
                <tr>
                    <td colspan="4">No maintenance data available.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    
    <p>
        <a href="ReportServlet">Back to Main Report</a> | 
        <a href="controller?action=managerHome">Back to Home</a>
    </p>
</body>
</html>