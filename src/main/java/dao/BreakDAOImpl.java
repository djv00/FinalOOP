package dao;

import model.BreakDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of BreakDAO.
 * Handles operator break record storage and retrieval
 * from the 'breaks' table in the database.
 *
 * Supports FR-03 for manual break logging by operators.
 *
 * @author Kai Lu
 */
public class BreakDAOImpl implements BreakDAO {

    private final Connection conn;

    public BreakDAOImpl() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void logBreak(BreakDTO breakDTO) throws Exception {
        String sql = "INSERT INTO breaks (user_id, vehicle_id, break_start, break_end, location_lat, location_lng) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, breakDTO.getUserId());
            ps.setInt(2, breakDTO.getVehicleId());
            ps.setTimestamp(3, breakDTO.getBreakStart());
            ps.setTimestamp(4, breakDTO.getBreakEnd());
            ps.setDouble(5, breakDTO.getLocationLat());
            ps.setDouble(6, breakDTO.getLocationLng());
            ps.executeUpdate();
        }
    }

    @Override
    public List<BreakDTO> getBreaksByUserId(int userId) throws Exception {
        List<BreakDTO> breaks = new ArrayList<>();
        String sql = "SELECT * FROM breaks WHERE user_id = ? ORDER BY break_start DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                breaks.add(extractBreakFromResultSet(rs));
            }
        }
        return breaks;
    }

    @Override
    public List<BreakDTO> getBreaksByVehicleId(int vehicleId) throws Exception {
        List<BreakDTO> breaks = new ArrayList<>();
        String sql = "SELECT * FROM breaks WHERE vehicle_id = ? ORDER BY break_start DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                breaks.add(extractBreakFromResultSet(rs));
            }
        }
        return breaks;
    }

    /**
     * Helper method to convert ResultSet row into BreakDTO.
     */
    private BreakDTO extractBreakFromResultSet(ResultSet rs) throws SQLException {
        BreakDTO b = new BreakDTO();
        b.setId(rs.getInt("id"));
        b.setUserId(rs.getInt("user_id"));
        b.setVehicleId(rs.getInt("vehicle_id"));
        b.setBreakStart(rs.getTimestamp("break_start"));
        b.setBreakEnd(rs.getTimestamp("break_end"));
        b.setLocationLat(rs.getDouble("location_lat"));
        b.setLocationLng(rs.getDouble("location_lng"));
        return b;
    }
} 
