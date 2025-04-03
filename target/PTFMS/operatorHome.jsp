<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserDTO"%>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null || !"Operator".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operator Home</title>
</head>
<body>
    <h1>Welcome, Operator <%= user.getName() %></h1>
    <p>Your role: <%= user.getRole() %></p>

    <h2>Available Actions</h2>
    <ul>
        <li><a href="breakLog.jsp">Log a Break</a></li>
        <li><a href="reportDashboard.jsp">View Performance Dashboard</a></li>
    </ul>

    <p><a href="logout">Logout</a></p>
</body>
</html>
