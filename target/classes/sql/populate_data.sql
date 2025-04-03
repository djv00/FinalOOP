-- populate_ptfms_data.sql
-- Sample data population for PTFMS database
-- Author: Kai Lu

-- Users
INSERT INTO users (name, email, password, role) VALUES
('Ali Wang', 'ali@ptfms.com', 'pass123', 'Manager'),
('Bruce Li', 'bruce@ptfms.com', 'pass456', 'Operator');

-- Vehicles
INSERT INTO vehicles (vehicle_number, vehicle_type, fuel_type, consumption_rate, max_passengers, current_route) VALUES
('BUS001', 'Diesel Bus', 'Diesel', 30.5, 50, 'Route A'),
('LRT001', 'Electric Light Rail', 'Electric', 25.0, 120, 'Route B'),
('TRAIN001', 'Diesel-Electric Train', 'Diesel-Electric', 40.0, 300, 'Route C');

-- Maintenance alerts: components based on vehicle type
-- BUS001: brakes, wheels, axle bearings, engine
INSERT INTO maintenance_alerts (vehicle_id, component, usage_hours, alert_time, resolved) VALUES
(1, 'brakes', 1200, NOW(), FALSE),
(1, 'wheels', 1000, NOW(), FALSE),
(1, 'axle bearings', 1100, NOW(), FALSE),
(1, 'engine', 1500, NOW(), FALSE);

-- LRT001: brakes, wheels, axle bearings, catenary, pantograph, circuit breakers
INSERT INTO maintenance_alerts (vehicle_id, component, usage_hours, alert_time, resolved) VALUES
(2, 'brakes', 900, NOW(), FALSE),
(2, 'wheels', 950, NOW(), FALSE),
(2, 'axle bearings', 970, NOW(), FALSE),
(2, 'catenary', 500, NOW(), FALSE),
(2, 'pantograph', 600, NOW(), FALSE),
(2, 'circuit breakers', 550, NOW(), FALSE);

-- TRAIN001: brakes, wheels, axle bearings, engine
INSERT INTO maintenance_alerts (vehicle_id, component, usage_hours, alert_time, resolved) VALUES
(3, 'brakes', 1300, NOW(), FALSE),
(3, 'wheels', 1250, NOW(), FALSE),
(3, 'axle bearings', 1270, NOW(), FALSE),
(3, 'engine', 1600, NOW(), FALSE);

-- GPS Logs
INSERT INTO gps_logs (vehicle_id, latitude, longitude, station_name, arrival_time, departure_time, at_final_station) VALUES
(1, 45.4215, -75.6972, 'Station A', '2025-04-01 08:00:00', '2025-04-01 08:10:00', FALSE),
(1, 45.4230, -75.6950, 'Station B', '2025-04-01 08:20:00', '2025-04-01 08:25:00', TRUE),
(2, 45.4240, -75.6900, 'Station C', '2025-04-01 09:00:00', '2025-04-01 09:15:00', FALSE),
(2, 45.4260, -75.6880, 'Station D', '2025-04-01 09:30:00', '2025-04-01 09:45:00', TRUE);

-- Break logs
INSERT INTO breaks (user_id, vehicle_id, break_start, break_end, location_lat, location_lng) VALUES
(2, 1, '2025-04-01 08:11:00', '2025-04-01 08:20:00', 45.4220, -75.6960);

-- Fuel logs
INSERT INTO fuel_logs (vehicle_id, amount, unit, distance_km, timestamp) VALUES
(1, 12.0, 'litres', 40.0, NOW()),
(3, 18.0, 'gallons', 60.0, NOW()),
(2, 24.0, 'kWh', 55.0, NOW());

-- Maintenance schedules
INSERT INTO maintenance_schedule (vehicle_id, task, scheduled_date, status) VALUES
(1, 'Check brakes and engine', '2025-04-05', 'Scheduled'),
(2, 'Inspect pantograph and wheels', '2025-04-07', 'Scheduled'),
(3, 'Routine diagnostics', '2025-04-10', 'Scheduled');

-- Performance logs
INSERT INTO performance_logs (user_id, vehicle_id, scheduled_arrival, actual_arrival, efficiency_score) VALUES
(2, 1, '2025-04-01 08:00:00', '2025-04-01 08:02:00', 98.5),
(2, 1, '2025-04-01 08:20:00', '2025-04-01 08:25:00', 95.0);

-- Cost reports
INSERT INTO cost_reports (vehicle_id, fuel_cost, maintenance_cost, month_year) VALUES
(1, 120.0, 300.0, '2025-04'),
(2, 95.0, 280.0, '2025-04'),
(3, 150.0, 500.0, '2025-04');

COMMIT;
