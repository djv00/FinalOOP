-- ptfms_schema.sql
-- MySQL schema for Public Transit Fleet Management System (PTFMS)
-- Author: Kai Lu

DROP DATABASE IF EXISTS ptfms;
CREATE DATABASE ptfms;
USE ptfms;

-- Users table (Transit Managers & Operators)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('Operator', 'Manager') NOT NULL
);

-- Vehicles table
CREATE TABLE vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_number VARCHAR(20) NOT NULL UNIQUE,
    vehicle_type ENUM('Diesel Bus', 'Electric Light Rail', 'Diesel-Electric Train') NOT NULL,
    fuel_type VARCHAR(50),
    consumption_rate DOUBLE,
    max_passengers INT,
    current_route VARCHAR(100)
);

-- GPS logs (simulated tracking)
CREATE TABLE gps_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    latitude DOUBLE,
    longitude DOUBLE,
    station_name VARCHAR(100),
    arrival_time DATETIME,
    departure_time DATETIME,
    at_final_station BOOLEAN DEFAULT FALSE,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- Operator break logs
CREATE TABLE breaks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    vehicle_id INT,
    break_start DATETIME,
    break_end DATETIME,
    location_lat DOUBLE,
    location_lng DOUBLE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- Fuel/Energy usage logs
CREATE TABLE fuel_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    amount DOUBLE,
    unit ENUM('litres', 'gallons', 'kWh') NOT NULL,
    distance_km DOUBLE,
    timestamp DATETIME,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- Maintenance alerts
CREATE TABLE maintenance_alerts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    component VARCHAR(100),
    usage_hours DOUBLE,
    alert_time DATETIME,
    resolved BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- Maintenance schedules
CREATE TABLE maintenance_schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    task VARCHAR(255),
    scheduled_date DATE,
    status ENUM('Scheduled', 'Completed', 'No Need') DEFAULT 'Scheduled',
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

-- Performance logs (for operators)
CREATE TABLE performance_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    vehicle_id INT,
    scheduled_arrival DATETIME,
    actual_arrival DATETIME,
    efficiency_score DOUBLE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

-- Cost reports (aggregated monthly or per vehicle)
CREATE TABLE cost_reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    fuel_cost DOUBLE,
    maintenance_cost DOUBLE,
    month_year VARCHAR(7), -- e.g., '2025-04'
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);

commit;
