<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Alerts</title>
</head>
<body>
    <h2>Maintenance Alerts</h2>

    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty alerts}">
            <p>No unresolved maintenance alerts found.</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="5">
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
                                <form action="controller?action=resolveAlert" method="post">
                                    <input type="hidden" name="alertId" value="${alert.id}" />
                                    <button type="submit">Mark Resolved</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <p><a href="controller?action=managerHome">Back to Home</a></p>
</body>
</html>
