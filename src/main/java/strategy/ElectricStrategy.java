package strategy;

import model.FuelLogDTO;

/**
 * Strategy for calculating energy efficiency for electric vehicles.
 * Applies to Electric Light Rail using kWh.
 *
 * Returns km per kWh.
 *
 * @author Kai Lu
 */
public class ElectricStrategy implements ConsumptionStrategy {

    @Override
    public double calculateEfficiency(FuelLogDTO log) {
        if (log.getAmount() <= 0) return 0;
        return log.getDistanceKm() / log.getAmount();
    }
} 
