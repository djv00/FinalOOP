<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register a Vehicle</title>
</head>
<body>
    <h2>Register New Vehicle</h2>

    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>

    <form method="post" action="controller?action=vehicleFormSubmit">
        <label for="vehicleNumber">Vehicle Number:</label>
        <input type="text" name="vehicleNumber" required /><br><br>

        <label for="vehicleType">Vehicle Type:</label>
        <select name="vehicleType" required>
            <option value="Diesel Bus">Diesel Bus</option>
            <option value="Electric Light Rail">Electric Light Rail</option>
            <option value="Diesel-Electric Train">Diesel-Electric Train</option>
        </select><br><br>

        <label for="fuelType">Fuel/Energy Type:</label>
        <select name="fuelType" required>
            <option value="Diesel">Diesel</option>
            <option value="CNG">CNG</option>
            <option value="Electric">Electric</option>

            <option value="Diesel-Electric">Diesel-Electric</option>
        </select><br><br>

        <label for="consumptionRate">Consumption Rate:</label>
        <input type="number" step="0.01" name="consumptionRate" required /><br><br>

        <label for="maxPassengers">Max Passengers:</label>
        <input type="number" name="maxPassengers" required /><br><br>

        <label for="currentRoute">Current Assigned Route:</label>
        <input type="text" name="currentRoute" required /><br><br>

        <button type="submit">Submit</button>
    </form>

    <p><a href="controller?action=managerHome">Back to Home</a></p>
</body>
</html>
