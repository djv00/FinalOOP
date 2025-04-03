<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, dao.ReportDAOImpl, model.CostReportDTO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cost Report</title>
</head>
<body>
    <h1>Cost Report</h1>
    <h2>Cost Report for Vehicles</h2>

    <% 
        // Get all cost reports from database
        ReportDAOImpl dao = new ReportDAOImpl();
        List<CostReportDTO> reports = dao.getAllCostReports();
    %>

    <table border="1">
        <thead>
            <tr>
                <th>Vehicle ID</th>
                <th>Fuel Cost</th>
                <th>Maintenance Cost</th>
                <th>Month/Year</th>
            </tr>
        </thead>
        <tbody>
            <% for (CostReportDTO report : reports) { %>
            <tr>
                <td><%= report.getVehicleId() %></td>
                <td><%= report.getFuelCost() %></td>
                <td><%= report.getMaintenanceCost() %></td>
                <td><%= report.getMonthYear() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <a href="operatorHome.jsp">Back to Home</a>
</body>
</html>
