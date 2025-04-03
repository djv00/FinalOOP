package dao;

import model.GpsLogDTO;
import java.util.List;

/**
 * DAO interface for handling GPS tracking logs.
 * Supports operations to insert and retrieve vehicle location data
 * from the 'gps_logs' table.
 *
 * Part of FR-03 GPS Tracking functionality.
 *
 * @author Kai Lu
 */
public interface GpsTrackingDAO {

    /**
     * Inserts a new GPS location log for a vehicle.
     * @param log the GpsLogDTO containing position and status data
     * @throws Exception if a database error occurs
     */
    void logVehicleLocation(GpsLogDTO log) throws Exception;

    /**
     * Retrieves all GPS logs for a specific vehicle.
     * @param vehicleId the vehicle ID
     * @return list of GPS logs for the vehicle
     * @throws Exception if a database error occurs
     */
    List<GpsLogDTO> getLogsByVehicleId(int vehicleId) throws Exception;

    /**
     * Retrieves the latest location log for a vehicle.
     * @param vehicleId the vehicle ID
     * @return the most recent GpsLogDTO
     * @throws Exception if a database error occurs
     */
    GpsLogDTO getLatestLocation(int vehicleId) throws Exception;
} 
