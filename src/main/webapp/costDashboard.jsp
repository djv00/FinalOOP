<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cost Analysis</title>
</head>
<body>
    <h2>Cost Analysis Dashboard</h2>
    
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <!-- Cost Summary -->
    <h3>Cost Summary</h3>
    <p>Total Fuel/Energy Cost: $<fmt:formatNumber value="${totalFuelCost}" pattern="#,##0.00" /></p>
    <p>Total Maintenance Cost: $<fmt:formatNumber value="${totalMaintenanceCost}" pattern="#,##0.00" /></p>
    <p>Total Operating Cost: $<fmt:formatNumber value="${totalCost}" pattern="#,##0.00" /></p>
    
    <!-- Cost By Vehicle -->
    <h3>Cost By Vehicle</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Vehicle ID</th>
                <th>Vehicle Type</th>
                <th>Fuel/Energy Cost</th>
                <th>Maintenance Cost</th>
                <th>Total Cost</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="vehicleId" begin="1" end="3">
                <c:set var="fuelCost" value="0" />
                <c:set var="maintCost" value="0" />
                
                <c:forEach var="report" items="${costReports}">
                    <c:if test="${report.vehicleId == vehicleId}">
                        <c:set var="fuelCost" value="${fuelCost + report.fuelCost}" />
                        <c:set var="maintCost" value="${maintCost + report.maintenanceCost}" />
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
                    <td>$<fmt:formatNumber value="${fuelCost}" pattern="#,##0.00" /></td>
                    <td>$<fmt:formatNumber value="${maintCost}" pattern="#,##0.00" /></td>
                    <td>$<fmt:formatNumber value="${fuelCost + maintCost}" pattern="#,##0.00" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- Cost Details -->
    <h3>Monthly Cost Details</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Month/Year</th>
                <th>Vehicle ID</th>
                <th>Fuel/Energy Cost</th>
                <th>Maintenance Cost</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="report" items="${costReports}">
                <tr>
                    <td>${report.monthYear}</td>
                    <td>${report.vehicleId}</td>
                    <td>$<fmt:formatNumber value="${report.fuelCost}" pattern="#,##0.00" /></td>
                    <td>$<fmt:formatNumber value="${report.maintenanceCost}" pattern="#,##0.00" /></td>
                    <td>$<fmt:formatNumber value="${report.fuelCost + report.maintenanceCost}" pattern="#,##0.00" /></td>
                </tr>
            </c:forEach>
            <c:if test="${empty costReports}">
                <tr>
                    <td colspan="5">No cost reports available.</td>
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