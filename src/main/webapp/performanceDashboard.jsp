<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Performance Dashboard</title>
</head>
<body>
    <h2>Operator Performance Dashboard</h2>
    
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <!-- Performance Summary -->
    <h3>Performance Summary</h3>
    <p>Average Efficiency: <fmt:formatNumber value="${avgEfficiency}" pattern="#0.00" /></p>
    <p>On-Time Arrival Rate: <fmt:formatNumber value="${onTimeRate}" pattern="#0.0" />%</p>
    <p>Total Trips: ${performanceLogs.size()}</p>
    
    <!-- Vehicle Performance -->
    <h3>Vehicle Performance</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Vehicle ID</th>
                <th>Vehicle Type</th>
                <th>Average Efficiency</th>
                <th>On-Time Rate</th>
                <th>Trip Count</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="vehicleId" begin="1" end="3">
                <c:set var="effTotal" value="0" />
                <c:set var="onTimeCount" value="0" />
                <c:set var="tripCount" value="0" />
                
                <c:forEach var="log" items="${performanceLogs}">
                    <c:if test="${log.vehicleId == vehicleId}">
                        <c:set var="effTotal" value="${effTotal + log.efficiencyScore}" />
                        <c:if test="${log.isOnTime()}">
                            <c:set var="onTimeCount" value="${onTimeCount + 1}" />
                        </c:if>
                        <c:set var="tripCount" value="${tripCount + 1}" />
                    </c:if>
                </c:forEach>
                
                <tr>
                    <td>${vehicleId}</td>
                    <td>
                        <c:choose>
                            <c:when test="${vehicleId == 1}">Diesel Bus</c:when>
                            <c:when test="${vehicleId == 2}">Electric Light Rail</c:when>
                            <c:when test="${vehicleId == 3}">Diesel-Electric Train</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${tripCount > 0}">
                                <fmt:formatNumber value="${effTotal / tripCount}" pattern="#0.00" />
                            </c:when>
                            <c:otherwise>N/A</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${tripCount > 0}">
                                <fmt:formatNumber value="${onTimeCount * 100.0 / tripCount}" pattern="#0.0" />%
                            </c:when>
                            <c:otherwise>N/A</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${tripCount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- Performance Logs -->
    <h3>Performance Log Details</h3>
    <table border="1">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Vehicle ID</th>
                <th>Efficiency Score</th>
                <th>On Time</th>
                <th>Arrival Time</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="log" items="${performanceLogs}">
                <tr>
                    <td>${log.userId}</td>
                    <td>${log.vehicleId}</td>
                    <td><fmt:formatNumber value="${log.efficiencyScore}" pattern="#0.00" /></td>
                    <td>${log.isOnTime() ? 'Yes' : 'No'}</td>
                    <td>${log.actualArrival}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty performanceLogs}">
                <tr>
                    <td colspan="5">No performance logs available.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    
    <p>
        <a href="ReportServlet">Back to Main Report</a> | 
        <a href="controller?action=managerHome">Back to Home</a>
    </p>
</body>
</html>