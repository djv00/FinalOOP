package dao;

import model.VehicleDTO;
import java.util.List;

/**
 * DAO interface for managing vehicle data.
 * Provides methods to add, retrieve, and delete vehicles
 * from the 'vehicles' table in the database.
 *
 * Applies DAO pattern to abstract database operations
 * related to vehicle management.
 *
 * @author Kai Lu
 */
public interface VehicleDAO {

    /**
     * Registers a new vehicle in the system.
     * @param vehicle the vehicle to be added
     * @throws Exception if SQL operation fails
     */
    void addVehicle(VehicleDTO vehicle) throws Exception;

    /**
     * Retrieves all vehicles from the database.
     * @return a list of all vehicles
     * @throws Exception if SQL operation fails
     */
    List<VehicleDTO> getAllVehicles() throws Exception;

    /**
     * Retrieves a vehicle by its ID.
     * @param id the vehicle ID
     * @return a VehicleDTO object or null if not found
     * @throws Exception if SQL operation fails
     */
    VehicleDTO getVehicleById(int id) throws Exception;

    /**
     * Deletes a vehicle by its ID.
     * @param id the vehicle ID to delete
     * @throws Exception if SQL operation fails
     */
    void deleteVehicle(int id) throws Exception;
} 
