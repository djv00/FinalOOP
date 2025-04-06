<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Alerts</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h1>Maintenance Alerts</h1>
    
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    
    <!-- Alerts Table -->
    <div class="card">
        <div class="card-header">
            <h2>Unresolved Maintenance Alerts</h2>
            <a href="controller?action=updateSchedule" class="btn btn-primary">Schedule Maintenance</a>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Alert ID</th>
                        <th>Vehicle ID</th>
                        <th>Component</th>
                        <th>Usage Hours</th>
                        <th>Alert Time</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="alert" items="${alerts}">
                        <tr>
                            <td>${alert.id}</td>
                            <td>${alert.vehicleId}</td>
                            <td>${alert.component}</td>
                            <td>${alert.usageHours}</td>
                            <td>${alert.alertTime}</td>
                            <td>
                                <form action="ResolveAlertServlet" method="post">
                                    <input type="hidden" name="alertId" value="${alert.id}">
                                    <button type="submit" class="btn btn-sm btn-success">Mark Resolved</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty alerts}">
                        <tr>
                            <td colspan="6" class="text-center">No unresolved maintenance alerts</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
    
    <p><a href="controller?action=managerHome">Back to Manager Home</a></p>
</body>
</html>