<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Dashboard</title>
    <!-- Include any JavaScript charting libraries or other required resources -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>Manager Dashboard</h1>
    <p>Welcome to the Dashboard, ${sessionScope.user.name}!</p>

    <h2>Vehicle Statistics</h2>
    <canvas id="vehicleStatusChart" width="400" height="200"></canvas>
    <script>
        // Example data for vehicle status (you would replace this with dynamic data from the server)
        var ctx = document.getElementById('vehicleStatusChart').getContext('2d');
        var vehicleStatusChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Diesel Bus', 'Electric Light Rail', 'Diesel-Electric Train'],
                datasets: [{
                    label: 'Number of Vehicles',
                    data: [5, 8, 3], // Example values for each vehicle type
                    backgroundColor: ['red', 'blue', 'green'],
                    borderColor: ['red', 'blue', 'green'],
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

    <h2>Fuel Consumption</h2>
    <canvas id="fuelConsumptionChart" width="400" height="200"></canvas>
    <script>
        // Example data for fuel consumption (replace with actual data)
        var ctx2 = document.getElementById('fuelConsumptionChart').getContext('2d');
        var fuelConsumptionChart = new Chart(ctx2, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr'],
                datasets: [{
                    label: 'Fuel Consumption (litres)',
                    data: [1000, 1500, 1200, 1300], // Example values
                    borderColor: 'green',
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

    <a href="managerHome.jsp">Back to Home</a>
</body>
</html>
