package model;

import java.sql.Timestamp;

/**
 * Data Transfer Object for maintenance alerts.
 * Maps to the 'maintenance_alerts' table with vehicle type from 'vehicles' table.
 *
 * @author Jiangyu Dai
 */
public class MaintenanceAlertDTO {
    private int id;
    private int vehicleId;
    private String vehicleType;  // Added field for vehicle type
    private String component;
    private double usageHours;
    private Timestamp alertTime;
    private boolean resolved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public double getUsageHours() {
        return usageHours;
    }

    public void setUsageHours(double usageHours) {
        this.usageHours = usageHours;
    }

    public Timestamp getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Timestamp alertTime) {
        this.alertTime = alertTime;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}