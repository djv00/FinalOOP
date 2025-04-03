<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reporting & Analytics</title>
</head>
<body>
    <h2>FR-06: Unified Reporting Dashboard</h2>

    <c:if test="${not empty reportData}">
        <table border="1">
            <tr>
                <th>Vehicle ID</th>
                <th>Metric</th>
                <th>Value</th>
                <th>Context</th>
            </tr>
            <c:forEach var="data" items="${reportData}">
                <tr>
                    <td><c:out value="${data.vehicleId}"/></td>
                    <td><c:out value="${data.label}"/></td>
                    <td><c:out value="${data.value}"/></td>
                    <td><c:out value="${data.context}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty reportData}">
        <p>No data available for reporting.</p>
    </c:if>
</body>
</html>
