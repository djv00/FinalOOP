<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unified Report Dashboard</title>
</head>
<body>
    <h2>Reporting & Analytics Dashboard</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <c:if test="${empty reportData}">
        <p>No report data available.</p>
    </c:if>

    <c:if test="${not empty reportData}">
        <table border="1">
            <thead>
                <tr>
                    <th>Vehicle ID</th>
                    <th>Metric</th>
                    <th>Value</th>
                    <th>Context</th>
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
            </tbody>
        </table>
    </c:if>

    <p><a href="controller?action=managerHome">Back to Home</a></p>
</body>
</html>
