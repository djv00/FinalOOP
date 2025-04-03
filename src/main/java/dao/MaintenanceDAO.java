package dao;

import model.MaintenanceAlertDTO;
import java.util.List;

/**
 * DAO interface for handling maintenance alerts.
 * Applies DAO Pattern for FR-05 Predictive Maintenance.
 *
 * Used by observer modules to trigger or retrieve alerts.
 *
 * @author Kai Lu
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
     * Marks a specific alert as resolved.
     * @param alertId alert ID to resolve
     * @throws Exception if database operation fails
     */
    void markAlertResolved(int alertId) throws Exception;
}
