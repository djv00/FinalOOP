<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Maintenance Alerts</title>
</head>
<body>
    <h2>Unresolved Maintenance Alerts</h2>

    <c:if test="${not empty alerts}">
        <table border="1">
            <tr>
                <th>Vehicle ID</th>
                <th>Component</th>
                <th>Usage Hours</th>
                <th>Alert Time</th>
                <th>Action</th>
            </tr>
            <c:forEach var="alert" items="${alerts}">
                <tr>
                    <td><c:out value="${alert.vehicleId}"/></td>
                    <td><c:out value="${alert.component}"/></td>
                    <td><c:out value="${alert.usageHours}"/></td>
                    <td><c:out value="${alert.alertTime}"/></td>
                    <td>
                        <form action="ResolveAlertServlet" method="post">
                            <input type="hidden" name="alertId" value="${alert.id}" />
                            <input type="submit" value="Mark Resolved" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty alerts}">
        <p>No unresolved alerts at this time.</p>
    </c:if>
</body>
</html>
