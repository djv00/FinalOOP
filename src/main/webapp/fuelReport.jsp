<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fuel/Energy Usage Report</title>
</head>
<body>
    <h1>Fuel/Energy Usage Report</h1>
    <p>Below is the report showing fuel/energy usage data for each vehicle.</p>

    <!-- Example Table: Add real data here -->
    <table border="1">
        <thead>
            <tr>
                <th>Vehicle Number</th>
                <th>Fuel Type</th>
                <th>Distance (km)</th>
                <th>Fuel/Energy Amount</th>
                <th>Unit</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <!-- Example data, replace with dynamic content -->
            <tr>
                <td>BUS001</td>
                <td>Diesel</td>
                <td>150</td>
                <td>45</td>
                <td>litres</td>
                <td>2025-04-01</td>
            </tr>
            <tr>
                <td>LRT001</td>
                <td>Electric</td>
                <td>120</td>
                <td>100</td>
                <td>kWh</td>
                <td>2025-04-02</td>
            </tr>
        </tbody>
    </table>

    <a href="operatorHome.jsp">Back to Home</a> <!-- Link to go back to operator home page -->
</body>
</html>
