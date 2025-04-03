package model;

import java.sql.Timestamp;

/**
 * Data Transfer Object for operator break logs.
 * Maps to the 'breaks' table in the database.
 *
 * Fields: id, userId, vehicleId, breakStart, breakEnd, locationLat, locationLng
 *
 * Used to manually record when operators go on break.
 *
 * @author Kai Lu
 */
public class BreakDTO {
    private int id;
    private int userId;
    private int vehicleId;
    private Timestamp breakStart;
    private Timestamp breakEnd;
    private double locationLat;
    private double locationLng;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public Timestamp getBreakStart() { return breakStart; }
    public void setBreakStart(Timestamp breakStart) { this.breakStart = breakStart; }

    public Timestamp getBreakEnd() { return breakEnd; }
    public void setBreakEnd(Timestamp breakEnd) { this.breakEnd = breakEnd; }

    public double getLocationLat() { return locationLat; }
    public void setLocationLat(double locationLat) { this.locationLat = locationLat; }

    public double getLocationLng() { return locationLng; }
    public void setLocationLng(double locationLng) { this.locationLng = locationLng; }
}
