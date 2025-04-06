package dao;

import model.MaintenanceAlertDTO;
import model.MaintenanceScheduleDTO;
import model.VehicleDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import util.DBConnection;

/**
 * Implementation of MaintenanceDAO interface.
 * Provides database operations for maintenance alerts and schedules.
 * 
 * @author Jiangyu Dai
 */
public class MaintenanceDAOImpl implements MaintenanceDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(MaintenanceDAOImpl.class.getName());
    private static final String DATE_TIME = "2025-04-06 16:46:27";
    private static final String USER = "djv00";

    public MaintenanceDAOImpl() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void insertAlert(MaintenanceAlertDTO alert) throws Exception {
        String sql = "INSERT INTO maintenance_alerts (vehicle_id, component, usage_hours, alert_time, resolved) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alert.getVehicleId());
            ps.setString(2, alert.getComponent());
            ps.setDouble(3, alert.getUsageHours());
            ps.setTimestamp(4, alert.getAlertTime());
            ps.setBoolean(5, alert.isResolved());
            ps.executeUpdate();
            
            logger.info(String.format("[%s] User %s created alert for vehicle %d, component %s", 
                DATE_TIME, USER, alert.getVehicleId(), alert.getComponent()));
        }
    }

    @Override
    public List<MaintenanceAlertDTO> getUnresolvedAlertsByVehicle(int vehicleId) throws Exception {
        List<MaintenanceAlertDTO> alerts = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_alerts WHERE vehicle_id = ? AND resolved = false";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alerts.add(fromAlertResultSet(rs));
                }
            }
        }
        return alerts;
    }
    
    @Override
    public List<MaintenanceAlertDTO> getAllAlertsByVehicle(int vehicleId) throws Exception {
        List<MaintenanceAlertDTO> alerts = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_alerts WHERE vehicle_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alerts.add(fromAlertResultSet(rs));
                }
            }
        }
        return alerts;
    }

    @Override
    public MaintenanceAlertDTO getAlertById(int alertId) throws Exception {
        String sql = "SELECT * FROM maintenance_alerts WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alertId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return fromAlertResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void markAlertResolved(int alertId) throws Exception {
        String sql = "UPDATE maintenance_alerts SET resolved = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, true);
            ps.setInt(2, alertId);
            ps.executeUpdate();
            
            logger.info(String.format("[%s] User %s resolved alert ID %d", 
                DATE_TIME, USER, alertId));
        }
    }
    
    @Override
    public void insertSchedule(MaintenanceScheduleDTO schedule) throws Exception {
        String sql = "INSERT INTO maintenance_schedule (vehicle_id, task, scheduled_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, schedule.getVehicleId());
            ps.setString(2, schedule.getTask());
            ps.setDate(3, schedule.getScheduledDate());
            ps.setString(4, schedule.getStatus());
            ps.executeUpdate();
            
            logger.info(String.format("[%s] User %s scheduled maintenance for vehicle %d", 
                DATE_TIME, USER, schedule.getVehicleId()));
        }
    }
    
    @Override
    public void updateScheduleStatus(int scheduleId, String status) throws Exception {
        String sql = "UPDATE maintenance_schedule SET status = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, scheduleId);
            ps.executeUpdate();
            
            logger.info(String.format("[%s] User %s updated schedule %d to %s", 
                DATE_TIME, USER, scheduleId, status));
        }
    }
    
    @Override
    public List<MaintenanceScheduleDTO> getAllSchedules() throws Exception {
        List<MaintenanceScheduleDTO> schedules = new ArrayList<>();
        String sql = "SELECT s.*, v.vehicle_number, v.vehicle_type FROM maintenance_schedule s " +
                     "JOIN vehicles v ON s.vehicle_id = v.id " +
                     "ORDER BY s.scheduled_date";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MaintenanceScheduleDTO schedule = new MaintenanceScheduleDTO();
                schedule.setId(rs.getInt("id"));
                schedule.setVehicleId(rs.getInt("vehicle_id"));
                schedule.setTask(rs.getString("task"));
                schedule.setScheduledDate(rs.getDate("scheduled_date"));
                schedule.setStatus(rs.getString("status"));
                schedule.setVehicleNumber(rs.getString("vehicle_number"));
                schedule.setVehicleType(rs.getString("vehicle_type"));
                schedules.add(schedule);
            }
        }
        return schedules;
    }
    
    // Helper method to convert ResultSet to DTO
    private MaintenanceAlertDTO fromAlertResultSet(ResultSet rs) throws SQLException {
        MaintenanceAlertDTO alert = new MaintenanceAlertDTO();
        alert.setId(rs.getInt("id"));
        alert.setVehicleId(rs.getInt("vehicle_id"));
        alert.setComponent(rs.getString("component"));
        alert.setUsageHours(rs.getDouble("usage_hours"));
        alert.setAlertTime(rs.getTimestamp("alert_time"));
        alert.setResolved(rs.getBoolean("resolved"));
        return alert;
    }
}