<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operator Home</title>
</head>
<body>
    <h1>Welcome, Operator ${sessionScope.user.name}</h1>
    <p>Your role: Operator</p>

    <h2>Actions</h2>
    <ul>
        <li><a href="breakLog.jsp">Log your break</a></li> <!-- Link to log break page -->

        <li><a href="operatorDashboard.jsp">Go to Operator Dashboard</a></li> <!-- Link to operator dashboard -->
    </ul>

    <a href="logout">Logout</a> <!-- Link to logout -->
</body>
</html>
