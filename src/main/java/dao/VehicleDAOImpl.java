package dao;

import model.VehicleDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO interface for managing vehicle data.
 * Provides methods to add, retrieve, and delete vehicles
 * from the 'vehicles' table in the database.
 *
 * Applies DAO pattern to abstract database operations
 * related to vehicle management.
 *
 * @author Kai Lu
 */
public class VehicleDAOImpl implements VehicleDAO {

    private final Connection conn;

    public VehicleDAOImpl() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void addVehicle(VehicleDTO vehicle) throws Exception {
        String sql = "INSERT INTO vehicles (vehicle_number, vehicle_type, fuel_type, consumption_rate, max_passengers, current_route) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicle.getVehicleNumber());
            ps.setString(2, vehicle.getVehicleType());
            ps.setString(3, vehicle.getFuelType());
            ps.setDouble(4, vehicle.getConsumptionRate());
            ps.setInt(5, vehicle.getMaxPassengers());
            ps.setString(6, vehicle.getCurrentRoute());
            ps.executeUpdate();
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() throws Exception {
        List<VehicleDTO> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                VehicleDTO vehicle = new VehicleDTO.Builder()
                    .withId(rs.getInt("id"))
                    .withVehicleNumber(rs.getString("vehicle_number"))
                    .withVehicleType(rs.getString("vehicle_type"))
                    .withFuelType(rs.getString("fuel_type"))
                    .withConsumptionRate(rs.getDouble("consumption_rate"))
                    .withMaxPassengers(rs.getInt("max_passengers"))
                    .withCurrentRoute(rs.getString("current_route"))
                    .build();
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    @Override
    public VehicleDTO getVehicleById(int id) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new VehicleDTO.Builder()
                    .withId(rs.getInt("id"))
                    .withVehicleNumber(rs.getString("vehicle_number"))
                    .withVehicleType(rs.getString("vehicle_type"))
                    .withFuelType(rs.getString("fuel_type"))
                    .withConsumptionRate(rs.getDouble("consumption_rate"))
                    .withMaxPassengers(rs.getInt("max_passengers"))
                    .withCurrentRoute(rs.getString("current_route"))
                    .build();
            }
        }
        return null;
    }

    @Override
    public void deleteVehicle(int id) throws Exception {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
} 
