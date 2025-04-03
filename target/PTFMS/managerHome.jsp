<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Home</title>
</head>
<body>
    <h1>Welcome, Manager ${sessionScope.user.name}</h1>
    <p>Your role: Manager</p>

    <h2>Actions</h2>
    <ul>
        <li><a href="managerDashboard.jsp">Go to Dashboard</a></li> <!-- Link to Dashboard -->
        <li><a href="vehicleRegistration.jsp">Register a new vehicle</a></li>
        <li><a href="gpsTracking.jsp">View GPS tracking report</a></li>
        <li><a href="fuelReport.jsp">View fuel/energy usage report</a></li>
        <li><a href="maintenanceAlerts.jsp">View maintenance alerts</a></li>
        <li><a href="maintenanceSchedule.jsp">Schedule maintenance tasks</a></li>
        <li><a href="costReport.jsp">View cost reports</a></li>
    </ul>

    <a href="logout">Logout</a>
</body>
</html>
