<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Alerts</title>
    <link rel="stylesheet" href="css/styles.css">
    <style>
        /* Simple spacing adjustment for table */
        .table th, .table td {
            padding: 8px 12px;  /* Slightly increase padding */
        }
    </style>
</head>
<body>
    <h1>Maintenance Alerts</h1>
    
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    
    <!-- Filter Controls -->
    <div class="card mb-3">
        <div class="card-header">
            <h3>Filter Alerts</h3>
        </div>
        <div class="card-body">
            <form action="MaintenanceAlertServlet" method="get" class="form-inline">
                <div class="form-group mr-2">
                    <label for="vehicleId">By Vehicle:</label>
                    <select name="vehicleId" id="vehicleId" class="form-control">
                        <option value="">All Vehicles</option>
                        <c:forEach var="vehicle" items="${vehicles}">
                            <option value="${vehicle.id}" ${selectedVehicleId == vehicle.id ? 'selected' : ''}>
                                ${vehicle.vehicleNumber} (${vehicle.vehicleType})
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group mr-2">
                    <label for="type">By Type:</label>
                    <select name="type" id="type" class="form-control">
                        <option value="">All Types</option>
                        <c:forEach var="type" items="${vehicleTypes}">
                            <option value="${type}" ${selectedType == type ? 'selected' : ''}>${type}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group mr-2">
                    <label for="resolved">Status:</label>
                    <select name="resolved" id="resolved" class="form-control">
                        <option value="false" ${!showResolved ? 'selected' : ''}>Unresolved Only</option>
                        <option value="true" ${showResolved ? 'selected' : ''}>All Alerts</option>
                    </select>
                </div>
                
                <button type="submit" class="btn btn-primary">Apply Filter</button>
                <a href="MaintenanceAlertServlet" class="btn btn-secondary ml-2">Clear Filter</a>
            </form>
        </div>
    </div>
    
    <!-- Alerts Table -->
    <div class="card">
        <div class="card-header">
            <h2>Maintenance Alerts</h2>
            <a href="controller?action=updateSchedule" class="btn btn-primary">Schedule Maintenance</a>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Alert ID</th>
                        <th>Vehicle ID</th>
                        <th>Vehicle Type</th>
                        <th>Component</th>
                        <th>Usage Hours</th>
                        <th>Alert Time</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="alert" items="${alerts}">
                        <tr>
                            <td>${alert.id}</td>
                            <td>${alert.vehicleId}</td>
                            <td>${alert.vehicleType}</td>
                            <td>${alert.component}</td>
                            <td>${alert.usageHours}</td>
                            <td>${alert.alertTime}</td>
                            <td>${alert.resolved ? 'Resolved' : 'Unresolved'}</td>
                            <td>
                                <c:if test="${!alert.resolved}">
                                    <form action="ResolveAlertServlet" method="post">
                                        <input type="hidden" name="alertId" value="${alert.id}">
                                        <button type="submit" class="btn btn-sm btn-success">Mark Resolved</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty alerts}">
                        <tr>
                            <td colspan="8" class="text-center">No maintenance alerts found</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
    
    <p><a href="controller?action=managerHome">Back to Manager Home</a></p>
</body>
</html>