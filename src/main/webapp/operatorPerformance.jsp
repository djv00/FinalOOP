<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, adapter.ReportData"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operator Performance Dashboard</title>
</head>
<body>
    <h1>Operator Performance Dashboard</h1>
    <h2>Performance Data</h2>

    <% 
        // Retrieve report data from the request attributes
        List<ReportData> reportData = (List<ReportData>) request.getAttribute("reportData");
    %>

    <table border="1">
        <thead>
            <tr>
                <th>Vehicle ID</th>
                <th>Label</th>
                <th>Value</th>
                <th>Context</th>
            </tr>
        </thead>
        <tbody>
            <% for (ReportData data : reportData) { %>
                <tr>
                    <td><%= data.getVehicleId() %></td>
                    <td><%= data.getLabel() %></td>
                    <td><%= data.getValue() %></td>
                    <td><%= data.getContext() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <a href="operatorHome.jsp">Back to Home</a>
</body>
</html>
