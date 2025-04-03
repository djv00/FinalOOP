<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registered Vehicles</title>
</head>
<body>
    <h1>Registered Vehicles</h1>

    <c:if test="${not empty vehicles}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Vehicle Number</th>
                    <th>Type</th>
                    <th>Fuel Type</th>
                    <th>Consumption Rate</th>
                    <th>Max Passengers</th>
                    <th>Current Route</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="v" items="${vehicles}">
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.vehicleNumber}</td>
                        <td>${v.vehicleType}</td>
                        <td>${v.fuelType}</td>
                        <td>${v.consumptionRate}</td>
                        <td>${v.maxPassengers}</td>
                        <td>${v.currentRoute}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty vehicles}">
        <p>No vehicles found.</p>
    </c:if>

    <p><a href="controller?action=managerHome">Back to Home</a></p>
</body>
</html>
