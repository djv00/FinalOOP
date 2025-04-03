package strategy;

import model.FuelLogDTO;

/**
 * Strategy interface for calculating fuel/energy efficiency.
 * Implemented using Strategy Pattern in FR-04.
 *
 * Example calculation:
 *   efficiency = distance / amount
 *
 * Each strategy handles a specific unit (litres, gallons, kWh).
 *
 * @author Kai Lu
 */
public interface ConsumptionStrategy {

    /**
     * Calculates efficiency based on a fuel/energy log.
     * @param log the fuel log DTO
     * @return calculated efficiency (e.g. km per unit)
     */
    double calculateEfficiency(FuelLogDTO log);
} 
