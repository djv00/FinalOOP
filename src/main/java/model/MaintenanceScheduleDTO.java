package model;

import java.sql.Date;

/**
 * Data Transfer Object for scheduled maintenance tasks.
 * Maps to the 'maintenance_schedule' table.
 *
 * Fields: id, vehicleId, task, scheduledDate, status
 * Used in FR-05 for scheduling future vehicle maintenance.
 *
 * @author Jiangyu Dai
 */
public class MaintenanceScheduleDTO {
    private int id;
    private int vehicleId;
    private String task;
    private Date scheduledDate;
    private String status; // Scheduled, Completed, No Need
    private String vehicleNumber; // Added to store vehicle number from join
    private String vehicleType;   // Added to store vehicle type from join

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public Date getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(Date scheduledDate) { this.scheduledDate = scheduledDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // Getters and setters for additional properties
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
}