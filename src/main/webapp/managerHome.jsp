<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <li><a href="controller?action=reportDashboard">Go to Dashboard</a></li>
        <li><a href="controller?action=vehicleForm">Register a Vehicle</a></li>
        <li><a href="controller?action=gpsView">View GPS Tracking</a></li>
        <li><a href="controller?action=fuelView">View Fuel/Energy Usage</a></li>
        <li><a href="controller?action=maintenanceAlert">View Maintenance Alerts</a></li>
        <li><a href="controller?action=maintenanceSchedule">Schedule Maintenance Tasks</a></li>
        <li><a href="controller?action=report">View Cost & Performance Reports</a></li>
    </ul>

    <a href="controller?action=logout">Logout</a>
</body>
</html>
