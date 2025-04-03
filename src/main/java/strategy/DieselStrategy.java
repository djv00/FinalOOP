package strategy;

import model.FuelLogDTO;

/**
 * Strategy for calculating efficiency for diesel or gasoline fuel (litres or gallons).
 * Applies to Diesel Bus and Diesel-Electric Train.
 *
 * Returns km per litre or gallon.
 *
 * @author Kai Lu
 */
public class DieselStrategy implements ConsumptionStrategy {

    @Override
    public double calculateEfficiency(FuelLogDTO log) {
        if (log.getAmount() <= 0) return 0;
        return log.getDistanceKm() / log.getAmount();
    }
} 
