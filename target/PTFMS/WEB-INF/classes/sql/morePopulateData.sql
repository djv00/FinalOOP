-- Extended vehicle records (id = 5 to 10)
INSERT INTO ptfms.vehicles (vehicle_number, vehicle_type, fuel_type, consumption_rate, max_passengers, current_route) VALUES
('BUS102A', 'Diesel Bus', 'Diesel', 32.0, 55, 'Route B'),
('LRT-22X', 'Electric Light Rail', 'Electric', 26.5, 130, 'Route C'),
('TRN-880', 'Diesel-Electric Train', 'Diesel-Electric', 42.0, 310, 'Route D'),
('BUS103B', 'Diesel Bus', 'Biodiesel', 28.0, 48, 'Route A'),
('LRT-E35', 'Electric Light Rail', 'Electric', 24.0, 110, 'Route E'),
('TRN-PRO1', 'Diesel-Electric Train', 'Diesel-Electric', 41.5, 290, 'Route F');


-- Additional fuel_logs for vehicles with id 4 to 10
INSERT INTO ptfms.fuel_logs (vehicle_id, amount, unit, distance_km, timestamp) VALUES
(4, 15.0, 'litres', 45.0, NOW()),        -- BUS100
(5, 16.5, 'litres', 50.0, NOW()),        -- BUS102A
(6, 30.0, 'kWh',    60.0, NOW()),        -- LRT-22X
(7, 22.0, 'gallons', 65.0, NOW()),       -- TRN-880
(8, 14.5, 'litres', 42.0, NOW()),        -- BUS103B
(9, 28.0, 'kWh',    58.0, NOW()),        -- LRT-E35
(10, 20.0, 'gallons', 70.0, NOW());      -- TRN-PRO1

commit;