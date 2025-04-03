package dao;

import model.BreakDTO;
import java.util.List;

/**
 * DAO interface for managing operator break logs.
 * Provides methods to insert and retrieve break records
 * from the 'breaks' table.
 *
 * Used in FR-03 to support manual out-of-service logging by operators.
 *
 * @author Kai Lu
 */
public interface BreakDAO {

    /**
     * Logs a new break record.
     * @param breakDTO the break record to log
     * @throws Exception if database operation fails
     */
    void logBreak(BreakDTO breakDTO) throws Exception;

    /**
     * Retrieves all break records for a specific user.
     * @param userId the ID of the user/operator
     * @return list of BreakDTO
     * @throws Exception if database operation fails
     */
    List<BreakDTO> getBreaksByUserId(int userId) throws Exception;

    /**
     * Retrieves all break records for a specific vehicle.
     * @param vehicleId the ID of the vehicle
     * @return list of BreakDTO
     * @throws Exception if database operation fails
     */
    List<BreakDTO> getBreaksByVehicleId(int vehicleId) throws Exception;
}
