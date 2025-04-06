package dao;

import model.MaintenanceAlertDTO;
import model.MaintenanceScheduleDTO;
import java.util.List;

/**
 * DAO interface for handling maintenance alerts.
 * Applies DAO Pattern for FR-05 Predictive Maintenance.
 *
 * Used by observer modules to trigger or retrieve alerts.
 *
 * @author Jiangyu Dai
 */
public interface MaintenanceDAO {

    /**
     * Inserts a new maintenance alert.
     * @param alert the alert DTO
     * @throws Exception if database operation fails
     */
    void insertAlert(MaintenanceAlertDTO alert) throws Exception;

    /**
     * Retrieves unresolved alerts for a specific vehicle.
     * @param vehicleId vehicle ID
     * @return list of unresolved alerts
     * @throws Exception if database operation fails
     */
    List<MaintenanceAlertDTO> getUnresolvedAlertsByVehicle(int vehicleId) throws Exception;
    
    /**
     * Retrieves all alerts (both resolved and unresolved) for a specific vehicle.
     * @param vehicleId vehicle ID
     * @return list of all alerts
     * @throws Exception if database operation fails
     */
    List<MaintenanceAlertDTO> getAllAlertsByVehicle(int vehicleId) throws Exception;

    /**
     * Marks a specific alert as resolved.
     * @param alertId alert ID to resolve
     * @throws Exception if database operation fails
     */
    void markAlertResolved(int alertId) throws Exception;
    
    /**
     * Gets a specific alert by its ID.
     * @param alertId alert ID to retrieve
     * @return the alert or null if not found
     * @throws Exception if database operation fails
     */
    MaintenanceAlertDTO getAlertById(int alertId) throws Exception;
    
    /**
     * Inserts a new maintenance schedule.
     * @param schedule the schedule to insert
     * @throws Exception if database operation fails
     */
    void insertSchedule(MaintenanceScheduleDTO schedule) throws Exception;
    
    /**
     * Updates the status of a maintenance schedule.
     * @param scheduleId ID of the schedule to update
     * @param status new status
     * @throws Exception if database operation fails
     */
    void updateScheduleStatus(int scheduleId, String status) throws Exception;
    
    /**
     * Retrieves all maintenance schedules.
     * @return list of all maintenance schedules
     * @throws Exception if database operation fails
     */
    List<MaintenanceScheduleDTO> getAllSchedules() throws Exception;
}