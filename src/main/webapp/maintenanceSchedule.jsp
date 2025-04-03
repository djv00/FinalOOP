<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Schedule</title>
</head>
<body>
    <h2>Maintenance Schedule Tasks</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <c:if test="${empty schedules}">
        <p>No scheduled maintenance tasks found.</p>
    </c:if>

    <c:if test="${not empty schedules}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Vehicle ID</th>
                    <th>Task</th>
                    <th>Scheduled Date</th>
                    <th>Status</th>
                    <th>Update Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${schedules}">
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.vehicleId}</td>
                        <td>${s.task}</td>
                        <td>${s.scheduledDate}</td>
                        <td>${s.status}</td>
                        <td>
                            <form action="controller?action=updateSchedule" method="post">
                                <input type="hidden" name="id" value="${s.id}" />
                                <select name="status">
                                    <option value="Scheduled" ${s.status == 'Scheduled' ? 'selected' : ''}>Scheduled</option>
                                    <option value="Completed" ${s.status == 'Completed' ? 'selected' : ''}>Completed</option>
                                    <option value="No Need" ${s.status == 'No Need' ? 'selected' : ''}>No Need</option>
                                </select>
                                <button type="submit">Update</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <p><a href="controller?action=managerHome">Back to Home</a></p>
</body>
</html>
