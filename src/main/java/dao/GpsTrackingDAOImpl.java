package dao;

import model.GpsLogDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of GpsTrackingDAO.
 * Interacts with 'gps_logs' table for logging and retrieving vehicle GPS data.
 *
 * Used in FR-03 to simulate real-time vehicle tracking and reporting.
 *
 * @author Kai Lu
 */
public class GpsTrackingDAOImpl implements GpsTrackingDAO {

    private final Connection conn;

    public GpsTrackingDAOImpl() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void logVehicleLocation(GpsLogDTO log) throws Exception {
        String sql = "INSERT INTO gps_logs (vehicle_id, latitude, longitude, station_name, arrival_time, departure_time, at_final_station, timestamp) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, log.getVehicleId());
            ps.setDouble(2, log.getLatitude());
            ps.setDouble(3, log.getLongitude());
            ps.setString(4, log.getStationName());
            ps.setTimestamp(5, log.getArrivalTime());
            ps.setTimestamp(6, log.getDepartureTime());
            ps.setBoolean(7, log.isAtFinalStation());
            ps.setTimestamp(8, log.getTimestamp());
            ps.executeUpdate();
        }
    }

    @Override
    public List<GpsLogDTO> getLogsByVehicleId(int vehicleId) throws Exception {
        List<GpsLogDTO> logs = new ArrayList<>();
        String sql = "SELECT * FROM gps_logs WHERE vehicle_id = ? ORDER BY timestamp ASC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GpsLogDTO log = extractLogFromResultSet(rs);
                logs.add(log);
            }
        }
        return logs;
    }

    @Override
    public GpsLogDTO getLatestLocation(int vehicleId) throws Exception {
        String sql = "SELECT * FROM gps_logs WHERE vehicle_id = ? ORDER BY timestamp DESC LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractLogFromResultSet(rs);
            }
        }
        return null;
    }

    /**
     * Helper method to convert ResultSet row to GpsLogDTO.
     */
    private GpsLogDTO extractLogFromResultSet(ResultSet rs) throws SQLException {
        GpsLogDTO log = new GpsLogDTO();
        log.setId(rs.getInt("id"));
        log.setVehicleId(rs.getInt("vehicle_id"));
        log.setLatitude(rs.getDouble("latitude"));
        log.setLongitude(rs.getDouble("longitude"));
        log.setStationName(rs.getString("station_name"));
        log.setArrivalTime(rs.getTimestamp("arrival_time"));
        log.setDepartureTime(rs.getTimestamp("departure_time"));
        log.setAtFinalStation(rs.getBoolean("at_final_station"));
        log.setTimestamp(rs.getTimestamp("timestamp"));
        return log;
    }
} 
