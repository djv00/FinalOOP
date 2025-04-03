package dao;

import model.FuelLogDTO;
import strategy.ConsumptionStrategy;
import strategy.StrategyFactory;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of EnergyConsumptionDAO using JDBC.
 * Applies DAO, Strategy, and Simple Factory Pattern.
 *
 * Used in FR-04 to manage energy/fuel data and calculate efficiency.
 *
 * @author Kai Lu
 */
public class EnergyConsumptionDAOImpl implements EnergyConsumptionDAO {

    private final Connection conn;

    public EnergyConsumptionDAOImpl() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void insertFuelLog(FuelLogDTO log) throws Exception {
        String sql = "INSERT INTO fuel_logs (vehicle_id, amount, unit, distance_km, timestamp) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, log.getVehicleId());
            ps.setDouble(2, log.getAmount());
            ps.setString(3, log.getUnit());
            ps.setDouble(4, log.getDistanceKm());
            ps.setTimestamp(5, log.getTimestamp());
            ps.executeUpdate();
        }
    }

    @Override
    public List<FuelLogDTO> getLogsByVehicleId(int vehicleId) throws Exception {
        List<FuelLogDTO> logs = new ArrayList<>();
        String sql = "SELECT * FROM fuel_logs WHERE vehicle_id = ? ORDER BY timestamp DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logs.add(fromResultSet(rs));
            }
        }
        return logs;
    }

    @Override
    public List<FuelLogDTO> getAllLogs() throws Exception {
        List<FuelLogDTO> logs = new ArrayList<>();
        String sql = "SELECT * FROM fuel_logs ORDER BY timestamp DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                logs.add(fromResultSet(rs));
            }
        }
        return logs;
    }

    private FuelLogDTO fromResultSet(ResultSet rs) throws SQLException {
        FuelLogDTO log = new FuelLogDTO();
        log.setId(rs.getInt("id"));
        log.setVehicleId(rs.getInt("vehicle_id"));
        log.setAmount(rs.getDouble("amount"));
        log.setUnit(rs.getString("unit"));
        log.setDistanceKm(rs.getDouble("distance_km"));
        log.setTimestamp(rs.getTimestamp("timestamp"));
        return log;
    }

    /**
     * Calculates efficiency for a log using strategy pattern.
     * @param log the fuel log
     * @return efficiency score (e.g. km/litre or km/kWh)
     */
    @Override
    public double calculateEfficiency(FuelLogDTO log) {
        // Use strategy pattern to calculate efficiency based on unit
        ConsumptionStrategy strategy = StrategyFactory.getStrategy(log.getUnit());
        return strategy.calculateEfficiency(log);
    }
}
