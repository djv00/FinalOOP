package dao;

import model.MaintenanceAlertDTO;
import model.MaintenanceScheduleDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of MaintenanceDAO.
 * Handles database operations for maintenance alerts and schedule.
 *
 * Applies DAO pattern for FR-05 Predictive Maintenance.
 *
 * @author Kai Lu
 */
public class MaintenanceDAOImpl implements MaintenanceDAO {

    private final Connection conn;

    public MaintenanceDAOImpl() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void insertAlert(MaintenanceAlertDTO alert) throws Exception {
        String sql = "INSERT INTO maintenance_alerts (vehicle_id, component, usage_hours, alert_time, resolved) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alert.getVehicleId());
            ps.setString(2, alert.getComponent());
            ps.setDouble(3, alert.getUsageHours());
            ps.setTimestamp(4, alert.getAlertTime());
            ps.setInt(5, alert.isResolved() ? 1 : 0); // 使用 1/0 替代 true/false
            ps.executeUpdate();
        }
    }

    @Override
    public List<MaintenanceAlertDTO> getUnresolvedAlertsByVehicle(int vehicleId) throws Exception {
        List<MaintenanceAlertDTO> alerts = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_alerts WHERE vehicle_id = ? AND resolved = 0 ORDER BY alert_time DESC";
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
    public void markAlertResolved(int alertId) throws Exception {
        String sql = "UPDATE maintenance_alerts SET resolved = 1 WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alertId);
            ps.executeUpdate();
        }
    }

    private MaintenanceAlertDTO fromAlertResultSet(ResultSet rs) throws SQLException {
        MaintenanceAlertDTO alert = new MaintenanceAlertDTO();
        alert.setId(rs.getInt("id"));
        alert.setVehicleId(rs.getInt("vehicle_id"));
        alert.setComponent(rs.getString("component"));
        alert.setUsageHours(rs.getDouble("usage_hours"));
        alert.setAlertTime(rs.getTimestamp("alert_time"));
        alert.setResolved(rs.getInt("resolved") == 1); // 从 1 映射为 true
        return alert;
    }

    public void insertSchedule(MaintenanceScheduleDTO task) throws Exception {
        String sql = "INSERT INTO maintenance_schedule (vehicle_id, task, scheduled_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, task.getVehicleId());
            ps.setString(2, task.getTask());
            ps.setDate(3, task.getScheduledDate());
            ps.setString(4, task.getStatus());
            ps.executeUpdate();
        }
    }

    public List<MaintenanceScheduleDTO> getAllSchedules() throws Exception {
        List<MaintenanceScheduleDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_schedule ORDER BY scheduled_date DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MaintenanceScheduleDTO task = new MaintenanceScheduleDTO();
                task.setId(rs.getInt("id"));
                task.setVehicleId(rs.getInt("vehicle_id"));
                task.setTask(rs.getString("task"));
                task.setScheduledDate(rs.getDate("scheduled_date"));
                task.setStatus(rs.getString("status"));
                list.add(task);
            }
        }
        return list;
    }

    public void updateScheduleStatus(int scheduleId, String status) throws Exception {
        String sql = "UPDATE maintenance_schedule SET status = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, scheduleId);
            ps.executeUpdate();
        }
    }
}
