<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Scheduling</title>
    <link rel="stylesheet" href="css/styles.css">
    <style>
        /* Simple spacing adjustment for table */
        .table th, .table td {
            padding: 8px 12px;  /* Slightly increase padding */
        }
    </style>
</head>
<body>
    <h1>Maintenance Scheduling</h1>
    
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    
    <!-- Form for scheduling new maintenance -->
    <div class="card mb-4">
        <div class="card-header">
            <h2>Schedule New Maintenance</h2>
        </div>
        <div class="card-body">
            <form action="ScheduleTaskServlet" method="post">
                <div class="form-group">
                    <label for="vehicleId">Vehicle:</label>
                    <select name="vehicleId" id="vehicleId" class="form-control" required>
                        <c:forEach var="vehicle" items="${vehicles}">
                            <option value="${vehicle.id}">${vehicle.vehicleNumber} (${vehicle.vehicleType})</option>
                        </c:forEach>
                        <c:if test="${empty vehicles}">
                            <!-- Fallback options if vehicles not loaded -->
                            <option value="1">Vehicle 1 (Bus)</option>
                            <option value="2">Vehicle 2 (Light Rail)</option>
                            <option value="3">Vehicle 3 (Train)</option>
                        </c:if>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="task">Maintenance Task:</label>
                    <input type="text" name="task" id="task" class="form-control" required>
                </div>
                
                <div class="form-group">
                    <label for="scheduledDate">Scheduled Date:</label>
                    <input type="date" name="scheduledDate" id="scheduledDate" class="form-control" required>
                </div>
                
                <button type="submit" class="btn btn-primary">Schedule Maintenance</button>
            </form>
        </div>
    </div>
    
    <!-- Debug info - will help identify if schedules are being loaded -->
    <!-- 
    <p>Debug: Found ${schedules.size()} schedules</p>
    -->
    
    <!-- Maintenance Schedule Table -->
    <div class="card">
        <div class="card-header">
            <h2>Maintenance Schedule</h2>
        </div>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Vehicle</th>
                        <th>Task</th>
                        <th>Scheduled Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="schedule" items="${schedules}">
                        <tr>
                            <td>${schedule.id}</td>
                            <td>${schedule.vehicleNumber} (${schedule.vehicleType})</td>
                            <td>${schedule.task}</td>
                            <td>${schedule.scheduledDate}</td>
                            <td>${schedule.status}</td>
                            <td>
                                <form action="UpdateScheduleServlet" method="post">
                                    <input type="hidden" name="scheduleId" value="${schedule.id}">
                                    <select name="status" onchange="this.form.submit()">
                                        <option value="Scheduled" ${schedule.status eq 'Scheduled' ? 'selected' : ''}>Scheduled</option>
                                        <option value="In Progress" ${schedule.status eq 'In Progress' ? 'selected' : ''}>In Progress</option>
                                        <option value="Completed" ${schedule.status eq 'Completed' ? 'selected' : ''}>Completed</option>
                                        <option value="Cancelled" ${schedule.status eq 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                                    </select>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty schedules}">
                        <tr>
                            <td colspan="6" class="text-center">No scheduled maintenance tasks found</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
    
    <p><a href="controller?action=managerHome">Back to Manager Home</a></p>
</body>
</html>