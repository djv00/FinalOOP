package dao;

import model.FuelLogDTO;
import java.util.List;

/**
 * DAO interface for handling fuel/energy usage logs.
 * Applies DAO pattern to interact with the 'fuel_logs' table.
 * Used in FR-04 for energy/fuel monitoring.
 *
 * @author Kai Lu
 */
public interface EnergyConsumptionDAO {

    void insertFuelLog(FuelLogDTO log) throws Exception;
    List<FuelLogDTO> getLogsByVehicleId(int vehicleId) throws Exception;
    List<FuelLogDTO> getAllLogs() throws Exception;

    /**
     * Calculates fuel/energy efficiency for a given log.
     * @param fuelLog the fuel log to calculate efficiency for
     * @return the calculated efficiency
     * @throws Exception if calculation fails
     */
    double calculateEfficiency(FuelLogDTO fuelLog) throws Exception;  // 添加方法声明
}
