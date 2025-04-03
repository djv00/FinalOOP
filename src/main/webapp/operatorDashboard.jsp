<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operator Dashboard</title>
    <!-- Include any required JavaScript charting libraries -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>Welcome, Operator ${sessionScope.user.name}</h1>
    <p>Your role: Operator</p>

    <h2>Actions</h2>
    <ul>

        <li><a href="fuelReport.jsp">View fuel/energy usage</a></li> <!-- Fuel report link -->
        <li><a href="operatorPerformance.jsp">View performance</a></li> <!-- Performance dashboard link -->
        <li><a href="costReport.jsp">View cost</a></li> <!-- Cost report link -->
    </ul>

    <h2>Operator Performance</h2>
    <canvas id="performanceChart" width="400" height="200"></canvas>
    <script>
        var ctx = document.getElementById('performanceChart').getContext('2d');
        var performanceChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr'], // Example months
                datasets: [{
                    label: 'On-time Arrival Rate (%)',
                    data: [95, 92, 96, 94], // Example performance data
                    borderColor: 'blue',
                    borderWidth: 2
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>

    <h2>Fuel Efficiency</h2>
    <canvas id="fuelEfficiencyChart" width="400" height="200"></canvas>
    <script>
        var ctx2 = document.getElementById('fuelEfficiencyChart').getContext('2d');
        var fuelEfficiencyChart = new Chart(ctx2, {
            type: 'bar',
            data: {
                labels: ['Diesel Bus', 'Electric Light Rail', 'Diesel-Electric Train'], // Example vehicle types
                datasets: [{
                    label: 'Fuel Efficiency (km/litre)',
                    data: [10, 12, 8], // Example fuel efficiency data
                    backgroundColor: ['red', 'green', 'blue'],
                    borderColor: ['red', 'green', 'blue'],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>

    <a href="operatorHome.jsp">Back to Home</a> <!-- Link back to operator home page -->
</body>
</html>
