package model;

import java.sql.Timestamp;

/**
 * Data Transfer Object for fuel or energy consumption logs.
 * Maps to the 'fuel_logs' table in the database.
 *
 * Fields: id, vehicleId, amount, unit, distanceKm, timestamp
 * Used by EnergyConsumptionDAO for FR-04 functionality.
 *
 * @author Kai Lu
 */
public class FuelLogDTO {
    private int id;
    private int vehicleId;
    private double amount;
    private String unit;
    private double distanceKm;
    private Timestamp timestamp;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
