package model;

import java.sql.Timestamp;

/**
 * DTO for operator performance logs.
 * Maps to the 'performance_logs' table in the database.
 *
 * Fields: id, userId, vehicleId, scheduledArrival, actualArrival, efficiencyScore
 * Used in FR-06 for reporting on-time performance.
 *
 * @author Kai Lu
 */
public class PerformanceLogDTO {
    private int id;
    private int userId;
    private int vehicleId;
    private Timestamp scheduledArrival;
    private Timestamp actualArrival;
    private double efficiencyScore;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public Timestamp getScheduledArrival() { return scheduledArrival; }
    public void setScheduledArrival(Timestamp scheduledArrival) { this.scheduledArrival = scheduledArrival; }

    public Timestamp getActualArrival() { return actualArrival; }
    public void setActualArrival(Timestamp actualArrival) { this.actualArrival = actualArrival; }

    public double getEfficiencyScore() { return efficiencyScore; }
    public void setEfficiencyScore(double efficiencyScore) { this.efficiencyScore = efficiencyScore; }
}
