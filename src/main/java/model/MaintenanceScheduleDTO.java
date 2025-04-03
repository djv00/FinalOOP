package model;

import java.sql.Date;

/**
 * Data Transfer Object for scheduled maintenance tasks.
 * Maps to the 'maintenance_schedule' table.
 *
 * Fields: id, vehicleId, task, scheduledDate, status
 * Used in FR-05 for scheduling future vehicle maintenance.
 *
 * @author Kai Lu
 */
public class MaintenanceScheduleDTO {
    private int id;
    private int vehicleId;
    private String task;
    private Date scheduledDate;
    private String status; // Scheduled, Completed, No Need

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
}
