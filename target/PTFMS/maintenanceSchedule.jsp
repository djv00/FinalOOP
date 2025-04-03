<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Maintenance Schedule</title>
</head>
<body>
    <h2>Scheduled Maintenance Tasks</h2>

    <c:if test="${not empty schedules}">
        <table border="1">
            <tr>
                <th>Vehicle ID</th>
                <th>Task</th>
                <th>Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="s" items="${schedules}">
                <tr>
                    <td><c:out value="${s.vehicleId}"/></td>
                    <td><c:out value="${s.task}"/></td>
                    <td><c:out value="${s.scheduledDate}"/></td>
                    <td><c:out value="${s.status}"/></td>
                    <td>
                        <form action="UpdateScheduleServlet" method="post">
                            <input type="hidden" name="id" value="${s.id}"/>
                            <select name="status">
                                <option value="Scheduled">Scheduled</option>
                                <option value="Completed">Completed</option>
                                <option value="No Need">No Need</option>
                            </select>
                            <input type="submit" value="Update"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty schedules}">
        <p>No scheduled tasks found.</p>
    </c:if>
</body>
</html>
