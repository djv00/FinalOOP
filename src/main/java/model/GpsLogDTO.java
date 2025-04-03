package model;

import java.sql.Timestamp;

/**
 * Data Transfer Object for storing GPS log entries.
 * Maps to the 'gps_logs' table in the database.
 *
 * Fields: id, vehicleId, latitude, longitude, stationName,
 * arrivalTime, departureTime, atFinalStation, timestamp
 *
 * Used by GpsTrackingDAO for logging and retrieving vehicle positions.
 *
 * @author Kai Lu
 */
public class GpsLogDTO {
    private int id;
    private int vehicleId;
    private double latitude;
    private double longitude;
    private String stationName;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private boolean atFinalStation;
    private Timestamp timestamp;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    public Timestamp getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Timestamp arrivalTime) { this.arrivalTime = arrivalTime; }

    public Timestamp getDepartureTime() { return departureTime; }
    public void setDepartureTime(Timestamp departureTime) { this.departureTime = departureTime; }

    public boolean isAtFinalStation() { return atFinalStation; }
    public void setAtFinalStation(boolean atFinalStation) { this.atFinalStation = atFinalStation; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
